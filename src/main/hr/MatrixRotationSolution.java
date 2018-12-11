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
        for (int y=0; y<matrix.size(); y++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int x=0; x<matrix.get(y).size(); x++) {
                row.add(matrix.get(y).get(x));
            }
            out.add(row);
        }

        int height = matrix.size();
        int width = matrix.get(0).size();
        int maxDepth = Math.min(height,width)/2;
        for (int d=0; d < maxDepth; d++) {
            int m = height - 2*d;
            int n = width - 2*d;
            int L = 2*(n+m-2);
            int shift = r%L;

            for (int s=0; s<L; s++) {
                int s1 = s + shift;
                ArrayList<Integer> p0 = getCoordinatesFromThePosition(d, s, m, n);
                ArrayList<Integer> p1 = getCoordinatesFromThePosition(d, s1, m, n);

                //update p1.x, p1.y value from p0.x, p0.y
            }
        }
        printResult(out);
    }

    /**
     * Get y,x coordinate by shifting it on the "outer contour of virtual rectangle"
     * by Shift position from 0,0 if to rotate anti-clockwise. E.g 1,0 => shift 1;
     * 3, 1=> shift 4 (for matrix 4x5)
     * @param depth
     * @param shift
     * @param w
     * @param h
     * @return
     */
    private static ArrayList<Integer> getCoordinatesFromThePosition(int depth, int shift, int w, int h) {
        ArrayList<Integer> results = new ArrayList<>();

        return results;
    }

    /**
     * Print result as a coma-spaced table
     *
     * @param matrix
     */
    private static void printResult(List<List<Integer>> matrix) {
        for (int y=0; y<matrix.size(); y++) {
            List<String> row = new ArrayList<>();
            for (int x=0; x<matrix.get(y).size(); x++) {
                row.add(matrix.get(y).get(x).toString());
            }
            System.out.println(String.join(" ", row));
        }
    }
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int y=0; y<4; y++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int x=0; x<4; x++) {
                row.add(4*y + x + 1);
            }
            matrix.add(row);
        }
        printResult(matrix);

        //rotation 1; expected:
//        2 3 4 8
//        1 7 11 12
//        5 6 10 16
//        9 13 14 15
    }
}
