/**
 * https://leetcode.com/problems/regular-expression-matching/
 * Difficulty: hard
 */
public class LC10 {
    public boolean isMatch(String s, String p) {
        // mississippi
        //i^

        // mis*is*p*.
        //j^

        int i = 0;
        int j = 0;

        while (true) {
            if (i == s.length() && j == p.length()) {
                return true;
            }

            if (i == s.length() && j != p.length()) {

            }

            char cS = s.charAt(i);
            char cP = p.charAt(j);

            if (cS == cP || cP == '.') {
                i++;
                j++;
                continue;
            }

            if (cP == '*') {
                break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC10 obj = new LC10();
        System.out.println(obj.isMatch("aa", "a") == false);
        System.out.println(obj.isMatch("aa", "a*") == true);
        System.out.println(obj.isMatch("aab", "c*a*b") == true);
        System.out.println(obj.isMatch("mississippi", "mis*is*p*.") == false);
    }
}
