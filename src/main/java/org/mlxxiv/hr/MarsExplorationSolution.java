/**
 * https://www.hackerrank.com/challenges/mars-exploration/
 * Difficulty: easy
 */
public class MarsExplorationSolution {

    public static int marsExploration(String s) {
        int total = 0;
        for (int i=0; i<s.length(); i++) {
            char chr = s.charAt(i);

            if ((i%3 == 0 && chr != 'S') ||
                (i%3 == 1 && chr != 'O') ||
                (i%3 == 2 && chr != 'S')) {
                total++;
            }
        }
        return total;
    }


    public static void main(String[] args) {
        System.out.println(marsExploration("SOSSPSSQSSOR"));
    }
}
