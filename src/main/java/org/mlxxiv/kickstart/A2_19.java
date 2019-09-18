package org.mlxxiv.kickstart;

import java.util.Arrays;
import java.util.Scanner;

public class A2_19 {
    private static int parcels(int[][] map) {
        int[][] manhattan = new int[map.length][];
        boolean hasZeros = false;
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (manhattan[r] == null) manhattan[r] = new int[map[r].length];
                if (map[r][c] == 0) hasZeros = true;
                manhattan[r][c] = calcManhattanDistance(r, c, map);
            }
            log(Arrays.toString(manhattan[r]));
        }

        if (!hasZeros) return 0;

        int minMaxDistance = Integer.MAX_VALUE;
        int[] pos = new int[2];

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 1) continue;
                int prevDistance = manhattan[r][c];
                map[r][c] = 1;

                int[][] copy = copyManhattan(manhattan);
                recalculateManhattanPart(map, manhattan, r, c, prevDistance, copy);

                int maxDistance = getMaxDistance(copy);
                if (maxDistance < minMaxDistance) {
                    minMaxDistance = maxDistance;
                    pos = new int[]{r, c};
                }
                map[r][c] = 0;

            }
        }
        log("\n" + Arrays.toString(pos));
        return minMaxDistance;
    }

    private static void recalculateManhattanPart(int[][] map, int[][] manhattan, int r, int c, int radius, int[][] copy) {
        for (int r1 = 0; r1 < Math.min(manhattan.length, r + radius); r1++) {
            for (int c1 = Math.max(0, c - radius); c1 < Math.min(manhattan[r1].length, c + radius); c1++) {
                copy[r1][c1] = calcManhattanDistance(r1, c1, map);
            }
        }
    }

    private static int[][] copyManhattan(int[][] manhattan) {
        int[][] copy = new int[manhattan.length][manhattan[0].length];
        for (int i = 0; i < manhattan.length; i++) {
            System.arraycopy(manhattan[i], 0, copy[i], 0, manhattan[i].length);
        }
        return copy;
    }

    private static int getMaxDistance(int[][] manh) {
        int res = 0;
        for (int r = 0; r < manh.length; r++) {
            for (int c = 0; c < manh[r].length; c++) {
                if (manh[r][c] > res)
                    res = manh[r][c];
            }
        }
        return res;
    }

    private static int calcManhattanDistance(int r, int c, int[][] map) {
        if (map[r][c] == 1) return 0;
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

    private static int[] getClosestCell(int r, int c, int step, int[][] map) {

        if (c + step < map[r].length && map[r][c + step] == 1) {
            return new int[]{r, c + step}; // right
        }
        if (c - step >= 0 && map[r][c - step] == 1) {
            return new int[]{r, c - step}; // left
        }
        if (r - step >= 0 && map[r - step][c] == 1) {
            return new int[]{r - step, c}; // top
        }
        if (r + step < map.length && map[r + step][c] == 1) {
            return new int[]{r + step, c}; // bottom
        }

        int i = 1;
        while (i <= step) {
            if (c - step >= 0 && r - i >= 0 && map[r - i][c - step] == 1) {
                return new int[]{r - i, c - step};
            }
            if (c - step >= 0 && r + i < map.length && map[r + i][c - step] == 1) {
                return new int[]{r + i, c - step};
            }
            if (c + step < map[r].length && r - i >= 0 && map[r - i][c + step] == 1) {
                return new int[]{r - i, c + step};
            }
            if (c + step < map[r].length && r + i < map.length && map[r + i][c + step] == 1) {
                return new int[]{r + i, c + step};
            }

            if (r - step >= 0 && c - i >= 0 && map[r - step][c - i] == 1) {
                return new int[]{r - step, c - i};
            }
            if (r - step >= 0 && c + i < map[r - step].length && map[r - step][c + i] == 1) {
                return new int[]{r - step, c + i};
            }

            if (r + step < map.length && c - i >= 0 && map[r + step][c - i] == 1) {
                return new int[]{r + step, c - i};
            }
            if (r + step < map.length && c + i < map[r + step].length && map[r + step][c + i] == 1) {
                return new int[]{r + step, c + i};
            }

            i++;
        }

        return null;
    }

    private static void log(String str) {
//        System.out.println(str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
                log(Arrays.toString(row));
                map[j] = row;
            }
            log("");
            int result = parcels(map);
            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}
