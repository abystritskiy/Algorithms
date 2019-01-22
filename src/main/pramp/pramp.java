import java.io.*;
import java.util.*;

class pramp {

    static int[] getIndicesOfItemWeights(int[] arr, int limit) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<arr.length; i++) {
            int compVal = limit - arr[i]; // ? this is negative
            if (map.containsKey(compVal)) {
                int j = map.get(compVal);
                return new int[] {i, j};
            } else {
                map.put(arr[i], i);
            }
        }
        int[] results = new int[2];

        return results;
    }

    public static void main(String[] args) {

    }

}