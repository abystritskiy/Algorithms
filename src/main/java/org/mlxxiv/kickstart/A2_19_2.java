package org.mlxxiv.kickstart;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class A2_19_2 {
    private static int parcels(int[][] map) {
        try {
            calculateManhattan(map);
        } catch (Exception e) {
            return 0;
        }


        int minMaxDistance = Integer.MAX_VALUE;
        int[] pos = new int[2];

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 1) continue;

                map[r][c] = 1;
                try {
                    int[][] manhattan = calculateManhattan(map);
                    int maxDistance = getMaxDistance(manhattan);
                    if (maxDistance < minMaxDistance) {
                        minMaxDistance = maxDistance;
                        pos = new int[]{r, c};
                    }
                } catch (Exception e) {
                    return 0;
                } finally {
                    map[r][c] = 0;
                }

            }
        }
        log("\n" + Arrays.toString(pos));
        return minMaxDistance;
    }

    private static int[][] calculateManhattan(int[][] map) throws Exception {
        boolean hasZeros = false;
        int[][] manhattan = new int[map.length][];
        for (int r = 0; r < map.length; r++) {
            if (manhattan[r] == null) manhattan[r] = new int[map[r].length];
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 0) hasZeros = true;
                manhattan[r][c] = calcManhattanDistance(r, c, map);
            }
//            log("===");
//            log(Arrays.toString(map[r]));
            log(Arrays.toString(manhattan[r]));
        }
        log("");
        if (!hasZeros) throw new Exception("Map has no empty spots");
        return manhattan;
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

        if (r == 1 && c == 0) {
            boolean debug = true;
        }

        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][];
        for (int i = 0; i < map.length; i++) {
            visited[i] = new boolean[map[i].length];
        }
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cell = queue.removeFirst();
            int r1 = cell[0];
            int c1 = cell[1];

            visited[r1][c1] = true;
            if (map[r1][c1] == 1) {
                return Math.abs(r - r1) + Math.abs(c - c1);
            } else {
                // left
                if (c1 - 1 >= 0 && !visited[r1][c1 - 1]) {
                    queue.add(new int[]{r1, c1 - 1});
                }
                // right
                if (c1 + 1 < map[r1].length && !visited[r1][c1 + 1]) {
                    queue.add(new int[]{r1, c1 + 1});
                }
                // top
                if (r1 - 1 >= 0 && !visited[r1 - 1][c1]) {
                    queue.add(new int[]{r1 - 1, c1});
                }
                // bottom
                if (r1 + 1 < map.length && !visited[r1 + 1][c1]) {
                    queue.add(new int[]{r1 + 1, c1});
                }
            }
        }
        return Integer.MAX_VALUE;
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
