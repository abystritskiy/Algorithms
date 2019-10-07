package org.mlxxiv.lc;

import java.util.LinkedList;

public class Interview1_1 {
    static final int EMPTY = 0;
    static final int FRESH = 1;
    static final int ROTTEN = 2;

    public static void main(String[] args) {
        int[][] test1 = {{2,1,1}, {1,1,0}, {0,1,1}};
        System.out.println(orangesRotting(test1)); //4

        int[][] test2 = {{2,1,1}, {0,1,1}, {1,0,1}};
        System.out.println(orangesRotting(test2)); //-1

        int[][] test3 = {{0,2}};
        System.out.println(orangesRotting(test3)); //0
    }

    public static int orangesRotting(int[][] grid) {
        int res = 0;

        boolean hasFresh = false;
        LinkedList<int[]> rotten = new LinkedList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == ROTTEN) {
                    rotten.add(new int[]{r,c});
                } else if (grid[r][c] == FRESH) {
                    hasFresh = true;
                }
            }
        }
        if (rotten.isEmpty() && hasFresh) return -1;
        if (rotten.isEmpty() && !hasFresh) return 0;

        while (!rotten.isEmpty()) {
            LinkedList<int[]> newRotten = new LinkedList<>();
            for (int[] rottenCell: rotten) {
                int y = rottenCell[0];
                int x = rottenCell[1];

                if (y>0 && grid[y-1][x] == FRESH) {
                    newRotten.add(new int[]{y-1,x});
                    grid[y-1][x] = ROTTEN;
                }
                if (y<grid.length-1 && grid[y+1][x] == FRESH) {
                    newRotten.add(new int[]{y+1, x});
                    grid[y+1][x] = ROTTEN;
                }
                if (x > 0 && grid[y][x-1] == FRESH) {
                    newRotten.add(new int[]{y, x-1});
                    grid[y][x-1] = ROTTEN;
                }
                if (x < grid[y].length-1 && grid[y][x+1] == FRESH) {
                    newRotten.add(new int[]{y, x+1});
                    grid[y][x+1] = ROTTEN;
                }
            }
            rotten = newRotten;
            res++;
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == FRESH) {
                    return -1;
                }
            }
        }

        return res-1;
    }
}
