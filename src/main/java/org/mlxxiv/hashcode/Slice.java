package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Sortable sizes class
 * Also has pre-calculated pizza coverage
 */
class Slice implements Comparable<Slice> {
    public final int rows;
    public final int cols;
    public final int area;
    public final int[][] heated;
    public final char[][] pizza;

    public int y0 = 0;
    public int x0 = 0;
    public Solver.Orientation orientation;

    public Slice(int rows, int colls, char[][] grid) {
        this.rows = rows;
        this.cols = colls;
        this.area = rows * colls;
        this.pizza = grid;
        this.heated = this.heat();
    }

    /**
     * Pre-calculate number of tomatoes in each possible slice
     *
     * @return
     */
    public int[][] heat() {
        int[][] cache = new int[pizza.length][pizza[0].length];
        for (int y = 0; y <= pizza.length - rows; y++) {
            for (int x = 0; x <= pizza[0].length - cols; x++) {
                cache[y][x] = countTomatoes(y, x);
            }
        }
        return cache;
    }

    /**
     * Assign slice to specific coordinates
     *
     * @param y0
     * @param x0
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
     * @return
     */
    public boolean fitsThePizza() {
        if (orientation == Solver.Orientation.TOP_RIGHT) {
            return (y0 + rows <= pizza.length && x0 - cols + 1 >= 0);
        } else if (orientation == Solver.Orientation.BOTTOM_LEFT) {
            return (y0 - rows + 1 >= 0 && cols <= pizza[0].length);
        } else if (orientation == Solver.Orientation.BOTTOM_RIGHT) {
            return (y0 - rows + 1 >= 0 && x0 - cols + 1 >= 0);
        }
        return (y0 + rows <= pizza.length && x0 + cols <= pizza[0].length);
    }

    /**
     * Check if slice has tomatoes and mushrooms more or equals than "low".
     * Check if the slice does not overlap with the previously cut slices
     *
     * @param low
     * @param sliced
     * @return
     */
    public boolean isValidSlice(int low, boolean[][] sliced) {
        int[] leftTop = getLeftTop();
        int yS = leftTop[0];
        int xS = leftTop[1];

        if (!this.fitsThePizza() || heated[yS][xS] < low || (area - heated[yS][xS]) < low) {
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
     * @return
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
     * @param sliced
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
     * @param sliced
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @param sliced
     * @return
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
     * @return
     */
    private int countTomatoes(int y0, int x0) {
        int tomatoes = 0;
        for (int y = y0; y < y0 + rows; y++) {
            for (int x = x0; x < x0 + cols; x++) {
                if (pizza[y][x] == Pizza.TOMATO) {
                    tomatoes++;
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
     * @param that
     * @return
     */
    public int compareTo(Slice that) {
        return this.cols * this.rows > that.cols * that.rows ? 1 : -1;
    }

    /**
     * To string - just for debug
     *
     * @return
     */
    public String toString() {
        return "{" + rows + ", " + cols + "}" + "{" + y0 + ", " + x0 + "}";
    }
}