import java.util.HashMap;
import java.util.Map;

public class LC76 {
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (int i=0; i<t.length(); i++) {
            char chrI = t.charAt(i);
            int iCount = tMap.getOrDefault(chrI, 0);
            tMap.put(chrI, iCount+1);
        }

        int start = 0, end =0, len = Integer.MAX_VALUE;
        int counter = getScore(tMap);

        String ans = "";

        while (end < s.length()) {
            char endChar = s.charAt(end);

            if (tMap.containsKey(endChar)) {
                int newValue = tMap.get(endChar)-1;
                tMap.put(endChar, newValue);

                counter--;
            }
            end++;

            while (counter == 0) {
                if (end-start < len) {
                    len = end - start;
                    ans = s.substring(start, end);
                }
                // begin char could be in table or not,
                // if not then good for us, it was a wasteful char and we shortened the previously found substring.

                // if found in table increment count in table, as we are leaving it out of window and moving ahead,
                // so it would no longer be a part of the substring marked by begin-end window
                // table only has count of chars required to make the present substring a valid candidate
                // if the count goes above zero means that the current window is missing one char to be an answer candidate

                char startchar = s.charAt(start);

                if(tMap.getOrDefault(startchar, 0 ) == 1){
                    tMap.put(startchar, tMap.get(startchar) + 1);
                    counter++;
                }

                start++;
            }
        }

        return ans;
    }

    public static int getScore(HashMap<Character, Integer> tMap) {
        int score = 0;
        for (Map.Entry<Character, Integer> entry: tMap.entrySet()) {
            score += entry.getValue();
        }
        return score;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
    }

    static int shiftedArrSearch(int[] shiftArr, int num) {
        int start = 0;
        int end = shiftArr.length - 1;
        //[9, 12, 17, 2, 4, 5] // 2 => out = 3;
        // start = 2;
        // end = 5;
        // mid = 3; mid element = 17
        while (start < end) {
            int mid = (end + start) / 2;
            int midElement = shiftArr[mid];

            if (midElement == num) {
                return mid;
            }
            if (num < mid && num >= shiftArr[start]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return -1;
    }
}
