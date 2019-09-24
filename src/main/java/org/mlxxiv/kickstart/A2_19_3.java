package org.mlxxiv.kickstart;

import java.util.Arrays;
import java.util.Scanner;

public class A2_19_3 {

    private static int parcels(int[][] map) {
        return 0;
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
