/**
 * https://www.hackerrank.com/challenges/the-grid-search
 * Difficulty: medium
 */
public class GridSearchSolution {
    public static final String YES = "YES";
    public static final String NO = "NO";

    static String gridSearch(String[] G, String[] P) {
        if (!validate(G, P)) {
            return NO;
        }
        int rowsG = G.length;
        int colsG = G[0].length();
        int rowsP = P.length;
        int colsP = P[0].length();

        for (int i=0; i<=rowsG-rowsP; i++) {
            for (int j=0; j<=colsG-colsP; j++) {
                if (match(i, j, G, P)) {
                    return YES;
                }
            }
        }
        return NO;
    }

    private static boolean validate(String[] G, String[] P) {
        if (G.length < P.length || G[0].length() < P[0].length()) {
            return false;
        }
        return true;
    }

    private static boolean match(int row, int col, String[] G, String[] P) {
        for (int i=0; i<P.length; i++) {
            for (int j=0; j<P[0].length(); j++) {
                int iG = i + row;
                int jG = j + col;
                if (P[i].charAt(j) != G[iG].charAt(jG)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] G = new String[] {
            "123412",
            "561212",
            "123634",
            "781288",
        };
        String[] P = new String[] {
            "12",
            "34",
        };
        System.out.println(gridSearch(G, P));
    }
}
