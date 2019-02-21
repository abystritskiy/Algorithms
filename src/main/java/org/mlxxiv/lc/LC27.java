package org.mlxxiv.lc; /**
 * https://leetcode.com/problems/remove-element/
 * Difficulty: easy
 */

import java.util.Arrays;

public class LC27 {
    public int removeElement(int[] nums, int val) {
        Arrays.sort(nums);
        int first = -1;
        int last = -1;
        for (int i=0; i<nums.length; i++) { //5
            if (nums[i] == val && (i==0 || nums[i-1]<val)) {
                first = i;
            }
            if (nums[i] == val && (i==nums.length-1 || nums[i+1]>val)) {
                last = i;
                break;
            }
        }
        // first = 3; last = 5
        if (first == -1) {
            return nums.length;
        }
        int numsOfVal = last-first+1;
        int newPosition = last+1;
        while (newPosition < nums.length) {
            int tmp = nums[newPosition-numsOfVal];
            nums[newPosition-numsOfVal] = nums[newPosition];
            nums[newPosition] = tmp;
            newPosition++;
        }
        return nums.length-numsOfVal;
    }
}
