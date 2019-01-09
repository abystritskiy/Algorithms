/**
 * https://www.hackerrank.com/challenges/two-characters/
 * Difficulty: easy
 *
 * Time Complexity: n^2 (worst case)
 * Space Complexity: constant
 */

import java.util.HashSet;

public class TwoCharactersSolution {
    static int alternate(String s) {
        HashSet<Character> set = new HashSet<>();

        // Make a list of unique characters
        for (int i=0; i<s.length(); i++) {
            set.add(s.charAt(i));
        }

        // Convert a set to an array to preserve order
        char[] chars = new char[set.size()];
        int k = 0;
        for (char chr: set) {
            chars[k++] = chr;
        }

        // Brute force trough all possible combination and find max length
        int max = 0;
        for (int i=0; i < chars.length; i++) {
            for (int j=i+1; j < chars.length; j++) {
                int res = getMaxLength(s ,chars[i], chars[j]);
                max = Math.max(max, res);
            }
        }

        return max;
    }

    private static int getMaxLength(String str, char first, char second) {
        int max = 0;
        char prev = 0;
        for (int i=0; i<str.length(); i++) {
            char chr = str.charAt(i);
            if (chr != first && chr != second) {
                continue;
            }
            if (chr == prev) {
                return -1;
            }
            prev = chr;
            max++;
        }
        return max;
    }

    public static void main (String[] args) {
        String str = "ebeabefabe";
        int res =  alternate(str);
        System.out.println(res);
    }
}
