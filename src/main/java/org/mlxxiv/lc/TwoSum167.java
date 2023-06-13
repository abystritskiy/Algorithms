package org.mlxxiv.lc;

public class TwoSum167 {
    public static void main(String[] args) {
        int[] numbers = new int[] {2,3,4};
        int[] coord = twoSum(numbers, 6);
        System.out.println(coord[0] + "," + coord[1]);
    }
    public static int[] twoSum(int[] numbers, int target) {
        for (int i=0; i< numbers.length; i++) {
            int value = target - numbers[i];
            int binarySearch = binarySearch(numbers, i+1, numbers.length-1, value);
            if (binarySearch != -1) {
                return new int[] {i, binarySearch};
            }
        }
        return new int[]{};
    }

    // 2, 7, 11, 15
    // start=0
    // end = 3
    // target = 9
    public static int binarySearch(int[] numbers, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int middle = start + (end-start) / 2;
        int val = numbers[middle];

        if (val == target) {
            return middle;
        }

        if (val > target) {
            return binarySearch(numbers, start, middle-1, target);
        } else {
            return binarySearch(numbers, middle+1, end, target);
        }
    }
}
