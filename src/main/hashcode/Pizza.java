import java.lang.*;

public class Pizza {
    public final int rows;
    public final int cols;
    public final char[][] grid;

    public final char TOMATO = 'T';
    public final char MASHROOM = 'M';
    public final int totalTomatoes;

    public Pizza(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;

        int count = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (this.grid[y][x] == 'T') {
                    count++;
                }
            }
        }
        this.totalTomatoes = count;

    }

    /**
     * Check if slice has enough Tomatoes and Mushrooms
     *
     * @param rowStart
     * @param colStart
     * @param rowEnd
     * @param colEnd
     * @param l
     * @param h
     * @return
     * @throws Exception
     */
    public boolean isValidSlice(int rowStart, int colStart, int rowEnd, int colEnd, int l, int h) throws Exception {
        int area = Math.abs(rowEnd - rowStart + 1) * Math.abs(colEnd - colStart + 1);
        if (area > h) {
            throw new Exception("Interval is bigger than allowed");
        }

        // Think of pizza cache - which will contain totals of TOMATOES (though SQUARE - TOMATOES == #of MUSHROOMS)
        // Than we can iterate only through rows and cols outside the cached value and cache them.

        int tomatoes = 0;
        for (int y = rowStart; y <= rowEnd; y++) {
            for (int x = colStart; x <= colEnd; x++) {
                if (this.grid[y][x] == 'T') {
                    tomatoes++;
                }
            }
        }
        return tomatoes >= l;
    }

    /**
     * Print pizza
     */
    public void printPizza() {
        System.out.println("Size: " + rows + "x" + cols + "; " +
                "Tomatoes: " + totalTomatoes + "; Mushrooms: " + (rows * cols - totalTomatoes) + "\n");
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Print pizza slice
     *
     * @param startRow
     * @param startCol
     * @param slice
     */
    public void printSlice(int startRow, int startCol, Slice slice) {
        for (int y = startRow; y < startRow + slice.rows; y++) {
            for (int x = startCol; x < startCol + slice.cols; x++) {
                System.out.println();
            }
        }
    }

}
