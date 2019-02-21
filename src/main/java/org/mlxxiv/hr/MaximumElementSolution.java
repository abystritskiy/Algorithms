import java.io.*;
import java.util.*;

public class MaximumElementSolution {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        MaxNode maxNode = null;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().trim().split(" ");
            int command = Integer.parseInt(line[0]);
            int val = 0;
            if (line.length > 1) {
                val = Integer.parseInt(line[1]);
            }

            if (command == 1) {
                stack.push(val);
                if (maxNode == null || maxNode.val < val) {
                    MaxNode newMax = new MaxNode(val);
                    newMax.previousMax = maxNode;
                    maxNode = newMax;
                }
            } else if (command == 2) {
                int top = stack.pop();
                if (top == maxNode.val) {
                    MaxNode prevMax = maxNode.previousMax;
                    maxNode = prevMax;
                }
            } else {
                // System.out.println(maxNode.val);
                bufferedWriter.write(String.valueOf(maxNode.val));
                bufferedWriter.newLine();
            }
        }
        scanner.close();
        bufferedWriter.close();

    }

    private static class MaxNode {
        int val;
        MaxNode previousMax = null;

        public MaxNode(int val) {
            this.val = val;
        }
    }
}
