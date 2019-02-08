import java.io.*;
import java.util.*;

public class PizzaSlicer {
    char[][] grid;
    int low, high;

    /* possible slice sizes*/
    private List<Slice> sizes;

    /**
     * Constructor - reads pizza from the file
     *
     * @param fileName
     */
    public PizzaSlicer(String fileName) {
        readInput(fileName);
        calcSizes();
    }

    /**
     * Constructor - generates random rows*cols pizza
     *
     * @param rows
     * @param cols
     */
    public PizzaSlicer(int rows, int cols, int low, int high) {
        this.low = low;
        this.high = low;
        grid = new char[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = (Math.random() > 0.5 ? Pizza.TOMATO : Pizza.MUSHROOM);
            }
        }
        calcSizes();
    }

    /**
     * All the magic starts here
     */
    public void sliceIt() {
        Integer coveredArea = 0;
        Integer checkedArea = 0;
        List<int[]> pieces = new ArrayList<>();

        SliceTask task0 = new SliceTask(
                sizes.get(0), 0, 0, coveredArea, grid, checkedArea, pieces
        );


        Queue<SliceTask> queue = new LinkedList();
        queue.add(task0);

        while (queue.size() != 0) {
            SliceTask task = queue.poll();
            if (task.isValidSlice(low)) {
                task.addBottomToQueue(queue);
                task.addRightToQueue(queue);
            } else {
                // check next size;
                // if no next size - shift position (maybe random)
            }

        }
    }

    /**
     * All the magic happens here - part two
     */
    public void sliceIt(int y0, int x0) {
        int[] start = getNextStartingPoint();
        ArrayList<Slice> possibleSlices = getPossibleSlices(start);
    }

    public int[] getNextStartingPoint(List<int[]>)
    {

    }

    public List<Slice> getPossibleSlices(int []);
    {

    }

    /**
     * Get available slice sizes
     * sorted from most fitting the 'high'
     * to least
     *
     * @return
     */
    private void calcSizes() {
        int pizzaRows = grid.length;
        int pizzaCols = grid[0].length;

        sizes = new ArrayList<>();
        for (int i = high; i >= 1; i--) {
            for (int j = 1; j <= high; j++) {
                if (i > pizzaRows || j > pizzaCols || i * j < 2 * low) {
                    continue;
                }
                if (i * j <= high) {
                    Slice size = new Slice(i, j, grid);
                    sizes.add(size);
                }
            }
        }
        sizes.sort(Collections.reverseOrder());
    }

    /**
     * Read the data file
     *
     * @param fileName
     * @return
     */
    private void readInput(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] initLine = bufferedReader.readLine().trim().split(" ");

            int rows = Integer.parseInt(initLine[0]), cols = Integer.parseInt(initLine[1]);
            low = Integer.parseInt(initLine[2]);
            high = Integer.parseInt(initLine[3]);

            grid = new char[rows][cols];
            for (int y = 0; y < rows; y++) {
                char row[] = bufferedReader.readLine().trim().toCharArray();
                grid[y] = row;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    /**
     * Write the results to file
     *
     * @param fileName
     * @param out
     */
    private void writeOutput(String fileName, int[][] out) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(out.length));
            bufferedWriter.newLine();

            for (int[] row : out) {
                bufferedWriter.write(row[0] + " " + row[1] + " " + row[2] + " " + row[3]);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

    /**
     * Write output from XXX.in to XXX.out
     *
     * @param inFileName
     * @return
     */
    private String getOutFileName(String inFileName) {
        return inFileName.replaceAll(".in", ".out");
    }

    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        PizzaSlicer ps = new PizzaSlicer("input/hashcode/pizza/example.in");
        Pizza pizza = new Pizza(ps.grid);
        pizza.printPizza();
        System.out.println(ps.sizes);

    }

    /**
     * Utility class to work with the stack
     */
    private class SliceTask {
        Slice slice;
        final int y0;
        final int x0;
        final char[][] pizza;
        Integer coveredArea;
        Integer checkedArea;
        List<int[]> pieces;

        private SliceTask(Slice slice,
                          int y0, int x0,
                          int coveredArea, char[][] pizza,
                          int checkedArea, List<int[]> pieces) {
            this.slice = slice;
            this.y0 = y0;
            this.x0 = x0;
            this.pizza = pizza;
            this.coveredArea = coveredArea;
            this.checkedArea = checkedArea;
            this.pieces = pieces;
        }

        private boolean coveredAll() {
            return coveredArea == pizza.length * pizza[0].length;
        }

        private boolean isValidSlice(int low) {
            return slice.isValidSlice(y0, x0, low) && slice.fitsThePizza(y0, x0);
        }

        private void addBottomToQueue(Queue<SliceTask> queue) {
            SliceTask taskBottom = new SliceTask(
                    sizes.get(0), this.y0 + this.slice.rows, this.x0,
                    coveredArea, grid, checkedArea, pieces
            );
            queue.add(taskBottom);
        }

        private void addRightToQueue(Queue<SliceTask> queue) {
            SliceTask taskRight = new SliceTask(
                    sizes.get(0), this.y0, this.x0 + this.slice.cols,
                    coveredArea, grid, checkedArea, pieces
            );
            queue.add(taskRight);
        }

    }
}
