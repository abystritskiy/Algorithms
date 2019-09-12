package org.mlxxiv.kickstart;

import java.io.*;
import java.util.*;

public class A2_19 {

    private static int parcels(int[][] map) {
        int[] maxManDistanceCell = getMaxDistanceCell(map);
        map[maxManDistanceCell[0]][maxManDistanceCell[1]] = 1;
        int[] maxManDistanceAfterUpdateCell = getMaxDistanceCell(map);
        return maxManDistanceAfterUpdateCell[2];
    }

    private static int[] getMaxDistanceCell(int[][] map) {
        int maxManDistance = 0;
        int[] maxManDistanceCoord = new int[] {0,0};
        for (int r=0; r<map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                int manDistance = calcManDistance(r, c, map);
                if (manDistance > maxManDistance) {
                    maxManDistance = manDistance;
                    maxManDistanceCoord = new int[] {r, c};
                }
            }
        }
        return new int[] {maxManDistanceCoord[0], maxManDistanceCoord[1], maxManDistance};
    }

    public static int calcManDistance(int r, int c, int[][] map)  {
        if (map[r][c] == 1)  return 0;
        int step = 0;
        int[] cell = getClosestCell(r, c, step, map);
        while (cell == null) {
            if (step > map.length) {
                return map.length;
            }
            cell = getClosestCell(r, c, ++step, map);
        }
        return Math.abs(r - cell[0]) + Math.abs(c - cell[1]);
    }

    public static int[] getClosestCell(int r, int c, int step, int[][] map) {

        if (c+step < map[r].length && map[r][c+step] == 1) {
            return new int[] {r, c+step}; // right
        }
        if (c-step >= 0 && map[r][c-step] == 1) {
            return new int[] {r, c-step}; // left
        }
        if (r-step >= 0 && map[r-step][c] == 1) {
            return new int[] {r-step, c}; // top
        }
        if (r+step < map.length && map[r+step][c] == 1) {
            return new int[] {r+step, c}; // bottom
        }

        int i = 1;
        while (i <=step) {
            if (c-step >= 0 && r-i>=0 && map[r-i][c-step] == 1) {
                return new int[] {r-i, c-step};
            }
            if (c-step >= 0 && r+i<map.length && map[r+i][c-step] == 1) {
                return new int[] {r+i, c-step};
            }
            if (c+step < map[r].length && r-i>=0 && map[r-i][c+step] == 1) {
                return new int[] {r-i, c+step};
            }
            if (c+step < map[r].length && r+i<map.length && map[r+i][c+step] == 1) {
                return new int[] {r+i, c+step};
            }

            if (r-step >= 0 && c-i >= 0 && map[r-step][c-i] == 1) {
                return new int[] {r-step, c-i};
            }
            if (r-step >= 0 && c+i < map[r-step].length && map[r-step][c+i] == 1) {
                return new int[] {r-step, c+i};
            }

            if (r+step < map.length && c-i >= 0 && map[r+step][c-i] == 1) {
                return new int[] {r+step, c-i};
            }
            if (r+step < map.length && c+i < map[r+step].length && map[r+step][c+i] == 1) {
                return new int[] {r+step, c+i};
            }

            i++;
        }
        return null;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");

            int r = Integer.parseInt(numbers[0]);
            int c = Integer.parseInt(numbers[1]);
            int[][] map = new int[r][];
            for (int j = 0; j < r; j++) {
                String[] rowString = scanner.nextLine().split("");
                int[] row = new int[c];
                for (int k = 0; k < c; k++) {
                    row[k] = Integer.parseInt(rowString[k]);
                }
                map[j] = row;
            }
            int result = parcels(map);
            System.out.println(String.format("Case #%d: %d", i+1, result));
        }
    }
}
