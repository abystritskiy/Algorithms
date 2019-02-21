package org.mlxxiv.lc;

/**
 * https://leetcode.com/problems/palindrome-number/submissions/
 * Difficulty: easy
 */
public class LC9 {
    public boolean isPalindrome(int x) {
        String xStr = String.valueOf(x);

        for (int i=0; i<=xStr.length()/2; i++) {
            char frontChr = xStr.charAt(i);
            char backChr = xStr.charAt(xStr.length()-1-i);
            if (frontChr != backChr) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        LC9 obj = new LC9();
        System.out.println(obj.isPalindrome(121) == true);
        System.out.println(obj.isPalindrome(-121) == false);
        System.out.println(obj.isPalindrome(0) == true);
        System.out.println(obj.isPalindrome(2222) == true);
        System.out.println(obj.isPalindrome(2122) == false);
    }
}
