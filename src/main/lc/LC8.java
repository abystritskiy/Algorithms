import java.util.ArrayList;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 * Difficulty: medium
 */
public class LC8 {
    public int myAtoi(String str) {
        int sign = 1;
        int total = 0;
        ArrayList<Integer> digits = new ArrayList<>();

        int pointer = 0;
        while(pointer<str.length()) {
            char chr = str.charAt(pointer);
            if (chr != ' ' && (chr<48 || chr>57) && chr != '-') {
                break;
            } else  if (chr == '-' && digits.size() == 0) {
                sign = -1;
            } else if ((chr<48 || chr>57) && digits.size() != 0 ) {
                break;
            } else if (chr>=48 && chr<=57) {
                digits.add(Character.getNumericValue(chr));
            }
            pointer++;

        }
        for (int i=digits.size()-1; i>=0; i--) {
            int pow = digits.size()-1-i;
            double tmp = digits.get(i) * Math.pow(10, pow);
            total += tmp;

        }
        return sign * total;
    }

    public static void main(String[] args) {
        LC8 obj = new LC8();
        System.out.println(obj.myAtoi("0")); // 0
        System.out.println(obj.myAtoi("    -42")); // -42
        System.out.println(obj.myAtoi("words and 987 and going")); //987

    }
}
