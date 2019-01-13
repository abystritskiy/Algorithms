/**
 * https://www.hackerrank.com/challenges/weighted-uniform-string
 * Difficulty: easy
 *
 */

import java.util.Arrays;
import java.util.HashMap;

public class WeightedUniformStringSolution {

    static String[] weightedUniformStrings(String s, int[] queries) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        for (int i=0; i<alphabet.length; i++) {
            alphabetMap.put(alphabet[i], i+1);
        }

        HashMap<Integer, Boolean> weights = new HashMap<>();
        for (int j=0; j<queries.length; j++) {
            weights.put(queries[j], false);
        }

        int score = 0;
        char prevChr = 0;

        for (int k=0; k<s.length(); k++) {
            char chr = s.charAt(k);
            if (chr == prevChr)  {
                score += alphabetMap.get(chr);
            } else {
                score = alphabetMap.get(chr);
            }
            prevChr = chr;
            if (k == s.length()-1) {
                if (weights.containsKey(score)){
                    weights.put(score, true);
                }
            }

            if (weights.containsKey(score)){
                weights.put(score, true);
            }
        }

        String[] out = new String[queries.length];
        for (int m=0; m<queries.length; m++) {
            out[m] = (weights.get(queries[m]) ? "Yes" : "No");
        }
        return out;
    }

    public static void main(String[] args) {

        String[] results1 = weightedUniformStrings(
                "aaabbbbcccddd",
                new int[] {9,7,8,12,5}
        );
        System.out.println(Arrays.toString(results1));
        /*
           Yes
           No
           Yes
           Yes
           No
        */

        String[] results2 = weightedUniformStrings(
                "abccddde",
                new int[] {1,3,12,5,9,10}
        );
        System.out.println(Arrays.toString(results2));
        /*
            Yes
            Yes
            Yes
            Yes
            No
            No
        */
    }
}
