/**
 * https://www.hackerrank.com/challenges/big-sorting
 * Difficulty: Easy
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class bigSorting {

    static String[] bigSorting(String[] unsorted) {
        ArrayList<longStringNumber> lsnUnsorded = new ArrayList<>();

        for (int i=0; i<unsorted.length; i++) {
            lsnUnsorded.add(new longStringNumber(unsorted[i])) ;
        }
        Collections.sort(lsnUnsorded);

        String[] result = new String[unsorted.length];
        for (int j=0; j<unsorted.length; j++) {
            result[j] = lsnUnsorded.get(j).toString();
        }

        return result;
    }

    private static class longStringNumber implements Comparable<longStringNumber>
    {
        private final String value;

        public longStringNumber(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }

        private int getChar(int i) {
            return Character.getNumericValue(this.value.charAt(i));
        }

        private boolean equals(longStringNumber that) {
            return this.value.equals(that.value);
        }

        public int compareTo(longStringNumber that) {
            if (that == null) {
                throw new java.lang.NullPointerException();
            }
            if (this.equals(that)) {
                return 0;
            }

            if (this.value.length() > that.value.length()) {
                return 1;
            } else if (this.value.length() < that.value.length()) {
                return -1;
            } else {
                for (int i=0; i<this.value.length(); i++) {
                    if (this.getChar(i) != that.getChar(i)) {
                        return (this.getChar(i) > that.getChar(i)) ? 1 : -1;
                    }
                }
                return 0;
            }

        }
    }

    public static void main(String[] args) {
        String[] unsorted = new String[] {
                "8",
                "1",
                "2",
                "100",
                "12303479849857341718340192371",
                "3084193741082937",
                "3084193741082938",
                "111",
                "200"
        };
        System.out.println(Arrays.toString(bigSorting(unsorted)));
    }
}
