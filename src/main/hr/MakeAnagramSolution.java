/**
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/
 * Difficulty: easy
 */

import java.util.HashMap;
import java.util.Map;

public class MakeAnagramSolution {
    static int makeAnagram(String a, String b) {
        HashMap<Character, Integer> aMap = getFrequencyMap(a);
        HashMap<Character, Integer> bMap = getFrequencyMap(b);

        int total = 0;
        for (Map.Entry<Character, Integer> entry : aMap.entrySet()) {
            Character chr = entry.getKey();
            int countA = entry.getValue();
            int countB = bMap.getOrDefault(chr, 0);
            total += Math.abs(countA - countB);
            bMap.remove(chr);
        }

        for (Map.Entry<Character, Integer> entry : bMap.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    static HashMap<Character, Integer> getFrequencyMap(String str) {
        HashMap<Character, Integer> results = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character chr = str.charAt(i);
            int frequency = results.getOrDefault(chr, 0) + 1;
            results.put(chr, frequency);
        }
        return results;
    }

    public static void main(String[] args) {
        String a = "cde";
        String b = "abc";
        int res = makeAnagram(a, b);
        System.out.println(res);
    }
}
