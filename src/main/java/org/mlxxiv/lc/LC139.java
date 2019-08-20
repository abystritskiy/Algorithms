package org.mlxxiv.lc;

import java.util.ArrayList;
import java.util.List;

public class LC139 {
    public static void main(String[] args) {
        List<String> dict1 = new ArrayList<>();
        dict1.add("leet");
        dict1.add("code");
        String s1 = "leetcode";
        boolean res1 = true;
        System.out.println(wordBreak(s1, dict1) == res1);


        List<String> dict2 = new ArrayList<>();
        dict2.add("apple");
        dict2.add("pen");
        String s2 = "applepenapple";
        boolean res2 = true;
        System.out.println(wordBreak(s2, dict2) == res2);

        List<String> dict3 = new ArrayList<>();
        dict3.add("cats");
        dict3.add("and");
        dict3.add("dog");
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        String s3 = "catsandog";
        boolean res3 = false;
        System.out.println(wordBreak(s3, dict3) == res3);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakRecursive(s, wordDict);
    }

    private static boolean wordBreakRecursive(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (String word: wordDict) {
            int pos = s.indexOf(word);
            if (pos != -1) {
                String sCut = s.substring(0, pos) + s.substring(pos+word.length());
                if (wordBreakRecursive(sCut, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
}
