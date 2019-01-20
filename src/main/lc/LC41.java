import java.util.HashSet;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * Difficulty: hard
 */
public class LC41 {
    public static int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            set.add(nums[i]);
        }

        for (int j=1; j<Integer.MAX_VALUE; j++) {
            if (!set.contains(j)) {
                return j;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int res = firstMissingPositive(new int[] {1,-1,3, 4});
        System.out.println(res);
    }
}
