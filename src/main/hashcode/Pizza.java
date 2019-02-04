import java.lang.*;

public class Pizza {
    public final int rows;
    public final int cols;
    public final boolean[][] grid;

    public final boolean TOMATO = true;
    public final boolean MASHROOM = false;

    public Pizza(boolean[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

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
                if (this.grid[y][x]) {
                    tomatoes++;
                }
            }
        }
        return tomatoes >= l;
    }
}
