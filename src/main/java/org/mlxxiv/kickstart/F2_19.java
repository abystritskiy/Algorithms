package org.mlxxiv.kickstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class F2_19 {
    private static int action(ArrayList<HashSet<Integer>> skillSet) {
        int pairs = 0;

        for (int i = 0; i < skillSet.size(); i++) {
            for (int j =0; j < skillSet.size(); j++) {
                if (i == j) continue;
                if (canTeach(skillSet.get(i), skillSet.get(j))) pairs++;
            }
        }
        return pairs;
    }

    private static boolean canTeach(HashSet<Integer> a,  HashSet<Integer> b) {
        if (a.size() > b.size()) return true;
        for (Integer skill: a) {
            if (!b.contains(skill)) return true;
        }
        return false;
    }

    private static void log(String str) {
//        System.out.println(str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");

            int N = Integer.parseInt(numbers[0]);

            ArrayList<HashSet<Integer>> skillSet = new ArrayList<HashSet<Integer>>();
            for (int j = 0; j < N; j++) {
                String[] rowString = scanner.nextLine().split(" ");

                HashSet<Integer> skills = new HashSet<>();
                for (int k = 0; k < Integer.parseInt(rowString[0]); k++) {
                    skills.add(Integer.parseInt(rowString[k+1]));
                }

                log(skills.toString());
                skillSet.add(skills);
            }

            int result = action(skillSet);
            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}
