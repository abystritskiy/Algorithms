/**
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * Difficulty: medium
 */

import java.util.ArrayList;

public class LC6 {
    public String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }
        ArrayList<ArrayList<Character>> rows = new ArrayList<>();
        for (int j = 0; j < numRows; j++) {
            ArrayList<Character> row = new ArrayList<>();
            rows.add(row);
        }

        int period = 2 * numRows - 2;
        int first = 0;
        int last = numRows-1;

        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            int rmd = i % period;

            if (rmd == 0 ) {
                rows.get(first).add(chr);
            } else if (rmd == last) {
                rows.get(last).add(chr);
            } else {
                if (rmd < numRows) {
                    rows.get(rmd).add(chr);
                } else {
                    rows.get(2*(numRows-1) - rmd).add(chr);
                }
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


    private static void main(String[] args) {
        LC6 obj = new LC6();
        System.out.println(obj.convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(obj.convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
    }
}
