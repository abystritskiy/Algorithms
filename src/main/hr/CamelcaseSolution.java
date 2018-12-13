/**
 * https://www.hackerrank.com/challenges/camelcase/problem
 * Difficulty: easy
 */
public class CamelcaseSolution {
    static int camelcase(String s) {
        int total = 0;
        if (s.length() > 0) {
            total++;
            for (int i = 1; i < s.length(); i++) {
                Character charUp = Character.toUpperCase(s.charAt(i));
                if (charUp.equals(s.charAt(i))) {
                    total++;
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String test = "merryHadaALittleLamb";
        System.out.println(camelcase(test));
    }
}
