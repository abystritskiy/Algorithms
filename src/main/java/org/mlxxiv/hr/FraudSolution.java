/**
 *
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/
 * Difficulty: medium
 *
 */


public class FraudSolution {

    static int activityNotifications(int[] exp, int d) {
        int t = 0;
        int[] sub = new int[d];
        System.arraycopy(exp, 0, sub, 0, d);
        countingSort(sub);

        for (int i=d; i<exp.length; i++) {
            System.arraycopy(exp, i-d, sub, 0, d);

            double med = getMedian(sub);

            int amount = exp[i];
            if (amount >= 2*med) {
                t++;
            }
            if (i != exp.length -1) {
                sub = rebuildForMedian(sub, exp[i-d], exp[i]);
            }

        }
        return t;
    }

    private static double getMedian(int[] arr) {
        double med;
        if (arr.length%2 == 1) {
            med = arr[arr.length/2];
        } else {
            med =((double) arr[arr.length/2] + arr[arr.length/2-1])/2;
        }
        return med;
    }

    private static int[] rebuildForMedian(int[] old, int newEl, int oldEl) {

        int oldElPos = -1;
        int newElPos = -1;

        int i = 0;
        while (oldElPos == -1 || newElPos == -1) {
            if (old[i] == oldEl) {
                oldElPos = i;
            }

            if (newEl <= old[i] && i==0) {
                //should be prepend

            }

            i++;
        }

//        0 1 2 3 4 5 6 7
//            ^     ^

        int[] newSub = new int[old.length];

        if (newElPos <  oldElPos) {
            System.arraycopy(old, 0, newSub, 0, newElPos);
            newSub[newElPos] = newEl;
            System.arraycopy(old, newElPos+1, newSub,newElPos+1,oldElPos-newElPos);
            System.arraycopy(old, newElPos+1, newSub, oldElPos,old.length-oldElPos-1);
        }
        return newSub;
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
//        2 3 4 2 3 6 8 4 5
//                    ^
//        2 3 3 4 8 => 3

//        d=6 => med=3 total=1
    }
}
