import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class AlmostSortedSolution {
    public static int[] sorted;

    static void almostSorted(int[] arr) {
        sorted = sort(arr);
        if (isSorted(arr)) {
            System.out.println("yes");
            return;
        }
        int l = -1;
        int r = -1;
        for (int i = 0; i < arr.length; i++) {
            if (l == -1 ) {
                if (arr[i] != sorted[i]) {
                    l = i;
                }
            } else if (sorted[i] == arr[l]) {
                r = i;
                break;
            }
        }

        int[] arrCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);

        int tmp = arrCopy[l];
        arrCopy[l] = arrCopy[r];
        arrCopy[r] = tmp;
        if (isSorted(arrCopy)) {
            System.out.println("yes");
            System.out.println("swap " + (l + 1) + " " + (r + 1));
            return;
        }

        for (int j = l; j <= (l + r) / 2; j++) {
            int idx = r - (j - l);
            int tmp1 = arr[j];
            arr[j] = arr[idx];
            arr[idx] = tmp1;
        }

        if (isSorted(arr)) {
            System.out.println("yes");
            System.out.println("reverse " + (l + 1) + " " + (r + 1));
            return;
        }

        System.out.println("no");
    }

    public static boolean isSorted(int[] arr) {
        return Arrays.equals(arr, sorted);
    }

    static int[] sort(int[] arr) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        for (int el : arr) {
            sortedList.add(el);
        }
        Collections.sort(sortedList);
        int[] sortedArray = new int[arr.length];
        for (int i = 0; i < sortedList.size(); i++) {
            sortedArray[i] = sortedList.get(i);
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] arr0 = new int[]{4, 2};
        int[] arr1 = new int[]{3, 1, 2};
        int[] arr2 = new int[]{1, 5, 4, 3, 2, 6};
        almostSorted(arr0); //yes swap 1 2
        almostSorted(arr1); //no
        almostSorted(arr2); //yes reverse  2 5
    }
}
