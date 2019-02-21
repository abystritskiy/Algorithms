package org.mlxxiv.lc;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * Difficulty: medium
 */
public class LC11 {
    public int maxArea(int[] height) {
        int area = Math.min(height[0], height[height.length-1]) * (height.length-1 );
        int left = 0;
        int right = height.length-1;

        while (left < right) {
            if (height[left] < height[right]) {
                int areaTmp = height[left] * (right-left);
                if (areaTmp > area) {
                    area = areaTmp;
                }
                left++;
            } else {
                int areaTmp = height[right] * (right-left);
                if (areaTmp > area) {
                    area = areaTmp;
                }
                right--;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        LC11 obj = new LC11();
        System.out.println(obj.maxArea(new int[] {1,8,6,2,5,4,8,3,7}) == 49);
    }
}
