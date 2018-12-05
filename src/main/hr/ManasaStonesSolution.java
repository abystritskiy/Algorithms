import java.util.*;


public class ManasaStonesSolution {

    static int[] stones(int n, int a, int b) {
        HashMap<Integer, Boolean> values = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int x=0; x<n; x++) {
            int y = n-1-x;
            int last = a*y + b*x;
            if (!values.containsKey(last)) {
                values.put(last, true);
                list.add(last);
            }
        }
        Collections.sort(list);
        int[] results = new int[values.size()];
        for (int i=0; i<list.size(); i++) {
            results[i] = list.get(i);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(stones(58, 69, 24)));
        System.out.println(Arrays.toString(stones(83, 86, 81)));
        System.out.println(Arrays.toString(stones(73, 25, 25)));
        System.out.println(Arrays.toString(stones(12, 73, 82)));
        System.out.println(Arrays.toString(stones(5, 3, 23)));
    }
}
