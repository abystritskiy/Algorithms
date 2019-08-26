package org.mlxxiv.lc;

import java.util.LinkedList;

public class LC42 {
    public static void main(String[] args) {
        int[] test1 = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(trap(test1) == 6);
        int[] test2 = new int[] {2,0,2};
//        System.out.println(trap(test2) == 2);
        int[] test3 = new int[] {5,4,1,2};
        System.out.println(trap(test3) == 1);
    }

    public static int trap(int[] arr) {
        if (arr.length < 3) return 0;
        int total = 0;
        int[] chunk  = new int[] {0, arr.length-1};
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(chunk);

        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            int left = element[0];
            int right = element[1];

            int topLeft = element[left];
            int topRight = element[left]+1;
            for (int i = left+2; i<=right; i++) {
                if (arr[i] >= Math.min(arr[topLeft], arr[topRight])) {
                    if (arr[topLeft] < arr[topRight]) {
                        topLeft = topRight;
                    }
                    topRight = i;
                }

            }

            total += getAmount(arr, topLeft, topRight);

            if (topLeft >= 2) {
                queue.add(new int[] {0, topLeft});
            }
            if (right-topRight>=2) {
                queue.add(new int[] {topRight, right});
            }

        }
        return total;
    }

    public static int getAmount(int[] arr, int left, int right ) {
        int amount = 0;
        if (right - left < 2) return 0;
        for (int i = left+1; i<right; i++) {
            amount += Math.min(arr[left], arr[right]) - arr[i];
        }
        return amount;
    }
}
