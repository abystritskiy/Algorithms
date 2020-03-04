package org.mlxxiv.kickstart.G2019;

import java.util.Scanner;

public class G1 {
    //public class Solution {
    private static int getPagesTotal(int N, boolean[] pagesTorn, int[] readers) {
        int total = 0;
        int i = 0;
        for (int factor : readers) {
            int read = 0;


            for (int nextPage = factor; nextPage <= N; nextPage += factor) {
                i++;
                if (!pagesTorn[nextPage]) {
                    read++;
                }
            }
            total += read;
        }
        System.out.println("Pages: " + N + "; Readers num: " + readers.length + "; Operations: " + i);
        return total;

    }

    private static void log(String str) {
        System.out.println(str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            int N = Integer.parseInt(numbers[0]);
            int M = Integer.parseInt(numbers[1]);
            int Q = Integer.parseInt(numbers[2]);

            boolean[] P = new boolean[N + 1];
            String[] rowString = scanner.nextLine().split(" ");
            for (int k = 0; k < M; k++) {
                int pageNum = Integer.parseInt(rowString[k]);
                P[pageNum] = true;
            }
            int[] R = new int[Q];
            String[] rowString2 = scanner.nextLine().split(" ");
            for (int j = 0; j < Q; j++) {
                R[j] = Integer.parseInt(rowString2[j]);
            }

            int result = getPagesTotal(N, P, R);

            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}

