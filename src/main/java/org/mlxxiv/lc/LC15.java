package org.mlxxiv.lc; /**
 * https://leetcode.com/problems/3sum/
 *
 * Difficulty: medium
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LC15 {
    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> results = new ArrayList<>();
        HashSet<ArrayList<Integer>> resultsSet = new HashSet<>();
        int n = nums.length;
        Arrays.sort(nums);

        for (int i=0; i<n-2; i++) {
            int start = i+1;
            int end = n-1;
            int a = nums[i];
            while (start < end) {
                int b = nums[start];
                int c = nums[end];
                if (a+b+c == 0) {
                    ArrayList<Integer> element = new ArrayList<Integer>();
                    element.add(a);
                    element.add(b);
                    element.add(c);
                    resultsSet.add(element);

                    start = start + 1;
                    end = end - 1;
                } else if (a+b+c > 0) {
                    end = end - 1;
                } else {
                    start = start + 1;
                }
            }
        }

        for (ArrayList<Integer> set: resultsSet) {
            results.add(set);
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        System.out.println((new LC15()).threeSum(nums));
    }

}
