/**
 * https://www.hackerrank.com/challenges/lilys-homework
 * Difficulty: medium
 *
 */
import java.util.Arrays;
import java.util.HashMap;

public class LilysHomeworkSolution {
    static int lilysHomework(int[] arr) {
        int[] sortedAsc = new int[arr.length];
        int[] sortedDesc = new int[arr.length];

        System.arraycopy(arr, 0, sortedAsc, 0, arr.length);
        Arrays.sort(sortedAsc);

        System.arraycopy(sortedAsc, 0, sortedDesc, 0, arr.length);
        reverseArray(sortedDesc);

        int swapsAsc = getSwaps(arr, sortedAsc);
        int swapsDesc = getSwaps(arr, sortedDesc);
        return Math.min(swapsAsc, swapsDesc);
    }

    private static int getSwaps(int[] arr, int[] sorted) {
        int swaps = 0;
        boolean[] visited = new boolean[arr.length];

        HashMap<Integer, Integer> mapSorted = new HashMap<>();
        for (int k=0; k<sorted.length; k++) {
            mapSorted.put(sorted[k], k);
        }

        for (int i=0; i<arr.length; i++) {
            if (visited[i] || arr[i] == sorted[i]) {
                continue;
            }

            int c_count = 0;
            int key = i;

            while (!visited[key]) {
                visited[key] = true;
                c_count++;
                key = mapSorted.get(arr[key]);
            }

            swaps += c_count - 1;
        }
        return swaps;
    }

    private static void reverseArray(int[] arr) {
        for (int j=0; j<arr.length/2; j++) {
            int tmp = arr[j];
            arr[j] = arr[arr.length-1-j];
            arr[arr.length-1-j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 5, 3, 1};
        System.out.println(lilysHomework(arr)); // 2
    }
}
