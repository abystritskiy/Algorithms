package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Sortable sizes class
 * Has pre-calculated pizza coverage and some utility method for easier calculations
 */
class Slice implements Comparable<Slice> {
    public final int rows;
    public final int cols;
    public final int area;
    public final char[][] pizza;

    public int y0 = 0;
    public int x0 = 0;
    public Solver.Orientation orientation;

    /**
     * Construct the slice
     *
     * @param rows   number of the rows in the slice
     * @param cols   number of the cols in the slice
     * @param grid   pizza components array
     */
    public Slice(int rows, int cols, char[][] grid) {
        this.rows = rows;
        this.cols = cols;
        this.area = rows * cols;
        this.pizza = grid;
    }


    /**
     * Assign slice to specific coordinates
     *
     * @param y0    y coordinate of the slice's left top corner
     * @param x0    x coordinate of the slice's left top corner
     */
    public void locate(int y0, int x0, Solver.Orientation orientation) {
        this.y0 = y0;
        this.x0 = x0;
        this.orientation = orientation;
    }

    /**
     * Check if the slice (y0, x0 - left top corner coordinates)
     * can be fit within the pizza
     *
     * @return validation result
     */
    public boolean fitsThePizza() {
        if (orientation == Solver.Orientation.TOP_RIGHT) {
            return (y0 + rows <= pizza.length && x0 - cols + 1 >= 0);
        } else if (orientation == Solver.Orientation.BOTTOM_LEFT) {
            return (y0 - rows + 1 >= 0 && x0 + cols <= pizza[0].length);
        } else if (orientation == Solver.Orientation.BOTTOM_RIGHT) {
            return (y0 - rows + 1 >= 0 && x0 - cols + 1 >= 0);
        }
        return (y0 + rows <= pizza.length && x0 + cols <= pizza[0].length);
    }

    /**
     * Check if slice has tomatoes and mushrooms more or equals than "low".
     * Check if the slice does not overlap with the previously cut slices
     *
     * @param low       minimum number of each component on the slice
     * @param sliced    maximum slice size
     * @return          validates slice if it matches the given conditions
     */
    public boolean isValidSlice(int low, boolean[][] sliced) {
        if (!this.fitsThePizza()) {
            return false;
        }
        int[] leftTop = getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];

        int tomatoes = countTomatoes(yS, xS);

        if (tomatoes < low || (area - tomatoes) < low) {
            return false;
        }

        for (int y = yS; y < yS + this.rows; y++) {
            for (int x = xS; x < xS + this.cols; x++) {
                if (sliced[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get top left cell position
     *
     * @return     y,x coordinates of the left top corner
     */
    public int[] getLeftTop() {
        int yS = y0;
        int xS = x0;

        if (orientation == Solver.Orientation.TOP_RIGHT) {
            yS = y0;
            xS = x0 - this.cols + 1;
        } else if (orientation == Solver.Orientation.BOTTOM_LEFT) {
            yS = y0 - this.rows + 1;
            xS = x0;
        } else if (orientation == Solver.Orientation.BOTTOM_RIGHT) {
            yS = y0 - this.rows + 1;
            xS = x0 - this.cols + 1;
        }
        return new int[] {yS, xS};
    }

    /**
     * Cut the slice out of pizza
     *
     * @param sliced    array with already used pizza cells
     */
    public void setSliced(boolean[][] sliced) {
        int[] leftTop = getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];

        for (int y = yS; y < yS + this.rows; y++) {
            for (int x = xS; x < xS + this.cols; x++) {
                sliced[y][x] = true;
            }
        }
    }

    /**
     * Revert 'setSliced'
     *
     * @param sliced    array with already used pizza cells
     */
    public void setUnsliced(boolean[][] sliced) {
        int[] leftTop = getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];
        for (int y = yS; y < yS + this.rows; y++) {
            for (int x = xS; x < xS + this.cols; x++) {
                sliced[y][x] = false;
            }
        }
    }

    /**
     * Get next top point to the right
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextRightTopPoint(boolean[][] sliced) {
        if (x0 + this.cols >= pizza[0].length) {
            return null;
        }
        int y = y0;
        while (y < y0 + this.rows) {
            if (!sliced[y][x0 + this.cols]) {
                List<Integer> point = new ArrayList<>();
                point.add(y);
                point.add(x0 + this.cols);
                return point;
            } else {
                y++;
            }
        }
        return null;
    }

    /**
     * Get next bottom point to the right
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextRightBottomPoint(boolean[][] sliced) {
        if (x0 + this.cols >= pizza[0].length) {
            return null;
        }
        int y = y0;
        while (y >= 0) {
            if (!sliced[y][x0 + this.cols]) {
                List<Integer> point = new ArrayList<>();
                point.add(y);
                point.add(x0 + this.cols);
                return point;
            } else {
                y--;
            }
        }
        return null;
    }

    /**
     * Get next top point to the left
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextLeftTopPoint(boolean[][] sliced) {
        if (x0 - this.cols < 0) {
            return null;
        }
        int y = y0;
        while (y < y0 + this.rows) {
            if (!sliced[y][x0 - this.cols]) {
                List<Integer> point = new ArrayList<>();
                point.add(y);
                point.add(x0 - this.cols);
                return point;
            } else {
                y++;
            }
        }
        return null;
    }

    /**
     * Get next bottom point to the left
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextLeftBottomPoint(boolean[][] sliced) {
        if (x0 - this.cols < 0) {
            return null;
        }
        int y = y0;
        while (y >= 0) {
            if (!sliced[y][x0 - this.cols]) {
                List<Integer> point = new ArrayList<>();
                point.add(y);
                point.add(x0 - this.cols);
                return point;
            } else {
                y--;
            }
        }
        return null;
    }

    /**
     * Get next point to the bottom
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextBottomLeftPoint(boolean[][] sliced) {
        if (y0 + this.rows >= pizza.length) {
            return null;
        }
        int x = x0;
        while (x < x0 + this.cols) {
            if (!sliced[y0 + this.rows][x]) {
                List<Integer> point = new ArrayList<>();
                point.add(y0 + this.rows);
                point.add(x);
                return point;
            } else {
                x++;
            }
        }
        return null;
    }

    /**
     * Get next point to the bottom
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextBottomRightPoint(boolean[][] sliced) {
        if (y0 + this.rows >= pizza.length) {
            return null;
        }
        int x = x0;
        while (x >= x0 - this.cols) {
            if (!sliced[y0 + this.rows][x]) {
                List<Integer> point = new ArrayList<>();
                point.add(y0 + this.rows);
                point.add(x);
                return point;
            } else {
                x--;
            }
        }
        return null;
    }

    /**
     * Get next point to the top
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextTopLeftPoint(boolean[][] sliced) {
        if (y0 - this.rows < 0) {
            return null;
        }
        int x = x0;
        while (x < x0 + this.cols) {
            if (!sliced[y0 - this.rows][x]) {
                List<Integer> point = new ArrayList<>();
                point.add(y0 - this.rows);
                point.add(x);
                return point;
            } else {
                x++;
            }
        }
        return null;
    }

    /**
     * Get next point to the top
     *
     * @param sliced    array with already used pizza cells
     * @return          get the next point to check
     */
    public List<Integer> getNextTopRightPoint(boolean[][] sliced) {
        if (y0 - this.rows < 0) {
            return null;
        }
        int x = x0;
        while (x >= x0 - this.cols) {
            if (!sliced[y0 - this.rows][x]) {
                List<Integer> point = new ArrayList<>();
                point.add(y0 - this.rows);
                point.add(x);
                return point;
            } else {
                x--;
            }
        }
        return null;
    }

    /**
     * Count tomatoes on a given slice
     *
     * @return  number of tomatoes
     */
    private int countTomatoes(int y0, int x0) {
        int tomatoes = 0;

        if (this.toString().equals("{8, 1}{0, 0}")) {
            int bkp2 = 2;
        }

        for (int y = y0; y < y0 + rows; y++) {
            for (int x = x0; x < x0 + cols; x++) {

                if (pizza[y][x] == Pizza.TOMATO) {
                    tomatoes++;
                } else {
                    int not = 0;
                }
            }
        }
        return tomatoes;
    }

    /**
     * Print slice content - just for debug
     */
    public void print() {
        int[] leftTop = getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];

        for (int y = yS; y < yS + rows; y++) {
            for (int x = xS; x < xS + cols; x++) {
                System.out.println(pizza[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
     * "Compare to" method
     *
     * @param that  slice to compare
     * @return  1 if this is bigger, -1 if that is bigger
     */
    public int compareTo(Slice that) {
        return this.area > that.area ? 1 : -1;
    }

    /**
     * To string - just for debug
     *
     * @return  string representation of the slice
     */
    public String toString() {
        return "{" + rows + ", " + cols + "}" + "{" + y0 + ", " + x0 + "}";
    }
}