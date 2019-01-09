import java.util.Arrays;

public class LilysHomeworkSolution {

    static int lilysHomework(int[] arr) {
        int swaps = 0;
        int[] sorted = new int[arr.length];

        System.arraycopy(arr, 0, sorted, 0, arr.length);
        Arrays.sort(sorted);


        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sorted));

        for (int i=0; i<arr.length-1; i++) {
            if (arr[i] != sorted[i]) {
                for (int j=i+1; j<arr.length; j++) {
                    if (arr[j] == sorted[i]) {
                        // swap arr[i] and arr[j]
                        int tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                        swaps++;
                        break;
                    }
                }
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3, 4, 5, 2};
        System.out.println(lilysHomework(arr)); // 2
    }
}
