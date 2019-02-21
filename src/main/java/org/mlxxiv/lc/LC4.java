package org.mlxxiv.lc; /**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Difficulty: hard
 */

import java.util.Arrays;

public class LC4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, merged, 0, nums1.length);
        System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);

        Arrays.sort(merged);
        double mediana;
        if (merged.length % 2 == 0) {
            mediana = ((double)merged[merged.length/2] + merged[merged.length/2 - 1])/2;
        } else {
            mediana = merged[merged.length/2];
        }
        return mediana;
    }

    public static void main(String[] args) {

        System.out.println(LC4.findMedianSortedArrays(
                new int[] {1,2,3}, new int[] {3,4}
        ));
    }
}
