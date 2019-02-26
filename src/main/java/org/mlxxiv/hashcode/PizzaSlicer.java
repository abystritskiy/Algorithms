package org.mlxxiv.hashcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class which finds out the best way to cut pizza into slices + utility methods
 */
public class PizzaSlicer {
    // that's how much (in milliseconds) I am ready to wait for it to finish calculation of one iteration
    public static final int INTERVAL = 120000;

    // pizza piece - all this nice tomatoes and mushrooms in the form of char array
    char[][] grid;

    // minimal num of each component, maximum number of pieces in the slice
    int low, high;

    /* defines where the start and where to place new slice */
    public final Solver.Orientation orientation;

    /* possible slice sizes */
    private List<Slice> sizes;

    /* cells used in slices */
    private boolean[][] sliced;

    /* slices left-top right bottom coordinates (y,x) */
    public List<int[]> coordinates;

    /* total maximum area of the slices */
    public int max = 0;

    /* slices left-top right bottom temp-coordinates (y,x) */
    public List<int[]> tempCoordinates;

    /* total temp-maximum area of the slices */
    public int tempMax = 0;

    /* Last max value updated - break if it is stuck */
    long lastMaxUpdateTime;

    /**
     * Constructor - reads pizza from the file
     *
     * @param low           minimum number of each component on the slice
     * @param high          maximum slice size
     * @param grid          pizza components array
     * @param orientation   orientation (e.g TOP-LEFT) from which corner start to put slices
     * @param sliced        map indicated which cells had been used to avoid overlapping
     */
    public PizzaSlicer(int low, int high, char[][] grid, Solver.Orientation orientation, boolean[][] sliced) {
        this.low = low;
        this.high = high;
        this.grid = grid;
        this.orientation = orientation;
        this.sliced = sliced;
        this.sliced = new boolean[grid.length][grid[0].length];

        this.lastMaxUpdateTime = System.currentTimeMillis();
        this.coordinates = new ArrayList<>();
        this.tempCoordinates = new ArrayList<>();

        calcSizes();
    }

    /**
     * Constructor - generates random rows*cols pizza
     *
     * @param rows  number of rows in pizza
     * @param cols  number of cols in pizza
     */
    public PizzaSlicer(int rows, int cols, int low, int high, Solver.Orientation orientation) {
        this.low = low;
        this.high = low;
        this.orientation = orientation;
        this.lastMaxUpdateTime = System.currentTimeMillis();
        grid = new char[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = (Math.random() > 0.5 ? Pizza.TOMATO : Pizza.MUSHROOM);
            }
        }

        sliced = new boolean[grid.length][grid[0].length];
        coordinates = new ArrayList<>();
        tempCoordinates = new ArrayList<>();

        calcSizes();
    }

    /**
     * Recursively try to place slices and measure the covered size
     * Try bigger first. Uses backtracking strategy
     *
     * @param points    y,x coordinates to try to put next slice to
     * @return          true/false to continue/stop further processing
     */
    public boolean slice(List<List<Integer>> points) {
        // if whole pizza is covered, no need to search further
        if (tempMax == grid.length * grid[0].length) {
            max = tempMax;
            coordinates = new ArrayList<>(tempCoordinates);
            return true;
        }

        // nothing to do here - go home!
        if (points.isEmpty()) {
            return false;
        }

        // If we stuck for too long - let's move over
        if (System.currentTimeMillis() - lastMaxUpdateTime > INTERVAL) {
            return false;
        }

        // let's try all the positions we calculated on the previous step
        for (List<Integer> point : points) {
            int y0 = point.get(0);
            int x0 = point.get(1);

            // go through all the possible slice sizes
            for (Slice size : sizes) {
                size.locate(y0, x0, orientation);

                // check if slice contains required number of ingredients
                // and do not overlaps with other slices
                if (!size.isValidSlice(low, sliced)) {
                    continue;
                }

                // memorizing temporary results
                rememberSlicePosition(size);
                tempMax += size.rows * size.cols;
                size.setSliced(sliced);

                List<List<Integer>> next = getNextPoints(size, this.orientation);

                if (slice(next)) {
                    return true;
                } else {
                    size.locate(y0, x0, orientation);
                    if (tempMax > max) {
                        // we've found something better then we had! lets save it for the future
                        max = tempMax;
                        lastMaxUpdateTime = System.currentTimeMillis();
                        coordinates = new ArrayList<>(tempCoordinates);
                    }

                    // rollback
                    size.setUnsliced(sliced);
                    tempMax -= size.rows * size.cols;
                    tempCoordinates.remove(tempCoordinates.size() - 1);
                }
            }
        }
        return false;
    }

    /**
     * Remember slice top-left and bottom-right coordintates
     *
     * @param slice slice with it's size and position
     */
    public void rememberSlicePosition(Slice slice) {
        int[] leftTop = slice.getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];
        tempCoordinates.add(
                new int[]{yS, xS, yS + slice.rows - 1, xS + slice.cols - 1}
        );
    }

    /**
     * Get next points to put slices.
     * Ideally, need to move around starting point to find the
     * most optimal solution, but in practice it is too expensive
     *
     * @param orientation   orientation (e.g TOP-LEFT) from which corner start to put slices
     * @param size          slice with it's size and position
     * @return              list of the points to try put slices next
     */
    private List<List<Integer>> getNextPoints(Slice size, Solver.Orientation orientation) {
        List<List<Integer>> next = new ArrayList<>();
        if (orientation == Solver.Orientation.TOP_LEFT) {
            List<Integer> rightPoint = size.getNextRightTopPoint(sliced);
            List<Integer> bottomPoint = size.getNextBottomLeftPoint(sliced);

            if (rightPoint != null && rightPoint.size() > 0) {
                next.add(rightPoint);
            }
            if (bottomPoint != null && bottomPoint.size() > 0) {
                next.add(bottomPoint);
            }
        } else if (orientation == Solver.Orientation.TOP_RIGHT) {
            List<Integer> leftPoint = size.getNextLeftTopPoint(sliced);
            List<Integer> bottomPoint = size.getNextBottomRightPoint(sliced);

            if (leftPoint != null && leftPoint.size() > 0) {
                next.add(leftPoint);
            }
            if (bottomPoint != null && bottomPoint.size() > 0) {
                next.add(bottomPoint);
            }
        } else if (orientation == Solver.Orientation.BOTTOM_LEFT) {
            List<Integer> rightPoint = size.getNextRightBottomPoint(sliced);
            List<Integer> topPoint = size.getNextTopLeftPoint(sliced);

            if (rightPoint != null && rightPoint.size() > 0) {
                next.add(rightPoint);
            }
            if (topPoint != null && topPoint.size() > 0) {
                next.add(topPoint);
            }
        } else if (orientation == Solver.Orientation.BOTTOM_RIGHT) {
            List<Integer> leftPoint = size.getNextLeftBottomPoint(sliced);
            List<Integer> topPoint = size.getNextTopRightPoint(sliced);

            if (leftPoint != null && leftPoint.size() > 0) {
                next.add(leftPoint);
            }
            if (topPoint != null && topPoint.size() > 0) {
                next.add(topPoint);
            }
        }
        return next;
    }


    /**
     * Get available slice sizes sorted from the biggest to smallest (by size).
     * No need to include the piece, that does not fit into pizza or
     * has not enough components
     *
     * @return
     */
    private void calcSizes() {
        int pizzaRows = grid.length;
        int pizzaCols = grid[0].length;

        sizes = new ArrayList<>();
        for (int i = high; i >= 1; i--) {
            for (int j = 1; j <= high; j++) {
                if (i > pizzaRows || j > pizzaCols || i * j < 2 * low) {
                    continue;
                }
                if (i * j <= high) {
                    Slice size = new Slice(i, j, grid);
                    sizes.add(size);
                }
            }
        }
        sizes.sort(Collections.reverseOrder());
    }

    /**
     * Show pizza after we 'cut' all the slices
     *
     * @param slices    list of the slices coordinates - y,x for left-top; y,x for right bottom
     */
    public Pizza getCutPizza(List<int[]> slices) {
        Pizza pizza = new Pizza(this.grid);

        for (int[] slice : slices) {
            int y0 = slice[0], x0 = slice[1], y1 = slice[2], x1 = slice[3];

            for (int y = y0; y <= y1; y++) {
                for (int x = x0; x <= x1; x++) {
                    this.grid[y][x] = Pizza.EMPTY;
                }
            }
        }
        return pizza;
    }

    /**
     * Try to extract blocks that left
     *
     * @param pizza     pizza components array (including empty spots)
     * @return          list of the empty rectangles (y,x of top-left; y,x if bottom-right)
     */
    public List<int[]> getRemnants(char[][] pizza) {
        List<int[]> remnants = new ArrayList<>();

        int y = 0;
        int x = 0;

        while (y < pizza.length) {
            while (x < pizza[0].length) {
                if (pizza[y][x] == Pizza.EMPTY || (y - 1 >= 0 && pizza[y - 1][x] != Pizza.EMPTY)) {
                    x++;
                    continue;
                }
                int y0 = y;
                int x0 = x;
                int y1 = y;
                int x1 = x;

                while (y+1 < pizza.length && pizza[y+1][x] != Pizza.EMPTY) {
                    y1 = y+1;
                    y = y1;
                }

                while (isValidCol(x+1, y0, y1, pizza)) {
                    x1 = x + 1;
                    x = x1;
                }

                remnants.add(new int[] {
                   y0, x0, y1, x1
                });
                y = y0;
                x = x1 + 1;

            }
            y++;
            x = 0;
        }

        return remnants;
    }

    /**
     * Check if the col does not contain empty cells;
     *
     * @param x         x - col position
     * @param y0        y start point
     * @param y1        y end row
     * @param pizza     pizza piece array
     * @return          if it does not has empty cells
     */
    public static boolean isValidCol(int x, int y0, int y1, char[][] pizza) {
        if (x >= pizza[0].length) {
            return false;
        }
        for (int y=y0; y<=y1; y++) {
            if (pizza[y][x] == Pizza.EMPTY) {
                return false;
            }
        }
        return true;
    }
}
