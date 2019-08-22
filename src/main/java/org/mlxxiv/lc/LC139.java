import java.util.*;

public class LC139 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] dictArr = new String[]{
                "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba"
        };

        List<String> dict = new ArrayList<>(Arrays.asList(dictArr));
        long start = System.nanoTime();
        System.out.println(!wordBreak(s, dict));
        System.out.println("Time: " + (System.nanoTime() - start) + " ns \n");

        List<String> dict1 = new ArrayList<>();
        dict1.add("leet");
        dict1.add("code");
        String s1 = "leetcode";
        long start1 = System.nanoTime();
        System.out.println(wordBreak(s1, dict1));
        System.out.println("Time: " + (System.nanoTime() - start1) + " ns \n");

        List<String> dict4 = new ArrayList<>();
        dict4.add("bc");
        dict4.add("cb");
        String s4 = "ccbb";
        long start4 = System.nanoTime();
        System.out.println(!wordBreak(s4, dict4));
        System.out.println("Time: " + (System.nanoTime() - start4) + " ns \n");


        List<String> dict2 = new ArrayList<>();
        dict2.add("apple");
        dict2.add("pen");
        String s2 = "applepenapple";
        long start2 = System.nanoTime();
        System.out.println(wordBreak(s2, dict2));
        System.out.println("Time: " + (System.nanoTime() - start2) + " ns \n");

        List<String> dict3 = new ArrayList<>();
        dict3.add("cats");
        dict3.add("and");
        dict3.add("dog");
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");
        String s3 = "catsandog";
        long start3 = System.nanoTime();
        System.out.println(!wordBreak(s3, dict3));
        System.out.println("Time: " + (System.nanoTime() - start3) + " ns \n");
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashMap<Character, HashSet<String>> dictionary = new HashMap<>();
        HashSet<Character> lettersInDict = new HashSet<>();

        for (String word : wordDict) {
            if (!s.contains(word)) {
                continue;
            }
            for (Character chrD : word.toCharArray()) {
                lettersInDict.add(chrD);
            }

            Character firstLetter = word.charAt(0);
            HashSet<String> startWithLetter = dictionary.getOrDefault(firstLetter, new HashSet<>());
            startWithLetter.add(word);
            dictionary.put(
                    firstLetter,
                    startWithLetter
            );
        }

        for (char charW : s.toCharArray()) {
            if (!lettersInDict.contains(charW)) {
                return false;
            }
        }
        HashMap<String, Boolean> cache = new HashMap<>();
        return wordBreak(s, dictionary, cache);
    }

    private static boolean wordBreak(String s, HashMap<Character, HashSet<String>> dictionary,
                                     HashMap<String, Boolean> cache) {
        if (s.length() == 0) {
            return true;
        }
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        Character firstLetter = s.charAt(0);
        HashSet<String> words = dictionary.get(firstLetter);
        if (words == null || words.isEmpty()) {
            return false;
        }

        for (String word : words) {
            if (s.length() >= word.length() && s.substring(0, word.length()).equals(word)) {
                String sCut = s.substring(word.length());
                if (wordBreak(sCut, dictionary, cache)) {
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }
}
