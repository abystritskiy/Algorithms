import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SherlockValidString {
    public static void main(String[] args) {
        String input = "aabbcd";
        System.out.println(isValid(input));
    }
    public static String isValid(String s) {
        if (s.length() <= 2) {
            return "YES";
        }

        int[] count = new int[26];
        Arrays.fill(count, 0);

        for (char chr: s.toCharArray()) {
            count[chr-'a']++;
        }

        HashMap<Integer, Integer> variance = new HashMap<>();
        for (int i=0; i<count.length; i++) {
            if (count[i] != 0) {
                variance.merge(count[i], 1, Integer::sum);
            }
        }
        if (variance.size() > 2) {
            return "NO";
        } else if (variance.size() < 2) {
            return "YES";
        } else {
            Map.Entry<Integer, Integer> entry1 = null;
            Map.Entry<Integer, Integer> entry2 = null;
            for (Map.Entry<Integer, Integer> entry: variance.entrySet()) {
                if (entry1 == null) {
                    entry1 = entry;
                } else {
                    entry2 = entry;
                }
            }

            int maxFrequencyLettersCount, minFrequency, minFrequencyLettersCount;

            if (entry2.getValue() > entry1.getValue()) {
                maxFrequencyLettersCount = entry2.getKey();
                minFrequency = entry1.getValue();
                minFrequencyLettersCount = entry1.getKey();
            } else {
                maxFrequencyLettersCount = entry1.getKey();
                minFrequency = entry2.getValue();
                minFrequencyLettersCount = entry2.getKey();
            }

            if (minFrequency == 1 &&
                    (
                            Math.abs(maxFrequencyLettersCount-minFrequencyLettersCount)==1  || minFrequencyLettersCount == 1)
            ) {
                return "YES";
            }
            return "NO";
        }
    }

}
