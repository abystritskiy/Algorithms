package org.mlxxiv.lc;

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

        while (end < s.length() || start<s.length()-t.length()) {
            char endChar = s.charAt(end);

            if (tMap.containsKey(endChar) && tMap.get(endChar) > 0) {
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
                char startChar = s.charAt(start);

                if(tMap.containsKey(startChar)){
                    tMap.put(startChar, tMap.get(startChar) + 1);
                    counter++;
                }

                start++;
            }
        }

        return ans;
    }

    private static int getScore(HashMap<Character, Integer> tMap) {
        int score = 0;
        for (Map.Entry<Character, Integer> entry: tMap.entrySet()) {
            score += entry.getValue();
        }
        return score;
    }


    public static void main(String[] args) {
        String s = "bba";
        String t = "ab";
        System.out.println(minWindow(s,t));
    }
}
