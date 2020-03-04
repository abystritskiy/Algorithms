package org.mlxxiv.kickstart.A2019;

import java.io.*;
import java.util.*;

public class A2_19_1 {

    private static int parcels(int[][] map) {
        List<int[]> maxManDistanceCells = getMaxDistanceCells(map);
        maxManDistanceCells.remove(maxManDistanceCells.size()-1);

        int minMaxDistance = Integer.MAX_VALUE;
        for (int[] cell: maxManDistanceCells) {
            map[cell[0]][cell[1]] = 1;
            List<int[]> distances = getMaxDistanceCells(map);
            int dist = distances.get(distances.size()-1)[0];
            if (dist < minMaxDistance) {
                minMaxDistance = dist;
            }
            map[cell[0]][cell[1]] = 0;
        }
        return minMaxDistance;
    }

    private static List<int[]> getMaxDistanceCells(int[][] map) {
        int maxManDistance = 0;
        ArrayList<int[]> maxManDistanceCoord = new ArrayList<>();

        int[][] manMap = new int[map.length][];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (manMap[r] == null) {
                    manMap[r] = new int[map[r].length];
                }
                int manDistance = calcManDistance(r, c, map);
                manMap[r][c] = manDistance;

                if (manDistance > maxManDistance) {
                    maxManDistance = manDistance;
                    maxManDistanceCoord = new ArrayList<>();
                    maxManDistanceCoord.add(new int[]{r, c});
                } else if (manDistance == maxManDistance) {
                    maxManDistanceCoord.add(new int[]{r, c});
                }
            }
            System.out.println(Arrays.toString(manMap[r]));
        }

        System.out.println();
        maxManDistanceCoord.add(new int[] {maxManDistance, 0});
        return maxManDistanceCoord;
    }

    public static int calcManDistance(int r, int c, int[][] map) {
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

    public static int[] getClosestCell(int r, int c, int step, int[][] map) {

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
                System.out.println(Arrays.toString(row));
                map[j] = row;
            }
            System.out.println();
            int result = parcels(map);
            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}
