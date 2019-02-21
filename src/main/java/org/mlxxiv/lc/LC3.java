package org.mlxxiv.lc; /**
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Difficulty: medium
 *
 */
import  java.util.HashMap;
public class LC3 {
    protected HashMap<Character, Integer> map = new HashMap<>();
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int currentLen = 0;
        int i=0;
        while (i<s.length()) {
            char c = s.charAt(i);
            int p = containsChar(c, i);
            if (p == -1) {
                currentLen++;
                i++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 0;
                i = p+1;
            }
        }
        return Math.max(maxLen, currentLen);
    }

    private int containsChar(char c, int i) {
        int pos = -1;
        if (map.containsKey(c)) {
            pos = map.get(c);
            map.clear();
        } else {
            map.put(c, i);
        }
        return pos;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        LC3 lc3 = new LC3();
        int lengthOfLongestSubstring = lc3.lengthOfLongestSubstring(s);
        System.out.println(lengthOfLongestSubstring);
    }
}
