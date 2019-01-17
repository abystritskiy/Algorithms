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
            int chr = str.charAt(pointer) + '0';
            if (chr != ' ' && (chr<48 || chr>57) && (chr != '-' || chr != '+')) {
                break;
            } else  if (chr == '-' && digits.size() == 0) {
                sign = -1;
            }  else  if (chr == '+' && digits.size() == 0) {
                sign = 1;
            } else if ((chr<48 || chr>57) && digits.size() != 0 ) {
                break;
            } else if (chr>=48 && chr<=57) {
                digits.add(Character.getNumericValue(chr));
            }
            pointer++;

        }
        for (int i=digits.size()-1; i>=0; i--) {
            double pow = Math.pow(10, digits.size()-1-i);
            double tmp = digits.get(i) * pow;
            total += tmp;




        }
        total = sign*total;
        return  total;
    }

    public static void main(String[] args) {
        LC8 obj = new LC8();
//        System.out.println(obj.myAtoi("-91283472332"));
        System.out.println(obj.myAtoi("+1"));


    }
}
