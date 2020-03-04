package org.mlxxiv.kickstart.G2019;

import java.util.Scanner;

public class G2 {
    static long getMaxK(long[] laws, long M) {
        long k = -1;

        for (int i = 0; i <128 ; i++) {
            long xor = getXor(laws, i);
            if (i > k && xor <= M) k = i;
        }
        return k;
    }

    static long getXor(long[] laws, int k) {
        long xor = 0;
        for (long law: laws) {
            xor += law ^ k;
        }
        return xor;
    }

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            int N = Integer.parseInt(numbers[0]);
            long M = Long.parseLong(numbers[1]);

            long[] laws = new long[N];
            String[] rowString = scanner.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                laws[j] = Long.parseLong(rowString[j]);
            }

            long result = getMaxK(laws, M);

            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}

