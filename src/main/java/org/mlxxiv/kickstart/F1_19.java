package org.mlxxiv.kickstart;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//public class Solution {
public class F1_19 {
    public static int action(int[] wall, int k) {
        if (validate(wall, k)) return 0;

        int res = wall.length - 1;
        HashSet<int[]> options = getOptions(wall, k);

        for (int[] option: options) {
            int steps = getStepsToDestination(wall, option);
            if (steps == 1) return steps;
            if (steps < res) res = steps;
        }
        return res;
    }

    private static HashSet<int[]> getOptions(int[] wall, int k) {
        HashSet<int[]> options = new HashSet<>();
        int n = wall.length;

        for (int i = 0; i < Math.pow(2, n); i++) {
            String binNum = Integer.toBinaryString(i);
            String mask = (new String(new char[wall.length - binNum.length()]).
                    replace("\0", "0")) + binNum;
            int[] sample = new int[wall.length];
            for (int j = 0; j < wall.length; j++) {
                if (mask.charAt(j) ==  '0') {
                    if (j == 0) {
                        sample[0] = wall[1];
                    } else {
                        sample[j] = wall[j-1];
                    }
                } else {
                    sample[j] = wall[j];
                }
            }
            if (validate(sample, k)) {
                options.add(sample);
            }
        }

        return options;
    }

    private static int getStepsToDestination(int[] orig, int[] dest) {
        int steps = 0;
        for (int i = 0; i < orig.length; i++) {
            if (orig[i] != dest[i]) {
                steps++;
            }
        }
        return steps;
    }

    private static boolean validate(int[] wall, int k) {
        int sections = 0;

        for (int i = 0; i < wall.length; i++) {
            if (i > 0 && wall[i] != wall[i - 1]) {
                sections++;
            }
            if (sections > k) {
                return false;
            }
        }

        return true;
    }

    private static void log(String str) {
//        System.out.println(str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");

            int size = Integer.parseInt(numbers[0]);
            int k = Integer.parseInt(numbers[1]);
            int[] wall = new int[size];
            String[] rowString = scanner.nextLine().split(" ");
            for (int j = 0; j < size; j++) {
                wall[j] = Integer.parseInt(rowString[j]);
            }
            log(Arrays.toString(wall));
            int result = action(wall, k);
            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}
