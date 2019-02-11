import java.io.*;
import java.util.*;

public class PizzaSlicer {
    char[][] grid;
    int low, high;

    /* possible slice sizes */
    private List<Slice> sizes;

    /* next start point for slice */
    private List<List> next;

    /* cells used in slices */
    private boolean[][] sliced;

    /* slices left-top right bottom coordinates (y,x) */
    public List<int[]> coordinates;

    /* total maximum area of the slices */
    public int max = 0;

    /* slices left-top right bottom temp-coordinates (y,x) */
    public List<int[]> tempCoordinates;

    /* total temp-maximum area of the slices */
    public int tempMax = 0;

    /**
     * Constructor - reads pizza from the file
     *
     * @param fileName
     */
    public PizzaSlicer(String fileName) {
        readInput(fileName);
        calcSizes();

        sliced = new boolean[grid.length][grid[0].length];
        coordinates = new ArrayList<>();
        tempCoordinates = new ArrayList<>();

        ArrayList<Integer> firstStartPoint = new ArrayList<>();
        firstStartPoint.add(0);
        firstStartPoint.add(0);

        next = new ArrayList<>();
        next.add(firstStartPoint);
    }

    /**
     * All the magic starts here
     */
    public boolean sliceIt() {
        List<Integer> point = next.get(next.size() - 1);
        next.remove(next.size() - 1);
        String nextTS = next.toString();
        int y0 = point.get(0);
        int x0 = point.get(1);

        for (Slice size : sizes) {
            if (size.isValidSlice(y0, x0, low, sliced)) {
                tempCoordinates.add(
                        new int[]{y0, x0, y0 + size.rows - 1, x0 + size.cols - 1}
                );
                size.setSliced(y0, x0, sliced);

                List<Integer> rightPoint = size.getNextRightPoint(y0, x0, sliced);
                List<Integer> bottomPoint = size.getNextBottomPoint(y0, x0, sliced);

                if (rightPoint != null && rightPoint.size()>0) {
                    next.add(rightPoint);
                }
                if (bottomPoint != null && bottomPoint.size()>0) {
                    next.add(bottomPoint);
                }
                tempMax += size.rows * size.cols;

                if (sliceIt()) {
                    return true;
                } else {

                    if (tempMax > max) {
                        max = tempMax;
                        coordinates = tempCoordinates;
                    }

                    //roll back
                    tempMax -= size.rows * size.cols;
                    size.setUnsliced(y0, x0, sliced);
                    tempCoordinates.remove(tempCoordinates.size()-1);
                }
            }
        }
        return false;
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
        System.out.println();
        System.out.println(ps.sizes);

        ps.sliceIt();
        System.out.println("Max: " + ps.max);
        System.out.println();
        for (int[] coord: ps.coordinates) {
            System.out.println(Arrays.toString(coord));
        }
    }

}
