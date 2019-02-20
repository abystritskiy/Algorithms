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
    public void locate(int y0, int x0) {
        this.y0 = y0;
        this.x0 = x0;
    }

    /**
     * Check if the slice (if left top corner coordinates y0, x0)
     * can be fit within the pizza
     *
     * @return
     */
    public boolean fitsThePizza() {
        return (y0 + rows <= pizza.length && x0 + cols <= pizza[0].length);
    }

    /**
     * Check if slice has tomatoes and mushrooms more or equals than "low"
     *
     * @param low
     * @return
     */
    public boolean isValidSlice(int low, boolean[][] sliced) {
        if (!this.fitsThePizza() || heated[y0][x0] < low || (area - heated[y0][x0]) < low) {
            int x1 = heated[y0][x0];
            int x2 = (area - heated[y0][x0]);
            return false;
        }
        for (int y = y0; y < y0 + this.rows; y++) {
            for (int x = x0; x < x0 + this.cols; x++) {
                if (sliced[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cut the slice out of pizza
     *
     * @param sliced
     */
    public void setSliced(boolean[][] sliced) {
        for (int y = y0; y < y0 + this.rows; y++) {
            for (int x = x0; x < x0 + this.cols; x++) {
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
        for (int y = y0; y < y0 + this.rows; y++) {
            for (int x = x0; x < x0 + this.cols; x++) {
                sliced[y][x] = false;
            }
        }
    }

    /**
     * Get next point to the right
     *
     * @param sliced
     * @return
     */
    public List<Integer> getNextRightPoint(boolean[][] sliced) {
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
     * Get next point to the bottom
     *
     * @param sliced
     * @return
     */
    public List<Integer> getNextBottomPoint(boolean[][] sliced) {
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
        for (int y = y0; y < y0 + rows; y++) {
            for (int x = x0; x < x0 + cols; x++) {
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