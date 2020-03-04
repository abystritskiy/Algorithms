package org.mlxxiv.lc;

public class LC994 {
    static final int EMPTY = 0;
    static final int FRESH = 1;
    static final int ROTTEN = 2;

    public int orangesRotting(int[][] grid) {
        int elapsed = 0;

        while (hasFresh(grid)) {
            int[][] gridNextMinute = applyMinute(grid);
            if (arraysEqual(grid, gridNextMinute)) {
                return -1;
            }
            grid = gridNextMinute;
            elapsed++;
        }

        return elapsed;
    }

    private int[][] applyMinute(final int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (hasRottenNeighbour(grid,i, j) && grid[i][j] == FRESH) {
                    copy[i][j] = ROTTEN;
                }
            }
        }
        return copy;
    }


    private boolean hasRottenNeighbour(int[][] grid, int i, int j) {
        if (i > 0 && grid[i-1][j] == ROTTEN) return true;
        if (i < grid.length-1 && grid[i+1][j] == ROTTEN) return true;
        if (j > 0 && grid[i][j-1] == ROTTEN) return true;
        if (j < grid[i].length-1 && grid[i][j+1] == ROTTEN) return true;
        return false;
    }

    private boolean hasFresh(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == FRESH) return true;
            }
        }
        return false;
    }

    private boolean arraysEqual(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) return false;
            }
        }
        return true;

    }
}

