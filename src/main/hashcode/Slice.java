/**
 * Class to implement sortable sizes - from most fitting to "high" to least
 */
class Slice implements Comparable<Slice> {
    public final int rows;
    public final int cols;

    public Slice(int rows, int colls) {
        this.rows = rows;
        this.cols = colls;
    }

    public int compareTo(Slice that) {
        return this.cols * this.rows > that.cols * that.rows ? 1 : -1;
    }

    public String toString() {
        return "{"+rows+", "+cols+"}";
    }
}