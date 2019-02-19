import java.io.*;
import java.util.*;

public class PizzaSlicer {
    char[][] grid;
    int low, high;

    /* possible slice sizes */
    private List<Slice> sizes;

    /* cells used in slices */
    private boolean[][] sliced;

    /* slices left-top right bottom coordinates (y,x) */
    public List<int[]> coordinates;

    /* total maximum area of the slices */
    public Integer max = 0;

    /* slices left-top right bottom temp-coordinates (y,x) */
    public List<int[]> tempCoordinates;

    /* total temp-maximum area of the slices */
    public Integer tempMax = 0;

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

        sliced = new boolean[grid.length][grid[0].length];
        coordinates = new ArrayList<>();
        tempCoordinates = new ArrayList<>();

    }

    public boolean slice( List<List<Integer>> points) {

        if (tempMax == grid.length * grid[0].length) {
            max = tempMax;
            coordinates = new ArrayList<>(tempCoordinates);
            return true;
        }

        if (points.isEmpty()) {
            return false;
        }

        for (List<Integer> point : points) {
            int y0 = point.get(0);
            int x0 = point.get(1);

            for (Slice size : sizes) {
                size.locate(y0, x0);


                if (!size.isValidSlice(low, sliced)) {
                    continue;
                }

                // save current slices
                tempCoordinates.add(
                        new int[]{y0, x0, y0 + size.rows - 1, x0 + size.cols - 1}
                );
                tempMax += size.rows * size.cols;
                size.setSliced(sliced);

                List<List<Integer>> next = new ArrayList<>();
                List<Integer> rightPoint = size.getNextRightPoint(sliced);
                List<Integer> bottomPoint = size.getNextBottomPoint(sliced);

                if (rightPoint != null && rightPoint.size()>0) {
                    next.add(rightPoint);
                }
                if (bottomPoint != null && bottomPoint.size()>0) {
                    next.add(bottomPoint);
                }

                if (slice(next)) {
                    return true;
                } else {
                    size.locate(y0,x0);
                    if (tempMax > max) {
                        max = tempMax;
                        coordinates = new ArrayList<>(tempCoordinates);
                    }

                    // rollback
                    size.setUnsliced(sliced);
                    tempMax -= size.rows * size.cols;
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
        long startTime = System.nanoTime();
        PizzaSlicer ps = new PizzaSlicer("input/hashcode/pizza/10x10.in");


//        Pizza pizza = new Pizza(ps.grid);
//        pizza.printPizza();

        List<Integer> firstStartPoint = new ArrayList<>();
        firstStartPoint.add(0);
        firstStartPoint.add(0);

        List<List<Integer>> next = new ArrayList<>();
        next.add(firstStartPoint);

        ps.slice(next);
        System.out.println("Max: " + ps.max);
        System.out.println();
        for (int[] coord : ps.coordinates) {
            System.out.println(Arrays.toString(coord));
        }

        long endTime = System.nanoTime();

        System.out.println("execution time: " + (endTime-startTime)/1000000);
    }


    public static void generatePizza() {
        PizzaSlicer ps = new PizzaSlicer(11, 11, 2, 6) ;

        Pizza pizza = new Pizza(ps.grid);
        pizza.printPizza();
    }
}
