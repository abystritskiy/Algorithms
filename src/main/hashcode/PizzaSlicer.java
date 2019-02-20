import java.util.*;

public class PizzaSlicer {
    public static final int INTERVAL = 120000;

    char[][] grid;
    int low, high;

    /* possible slice sizes */
    private List<Slice> sizes;

    /* cells used in slices */
    private boolean[][] sliced;

    /* slices left-top right bottom coordinates (y,x) */
    public List<int[]> coordinates;

    /* total maximum area of the slices */
    public Integer max = 0;

    /* slices left-top right bottom temp-coordinates (y,x) */
    public List<int[]> tempCoordinates;

    /* total temp-maximum area of the slices */
    public Integer tempMax = 0;

    /* Last max value updated - break if it is stuck */
    long lastMaxUpdate;

    /**
     * Constructor - reads pizza from the file
     *
     */
    public PizzaSlicer(int low, int high, char[][] grid) {
        this.low = low;
        this.high = high;
        this.grid = grid;
        this.lastMaxUpdate = System.currentTimeMillis();
        this.sliced = new boolean[grid.length][grid[0].length];
        this.coordinates = new ArrayList<>();
        this.tempCoordinates = new ArrayList<>();

        calcSizes();


    }

    /**
     * Constructor - generates random rows*cols pizza
     *
     * @param rows
     * @param cols
     */
    public PizzaSlicer(int rows, int cols, int low, int high) {
        this.low = low;
        this.high = low;
        this.lastMaxUpdate = System.currentTimeMillis();
        grid = new char[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = (Math.random() > 0.5 ? Pizza.TOMATO : Pizza.MUSHROOM);
            }
        }
        calcSizes();

        sliced = new boolean[grid.length][grid[0].length];
        coordinates = new ArrayList<>();
        tempCoordinates = new ArrayList<>();

    }

    /**
     * Recursively try to place slices and measure the covered size
     * Try bigger first.
     *
     * @param points
     * @return
     */
    public boolean slice( List<List<Integer>> points) {

        if (tempMax == grid.length * grid[0].length) {
            max = tempMax;
            coordinates = new ArrayList<>(tempCoordinates);
            return true;
        }

        if (points.isEmpty()) {
            return false;
        }

        // If we stuck for 2 minutes - let's move over
        if (System.currentTimeMillis() - lastMaxUpdate > INTERVAL) {
            return false;
        }

        for (List<Integer> point : points) {
            int y0 = point.get(0);
            int x0 = point.get(1);

            for (Slice size : sizes) {
                size.locate(y0, x0);


                if (!size.isValidSlice(low, sliced)) {
                    continue;
                }

                tempCoordinates.add(
                    new int[]{y0, x0, y0 + size.rows - 1, x0 + size.cols - 1}
                );
                tempMax += size.rows * size.cols;
                size.setSliced(sliced);

                List<List<Integer>> next = new ArrayList<>();
                List<Integer> rightPoint = size.getNextRightPoint(sliced);
                List<Integer> bottomPoint = size.getNextBottomPoint(sliced);

                if (rightPoint != null && rightPoint.size()>0) {
                    next.add(rightPoint);
                }
                if (bottomPoint != null && bottomPoint.size()>0) {
                    next.add(bottomPoint);
                }

                if (slice(next)) {
                    return true;
                } else {
                    size.locate(y0,x0);
                    if (tempMax > max) {
                        max = tempMax;
                        System.out.println("new max: " + max);
                        lastMaxUpdate = System.currentTimeMillis();
                        coordinates = new ArrayList<>(tempCoordinates);
                    }

                    // rollback
                    size.setUnsliced(sliced);
                    tempMax -= size.rows * size.cols;
                    tempCoordinates.remove(tempCoordinates.size()-1);
                }
            }

        }

        return false;
    }


    /**
     * Get available slice sizes
     * sorted from most fitting the 'high'
     * to least
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
     * For visual testing only
     *
     * @param slices
     */
    public void showCovered(List<int[]> slices) {
        Pizza pizza = new Pizza(this.grid);

        for (int[] slice : slices) {
            int y0 = slice[0], x0 = slice[1], y1 = slice[2], x1 = slice[3];

            for (int y=y0; y<=y1; y++) {
                for (int x=x0; x<=x1; x++) {
                    this.grid[y][x] = 176;
                }
            }
        }
        pizza.printPizza();
    }
}
