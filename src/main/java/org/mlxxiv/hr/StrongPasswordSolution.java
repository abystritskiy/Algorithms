/**
 * https://www.hackerrank.com/challenges/strong-password
 * Difficulty: easy
 */
public class StrongPasswordSolution {

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong

        char[] numbers = "0123456789".toCharArray();
        char[] lower_case = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] special_characters = "!@#$%^&*()-+".toCharArray();

        int numsUsed = 0;
        int lowUsed = 0;
        int upUsed = 0;
        int specialUsed = 0;

        for (int i =0; i < n; i++) {
            char chr = password.charAt(i);
            if (inArray(chr, numbers)) {
                numsUsed++;
            } else if (inArray(chr, lower_case)) {
                lowUsed++;
            } else  if (inArray(chr, upper_case)) {
                upUsed++;
            } else if (inArray(chr, special_characters)) {
                specialUsed++;
            }
        }

        int res = 0;
        if (numsUsed == 0) {
            res += 1;
        }
        if (lowUsed == 0) {
            res += 1;
        }
        if (upUsed == 0) {
            res += 1;
        }
        if (specialUsed == 0) {
            res += 1;
        }

        if (password.length() + res < 6) {
            res += 6 - password.length() - res;
        }

        return res;
    }



    private static boolean inArray(char chr, char[] list) {
        for (int i=0; i<list.length; i++) {
            if (list[i] == chr) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(minimumNumber(11, "HackerRan#1"));
    }
}
