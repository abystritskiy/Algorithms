package org.mlxxiv.lc; /**
 *
 * https://leetcode.com/problems/two-sum/
 * Difficulty: easy
 *
 */

import java.util.*;

class LC1 {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        for (int j = 0; j < nums.length; j++) {
            int x = nums[j];
            int y = target - x;
            if (map.containsKey(y) && map.get(y) != null && map.get(y).size() > 0) {
                int idx0 = map.get(x).get(0);
                ArrayList<Integer> ySet = map.get(y);
                if (ySet != null && ySet.size() > 0) {
                    for (int k = 0; k < ySet.size(); k++) {
                        if (ySet.get(k) != idx0) {
                            int[] res = new int[]{idx0, ySet.get(k)};
                            map.get(y).remove(0);
                            map.get(x).remove(0);
                            return res;
                        }
                    }
                }
            }
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        int[] res = LC1.twoSum(new int[] {3,2,4}, 6);
        System.out.println(Arrays.toString(res));
    }
}