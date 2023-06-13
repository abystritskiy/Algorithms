import java.util.*;

public class SumDecipher {

    public static void main(String[] args) {

        ArrayList<List<Integer>> cypher = new ArrayList<>();
        cypher.add(Arrays.asList(1, 2));
        cypher.add(Arrays.asList(1, 3));
        cypher.add(Arrays.asList(2, 3));
        cypher.add(Arrays.asList(4, 4));

        int n = 10;
        System.out.println(cypher);
        System.out.println(decypher(n, cypher));


        ArrayList<List<Integer>> cypher2 = new ArrayList<>();
        cypher2.add(Arrays.asList(8, 9));
        cypher2.add(Arrays.asList(7, 9));
        cypher2.add(Arrays.asList(9, 9));

        System.out.println(cypher2);
        System.out.println(decypher(n, cypher2));


        List<List<Integer>> cypher3 = generateRandomInput(n);
        System.out.println(cypher3);
        System.out.println(decypher(n, cypher3));
    }

    public static List<List<Integer>> generateRandomInput(int max) {
        Random random = new Random();
        int bound = random.nextInt(max);
        List<List<Integer>> output = new ArrayList<>();

        for (int i=0; i<bound; i++) {
            int start = random.nextInt(max);
            int end = random.nextInt(max);
            while (end < start) {
                end = random.nextInt(max);
            }

            output.add(Arrays.asList(start, end));
        }
        return  output;
    }

    public static HashSet<Integer> decypher(int n, List<List<Integer>> cypher) {
        // n - original (deciphered array size)

        // auxiliary structure to utilize set benefits
        ArrayList<TreeSet<Integer>> elementsStorage = new ArrayList<>();

        // remember deductions operations we did before (to avoid extraneous calculations)
        HashMap<TreeSet<Integer>, HashSet<TreeSet<Integer>>> operation = new HashMap<>();

        // uniquely identifiable indexes of the initial array
        HashSet<Integer> result = new HashSet<>();


        for (int index=0; index<cypher.size(); index++) {
            TreeSet<Integer> cells = new TreeSet<>();
            List<Integer> range = cypher.get(index);
            for (int j=range.get(0); j<=range.get(1); j++) {
                cells.add(j);
            }
            elementsStorage.add(cells);
        }

        // will iterate through elements and see if there are
        // - single element set
        // - 2 elements where one is the subset of the other. in this case we deduct one from the other and re-run
        boolean run = true;
        ArrayList<TreeSet<Integer>> updatedElementsStorage = new ArrayList<>(elementsStorage);

        while (run) {
            for (int i=0; i<elementsStorage.size()-1; i++) {
                for (int j=0; j<elementsStorage.size(); j++) {

                    if (result.size() == n) {
                        System.out.println(result);
                        return result;
                    }

                    if (i == j) continue;

                    TreeSet<Integer> a = elementsStorage.get(i);
                    TreeSet<Integer> b = elementsStorage.get(j);
                    if (a.size() == 1) {
                        result.add(getFirstElement(a));
                    }
                    if (b.size() == 1) {
                        result.add(getFirstElement(b));
                    }

                    // if b is subset of a, and we did not check a,b pair before
                    if (!operation.containsKey(a) || !operation.getOrDefault(a, new HashSet<>()).contains(b)) {

                        if (isSubset(a,b)) {
                            TreeSet aMinusB = deductSets(a, b);
                            updatedElementsStorage.add(aMinusB);
                        }

                        if (!operation.containsKey(a)) {
                            operation.put(a, new HashSet<>());
                        }
                        operation.get(a).add(b);
                    }

                    // if a is subset of b, and we did not check b,a pair before
                    if (!operation.containsKey(b) || !operation.getOrDefault(b, new HashSet<>()).contains(a)) {

                        if (isSubset(b, a)) {
                            TreeSet bMinusA = deductSets(b, a);
                            updatedElementsStorage.add(bMinusA);
                        }

                        if (!operation.containsKey(b)) {
                            operation.put(b, new HashSet<>());
                        }
                        operation.get(b).add(a);
                    }

                }
            }

            run = elementsStorage.size() != updatedElementsStorage.size();
            elementsStorage = new ArrayList<>(updatedElementsStorage);
        }

        return result;
    }

    private static boolean isSubset(TreeSet<Integer> a, TreeSet<Integer> b) {
        // skip identical or empty sets
        if (a.equals(b) || a.size() == 0 || b.size() == 0) {
            return  false;
        }

        TreeSet<Integer> overlap = new TreeSet<>();
        for (Integer element: b) {
            if (a.contains(element)) {
                overlap.add(element);
            }
        }
        return overlap.equals(b);
    }

    private static Integer getFirstElement(TreeSet<Integer> a) {
        for (Integer element: a) {
            return element;
        }
        return -1;
    }

    public static TreeSet<Integer> deductSets(TreeSet<Integer> a, TreeSet<Integer> b) {
        TreeSet<Integer> result = new TreeSet<>(a);
        for (Integer element: b) {
            if (a.contains(element)) {
                result.remove(element);
            }
        }
        return result;
    }
}
