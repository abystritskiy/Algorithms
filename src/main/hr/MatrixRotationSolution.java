/**
 * https://www.hackerrank.com/challenges/matrix-rotation-algo/
 * Difficulty: hard
 */

import java.util.ArrayList;
import java.util.List;

public class MatrixRotationSolution {
    protected static List<List<Integer>> matrix;

    //all the magic goes here
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        MatrixRotationSolution.matrix = matrix;
        List<List<Integer>> out = new ArrayList<>();
        for (int y = 0; y < matrix.size(); y++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int x = 0; x < matrix.get(y).size(); x++) {
                row.add(matrix.get(y).get(x));
            }
            out.add(row);
        }

        int height = matrix.size();
        int width = matrix.get(0).size();
        int maxDepth = Math.min(height, width) / 2;
        for (int d = 0; d < maxDepth; d++) {
            int m = height - 2 * d;
            int n = width - 2 * d;
            int L = 2 * (n + m - 2);
            int shift = r % L;

            for (int s = 0; s < L; s++) {
                int s1 = (s + shift) % L;
                ArrayList<Integer> p0 = getCoordinatesFromThePosition(d, s);
                ArrayList<Integer> p1 = getCoordinatesFromThePosition(d, s1);

                int newValue = matrix.get(p0.get(0)).get(p0.get(1));
                out.get(p1.get(0)).set(p1.get(1), newValue);
            }
        }
        printResult(out);
    }

    /**
     * Get y,x coordinate by shifting it on the "outer contour of virtual rectangle"
     * by Shift position from 0,0 if to rotate anti-clockwise. E.g 1,0 => shift 1;
     * 3, 1=> shift 4 (for matrix 4x5)
     *
     * @param depth
     * @param shift
     * @return
     */
    private static ArrayList<Integer> getCoordinatesFromThePosition(int depth, int shift) {

        int m = matrix.size() - 2 * depth; //y-coord
        int n = matrix.get(0).size() - 2 * depth; // x-coord
        int length = 2 * (n + m - 2);
        int x, y;
        if (shift < m ) {
            x = 0;
            y = shift;
        } else if (shift >= m && shift <= n + m - 2) {
            x = shift - m + 1;
            y = m - 1;
        } else if (shift > n + m - 2 && shift < 2 * m + n - 3) {
            x = n - 1;
            y = m - 1 - (shift - (n + m - 2));
        } else {
            x = length - shift;
            y = 0;
        }

        ArrayList<Integer> results = new ArrayList<>();
        results.add(y + depth);
        results.add(x + depth);
        return results;
    }

    /**
     * Print result as a coma-spaced table
     *
     * @param matrix
     */
    private static void printResult(List<List<Integer>> matrix) {
        for (int y = 0; y < matrix.size(); y++) {
            List<String> row = new ArrayList<>();
            for (int x = 0; x < matrix.get(y).size(); x++) {
                row.add(matrix.get(y).get(x).toString());
            }
            System.out.println(String.join(" ", row));
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int y = 0; y < 4; y++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int x = 0; x < 4; x++) {
                row.add(4 * y + x + 1);
            }
            matrix.add(row);
        }

        MatrixRotationSolution.matrix = matrix;
        printResult(matrix);

        System.out.println("\n");
        matrixRotation(matrix, 1);



        //rotation 1; expected:
//        2 3 4 8
//        1 7 11 12
//        5 6 10 16
//        9 13 14 15
    }
}
