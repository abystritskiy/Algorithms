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
     * All the magic happens here
     */
    public void sliceIt() {

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
                if (i > pizzaRows || j > pizzaCols) {
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

}
