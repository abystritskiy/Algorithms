package org.mlxxiv.kickstart.H2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class H3_19 {
    static HashSet<Integer[]> visited = new HashSet<>();
    static boolean compute(final int[] A) {
        Integer[] elements = getNumber(A);
        if (isDivisible(elements)) return true;

        int[] indexes = new int[elements.length];

        int i = 0;
        while (i < elements.length) {
            if (indexes[i] < i) {
                int pos1 = indexes[i];
                int pos2 = i;
                if (
                    !(pos1 % 2 == 0 && pos2 % 2 == 0) &&
                    !(pos1 % 2 == 1 && pos2 % 2 == 1)
                ) {
                    swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                    if (visited.contains(visited)) {
                        continue;
                    } else {
                        visited.add(elements);
                    }
                    if (isDivisible(elements)) return true;
                }

                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        return false;
    }

    static boolean isDivisible(Integer[] ints) {
        int total = 0;
        for (int i = 0; i < ints.length; i++) {
            if (i % 2 == 0) {
                total += ints[i];
            } else {
                total -= ints[i];
            }
        }
        return total % 11 == 0;
    }

    static Integer[] getNumber(int[] A) {
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i]; j++) {
                num.add(i + 1);
            }
        }
        Integer[] res = new Integer[num.size()];
        return num.toArray(res);
    }

    static void swap(Integer[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] row = scanner.nextLine().split(" ");

            int[] A = new int[9];

            boolean isSingleDigit = false;
            int num = 0;
            for (int j = 0; j < 9; j++) {
                A[j] = Integer.parseInt(row[j]);
                if (isSingleDigit == false && A[j] != 0 && num == 0) {
                    isSingleDigit = true;
                    num = A[j];
                } else if (isSingleDigit == true && A[j] != 0) {
                    isSingleDigit = false;
                }
            }
            String res;

            if (isSingleDigit && num % 2 == 0) {
                res = "YES";
            } else {
                res = compute(A) ? "YES" : "NO";
            }
            System.out.println(String.format("Case #%d: %s", i + 1, res));
        }
    }


}
