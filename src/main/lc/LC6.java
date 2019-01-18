/**
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * Difficulty: medium
 */

import java.util.ArrayList;

public class LC6 {
    public String convert(String s, int numRows) {
        ArrayList<ArrayList<Character>> rows = new ArrayList<>();
        for (int j = 0; j < numRows; j++) {
            ArrayList<Character> row = new ArrayList<>();
            rows.add(row);
        }

        // 3 => 4
        // 4 => 6
        int period = 2 * numRows - 2;
        int first = 0;
        int last = numRows-1;

        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            int rmd = i % period;

            // last and first get only their remainder vals
            // rest remainder; remainder + shift

            if (rmd == 0 ) {
                rows.get(first).add(chr);
            } else if (rmd == last) {
                rows.get(last).add(chr);
            } else {

            }

        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < rows.size(); i++) {
            ArrayList<Character> row = rows.get(i);
            for (char chr : row) {
                out.append(chr);
            }
        }

        return out.toString();
    }
    // 01234567890123
    // PAYPALISHIRING
    //
    //

    // 01234567890123456
    // amazonhiringevent => aoretmzniigvnahne
    //
//0    P     I     N    0, 6, 12
//1    A   L S   I G    1, 5, 7, 11, 13
//2    Y A   H R        2 + (x0 + 2 * distanceToTheEnd)
//3    P     I          3

//
    // 0 4 8 12 16          i%4 == 0
    // 1 3 5 7 9 11 13 15   i%2 == 1
    // 2 6 10 14            i%4 == 2


    private static void main(String[] args) {
        LC6 obj = new LC6();
        System.out.println(obj.convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(obj.convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
    }
}
