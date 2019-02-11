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
     * Check if the slice (if left top corner coordinates y0, x0)
     * can be fit within the pizza
     *
     * @param y0
     * @param x0
     * @return
     */
    public boolean fitsThePizza(int y0, int x0) {
        return (y0 + rows <= pizza.length && x0 + rows <= pizza[0].length);
    }

    /**
     * Check if slice has tomatoes and mushrooms more or equals than "low"
     *
     * @param y0
     * @param x0
     * @param low
     * @return
     */
    public boolean isValidSlice(int y0, int x0, int low, boolean[][] sliced) {
        if (!this.fitsThePizza(y0, x0) || heated[y0][x0] < low || (area - heated[y0][x0]) < low) {
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
     * @param y0
     * @param x0
     * @param sliced
     */
    public void setSliced(int y0, int x0, boolean[][] sliced) {
        for (int y = y0; y < y0 + this.rows; y++) {
            for (int x = x0; x < x0 + this.cols; x++) {
                sliced[y][x] = true;
            }
        }
    }

    /**
     * Revert 'setSliced'
     *
     * @param y0
     * @param x0
     * @param sliced
     */
    public void setUnsliced(int y0, int x0, boolean[][] sliced) {
        for (int y = y0; y < y0 + this.rows; y++) {
            for (int x = x0; x < x0 + this.cols; x++) {
                sliced[y][x] = false;
            }
        }
    }

    /**
     * Get next point to the right
     *
     * @param y0
     * @param x0
     * @param sliced
     * @return
     */
    public List<Integer> getNextRightPoint(int y0, int x0, boolean[][] sliced) {
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
     * @param y0
     * @param x0
     * @param sliced
     * @return
     */
    public List<Integer> getNextBottomPoint(int y0, int x0, boolean[][] sliced) {
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
     * @param y0
     * @param x0
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
     *
     * @param y0
     * @param x0
     */
    public void print(int y0, int x0) {
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
        return "{" + rows + ", " + cols + "}";
    }
}