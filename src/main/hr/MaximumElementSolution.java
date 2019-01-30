import java.io.*;
import java.util.*;

public class MaximumElementSolution {

    public static int findMaxElement(int[][]  query) {
        // 1 - push element to stack
        // 2 - delete element from the top of stack
        // 3 - print max element
        return 10;
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] query = new int[n][2];
        for (int i=0; i<n; i++) {
            String[] line = scanner.nextLine().trim().split(" ");
            int command = Integer.parseInt(line[0]);
            int val = 0;
            if (line.length > 1) {
                val = Integer.parseInt(line[1]);
            }

            query[i] = new int[] {command, val};
        }

        findMaxElement(query);
    }

    private static class Node {

    }
}




