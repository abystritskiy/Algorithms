package org.mlxxiv.kickstart.H2019;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class H2_19 {
    private static int calcHScore(final int[] A, int length) {
        int[] copy = new int[length];

        System.arraycopy(A, 0, copy, 0, length);
        Arrays.sort(copy);

        int h = 0;
        for (int i = 0; i < copy.length; i++) {
            int val = copy[i];
            int remained = copy.length - i;
            int tmp = Math.min(val, remained);
            if (tmp > h) h = tmp;
        }
        return h;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            String[] row = scanner.nextLine().split(" ");

            int[] A = new int[n];
            StringBuffer result = new StringBuffer();
            for (int j = 0; j < n; j++) {
                A[j] = Integer.parseInt(row[j]);
                result.append(calcHScore(A, j+1) + " ");
            }
            System.out.println(String.format("Case #%d: %s", i+1, result));
        }
    }
}
