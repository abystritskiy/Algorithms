package org.mlxxiv.hr;
/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid/problem
 * Difficulty: medium
 */

import java.util.LinkedList;
import java.util.Queue;

public class ConnectedCells {
    public static void main(String[] args) {
        int[][] test1 = new int[][]{
            {1,1,0,0},
            {0,1,1,0},
            {0,0,1,0},
            {1,0,0,0}
        };
        int res1 = 5;

        int[][] test2 = new int[][]{
            {0, 0, 1, 1},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0}
        };
        int res2 = 8;

        int[][] test3 = new int[][]{
            {0, 1, 0, 0, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 0, 1, 1, 0},
            {0, 1, 0, 1, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 0, 0}
        };
        int res3 = 29;
        System.out.println(connectedCell(test3) == res3);
    }


    static int connectedCell(int[][] matrix) {
        int result = 0;
        int[][] cellsMap = new int[matrix.length][];

        for (int y=0; y<matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 1) {
                    int tempResults = 0;
                    Queue<int[]> queue = new LinkedList<>();


                    validateAndAdd(y, x, matrix, cellsMap, queue);
                    while (!queue.isEmpty()) {
                        int[] task = queue.remove();
                        int y0 = task[0];
                        int x0 = task[1];

                        System.out.println(y0+", "+x0);
                        tempResults++;

                        validateAndAdd(y0-1, x0+1, matrix, cellsMap, queue);
                        validateAndAdd(y0-1, x0-1, matrix, cellsMap, queue);
                        validateAndAdd(y0-1, x0, matrix, cellsMap, queue);
                        validateAndAdd(y0, x0-1, matrix, cellsMap, queue);
                        validateAndAdd(y0, x0+1, matrix, cellsMap, queue);
                        validateAndAdd(y0+1, x0-1, matrix, cellsMap, queue);
                        validateAndAdd(y0+1, x0, matrix, cellsMap, queue);
                        validateAndAdd(y0+1, x0+1, matrix, cellsMap, queue);

                    }

                    if (tempResults > result) {
                        result = tempResults;
                    }
                }
            }
        }
        System.out.println(result);
        return result;
    }

    public static void validateAndAdd(int y, int x, int[][] matrix, int[][] map, Queue<int[]> queue)
    {
        if (y >= matrix.length || y < 0) return;
        if (map[y] == null) {
            map[y] = new int[matrix[y].length];
        }
        if (x >=0 && x < matrix[y].length && matrix[y][x] == 1 && map[y][x] == 0) {
            queue.add(new int[] {y, x});
            map[y][x] = 1;
        }
    }

}
