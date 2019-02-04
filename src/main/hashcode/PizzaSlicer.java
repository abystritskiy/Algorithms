import java.io.*;
import java.util.*;

public class PizzaSlicer {
    public boolean[][] pizzaGrid;
    static int low;
    static int high;

    public PizzaSlicer(String fileName) {
        this.readInput(fileName);


        String out = fileName.replaceAll(".in", ".out");


        writeOutput(
                out,
                new int[][]{
                        {0, 0, 2, 1},
                        {0, 2, 2, 2},
                        {0, 3, 2, 4}
                }
        );
    }

    /**
     * Read the data file
     *
     * @param fileName
     * @return
     */
    public void readInput(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] initLine = bufferedReader.readLine().trim().split(" ");

            int rows = Integer.parseInt(initLine[0]), cols = Integer.parseInt(initLine[1]);
            low = Integer.parseInt(initLine[2]);
            high = Integer.parseInt(initLine[3]);

            pizzaGrid = new boolean[rows][cols];
            for (int y = 0; y < rows; y++) {
                char row[] = bufferedReader.readLine().trim().toCharArray();

                for (int x = 0; x < cols; x++) {
                    pizzaGrid[y][x] = (row[x] == 'T');
                }
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
    public void writeOutput(String fileName, int[][] out) {
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
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        PizzaSlicer ps = new PizzaSlicer("input/hashcode/pizza/small.in");
        Pizza pizza = new Pizza(ps.pizzaGrid);

        // pure debug
        System.out.println(Arrays.deepToString(ps.pizzaGrid));


    }
}
