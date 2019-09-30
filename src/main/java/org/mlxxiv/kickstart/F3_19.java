package org.mlxxiv.kickstart;

import java.util.Arrays;
import java.util.Scanner;

public class F3_19 {
    private static int action(int[] B, int[][] map) {
        int max = 0;
        for (int i = 0; i<=Math.pow(2, B.length); i++) {
            String[] lightPattern = Integer.toBinaryString(i).split("");
            int[] lighted = new int[lightPattern.length];

            for (int j = 0; j < lightPattern.length; j++) {
                lighted[j] = Integer.parseInt(lightPattern[j]);
            }
            int count = count(B, map, lighted);

            if (count > max) {
                System.out.println(Arrays.toString(lightPattern));
                max = count;
            }
        }
        return max;
    }
    private static int count(int[] B, int[][] map, int[] lighted) {
        int res = 0;
        boolean[] visited = new boolean[B.length];
        for (int i = lighted.length-1; i>=0; i--) {

            if (i >= visited.length) {
                continue;
            }
            int count = 0;
            if (!visited[i]) {
                count += B[i];
            }

            for (int j = 0; j < B.length; j++) {
                if (map[i][j] == 1 && !visited[i]) {
                    count += B[j];
                }
            }

            if (count > 0) {
                visited[i] = true;
                for (int k = 0; k < B.length; k++) {
                    if (map[i][k] == 1) {
                        visited[k] = true;
                    }
                }
            }

            log(String.format("village %d: score: %d", i+1, count));
            if (count > 0 ) {
                res += count;
            }
        }
        return res;
    }



    private static void log(String str) {
//        System.out.println(str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String row0 = scanner.nextLine();

            int V = Integer.parseInt(row0);

            String row[] = scanner.nextLine().split(" ");
            int[] B = new int[V];
            for (int j = 0; j < V; j++) {
                B[j] = Integer.parseInt(row[j]);
            }
            log(Arrays.toString(B));

            int[][] map = new int[V][];

            for (int j = 0; j < V-1; j++) {
                String[] connection = scanner.nextLine().split(" ");
                int v1 = Integer.parseInt(connection[0])-1;
                int v2 = Integer.parseInt(connection[1])-1;


                if (map[v1] == null) {
                    map[v1] = new int[V];
                }

                map[v1][v2] = 1;

                if (map[v2] == null) {
                    map[v2] = new int[V];
                }

                map[v2][v1] = 1;
            }

            for (int j = 0; j < map.length ; j++) {
                log(Arrays.toString(map[j]));
            }

            int result = action(B, map);
            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }

    }
}


