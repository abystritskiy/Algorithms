package org.mlxxiv.kickstart.G2019;

import java.util.HashSet;
import java.util.Scanner;

public class G3 {

    private static int getResults(int[] A, int[] B, int H) {
        int happyAssignmentsNum = 0;
        int n = A.length;
        HashSet<String> bMap = new HashSet<>();
        HashSet<String> aMap = new HashSet<>();

        for (int i = 0; i < Math.pow(2, n); i++) {
            String binNum = Integer.toBinaryString(i);
            String mask = (new String(new char[n - binNum.length()]).
                    replace("\0", "0")) + binNum;
            if (isHappy(mask, A, H)) aMap.add(mask);
            if (isHappy(mask, B, H)) bMap.add(mask);
        }
        for (String aRec: aMap) {
            for (String bRec: bMap) {
                if (isShiftValid(aRec, bRec)) happyAssignmentsNum++;
            }
        }
        return happyAssignmentsNum;
    }

    private static boolean isShiftValid(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            if (!(A.charAt(i) == '1' || B.charAt(i) == '1')) return false;
        }
        return true;
    }

    private static boolean isHappy(String shifts, int[] pts, int H) {
        int ptsGained = 0;
        for (int i = 0; i < shifts.length(); i++) {
            ptsGained += (shifts.charAt(i) == '1' ? pts[i] : 0);
            if (ptsGained >= H) return true;
        }
        return false;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < t; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            int N = Integer.parseInt(numbers[0]);
            int H = Integer.parseInt(numbers[1]);

            int[] A = new int[N];
            String[] rowStringA = scanner.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                A[j] = Integer.parseInt(rowStringA[j]);
            }
            int[] B = new int[N];
            String[] rowStringB = scanner.nextLine().split(" ");
            for (int j1 = 0; j1 < N; j1++) {
                B[j1] = Integer.parseInt(rowStringB[j1]);
            }

            int result = getResults(A, B, H);

            System.out.println(String.format("Case #%d: %d", i + 1, result));
        }
    }
}
