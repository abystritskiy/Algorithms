package org.mlxxiv.lc;

/**
 * https://leetcode.com/problems/reverse-integer/
 * Difficulty: easy
 */
public class LC7 {


    public static int reverse(int x) {
        if (x >= Math.pow(2, 31) - 1 || x <= Math.pow(-2, 31)) return 0;


        int sign = (x >= 0  ? 1 : -1);
        x = Math.abs(x);
        int maxPow = (int) Math.log10(x);
        int out = 0;
        int num = x;
        for (int i=maxPow; i>=0; i--) {
            int pow = (int) Math.pow(10, i);
            int digit = (num - num % pow) / pow;
            if (Math.pow(10, maxPow - i) > Integer.MAX_VALUE ||
                    Math.pow(10, maxPow - i) * digit  > Integer.MAX_VALUE ||
                    Integer.MAX_VALUE - out < Math.pow(10, maxPow-i) * digit) {
                return 0;
            }
            out += Math.pow(10, maxPow-i) * digit;
            num = num % pow;
        }
        return out * sign;
    }

    public static void main(String[] args){
        System.out.println(reverse(-2147483648));
    }


}
