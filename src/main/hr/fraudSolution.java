import java.util.Arrays;

public class fraudSolution {

    static int activityNotifications(int[] exp, int d) {
        int t = 0;

        for (int i=d; i<exp.length; i++) {
            int[] sub = new int[d];
            System.arraycopy(exp, i-d, sub, 0, d);
            countingSort(sub);

            double med;
            if (sub.length%2 == 1) {
                med = sub[sub.length/2];
            } else {
                med =((double) sub[sub.length/2] + sub[sub.length/2-1])/2;
            }
            int amount = exp[i];
            if (amount >= 2*med) {
                t++;
            }
        }
        return t;
    }

    private static int[] rebuildForMedian(int[] old, int newElement, int oldElement) {
        int[] newArray = new int[old.length];
        int[] arr = new int[old.length-1];
        for (int k=0; k<old.length; k++) {
            if (oldElement == old[k]) {
                System.arraycopy(old, 0, arr, 0, k);
                System.arraycopy(old, k+1, arr, k, old.length - 1 - k);
                break;
            }
        }

        int j = 0;
        int i = 0;
        boolean placed = false;
        while(j < old.length) {
            if (j == old.length-1 && !placed) {
                newArray[j] = newElement;
                return newArray;
            }
            if (newElement < arr[i] && !placed) {
                newArray[j] = newElement;
                placed = true;
                j++;
            } else {
                newArray[j] = arr[i];
                j++;
                i++;
            }
        }
        return newArray;
    }

    public static void countingSort(int[] arr) {
        int max=201;
        int N = arr.length;
        int[] count = new int[max+1];
        int[] aux = new int[N];

        for (int i=0; i<N; i++ ) {
            count[arr[i]+1]++;
        }
        for (int i=0; i<max; i++ ) {
            count[i+1] += count[i];
        }
        for (int i=0; i<max; i++ ) {
            aux[count[arr[i]]++] += arr[i];
        }
        for (int i=0; i<N; i++) {
            arr[i] = aux[i];
        }
    }



    public static void main(String[] args) {
        int[] test = new int[] {2, 3, 4, 2, 3, 6, 8, 4, 5};
        System.out.println(activityNotifications(test, 5));
    }
}
