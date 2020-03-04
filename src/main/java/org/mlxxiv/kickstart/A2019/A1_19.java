package org.mlxxiv.kickstart.A2019;

import java.io.*;
import java.util.*;

public class A1_19 {
    private static int calcMinHours(int p, int[] skills) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(skills);
        int i = p - 1;

        int sum = 0;
        while (i < skills.length) {
            int hours = 0;
            int max = skills[i];
            if (i == p - 1) {
                for (int j = i - p + 1; j < i; j++) {
                    sum += skills[j];
                }
            } else {
                int prevMax = skills[i-1];
                int prevFirst = skills[i-p];
                sum = sum - prevFirst + prevMax;
            }

            hours = max * (p-1) - sum;

            if (hours < result) {
                result = hours;
            }
            i++;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            String[] row = scanner.nextLine().split(" ");

            int n = Integer.parseInt(numbers[0]);
            int p = Integer.parseInt(numbers[1]);
            int[] skills = new int[n];
            for (int j = 0; j < n; j++) {
                skills[j] = Integer.parseInt(row[j]);
            }
            int result = calcMinHours(p, skills);
            System.out.println(String.format("Case #%d: %d", i+1, result));
        }
    }
}
