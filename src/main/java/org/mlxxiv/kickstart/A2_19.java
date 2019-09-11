package org.mlxxiv.kickstart;

import java.io.*;
import java.util.*;

public class A2_19 {

    private static int parcels(int[][] map) {
        return 5;
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
                System.out.println();
                map[j] = row;
            }
            int result = parcels(map);
            System.out.println(String.format("Case #%d: %d", i+1, result));
        }
    }
}
