import java.io.*;
import java.util.*;

public class PizzaSlicer {
    char[][] grid;
    int low, high;

    /* pre-computed options */
    HashMap<String, int[][]> heatedPizza;

    /* possible slice sizes*/
    List<Slice> sizes;

    /**
     * Constructor - reads pizza from the file
     *
     * @param fileName
     */
    public PizzaSlicer(String fileName) {
        readInput(fileName);
        calcSizes();
        heatPizza();
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
                grid[y][x] = (Math.random() > 0.5 ? 'T' : 'M');
            }
        }

        calcSizes();
        heatPizza();
    }

    /**
     * Heat pizza (tomatoes count pre-computation) cut by different slices
     */
    private void heatPizza() {
        int pizzaRows = grid.length;
        int pizzaCols = grid[0].length;

        HashMap<String, int[][]> pizzaCache = new HashMap<>();
        for (Slice size : sizes) {
            if (!size.toString().equals("{3, 1}")) {
                continue;
            }
            int[][] heatedPizzaSlice = new int[pizzaRows + 1][pizzaCols + 1];

            int sliceRows = size.rows;
            int sliceCols = size.cols;

            int yStart = 0;
            int xStart = 0;
            int yEnd = sliceRows - 1;
            int xEnd = sliceCols - 1;

//            for (int yStart=0; yStart<pizzaRows-sliceRows; yStart++) {
//                for (int xStart=0; xStart<pizzaCols-sliceCols; xStart++) {
//
//                }
//            }
            while (yEnd < pizzaRows && xEnd < pizzaCols) {
                int tomatoes = 0;
                for (int y = yStart; y <= yEnd; y++) {
                    for (int x = xStart; x <= xEnd; x++) {
                        if (grid[y][x] == 'T') {
                            tomatoes++;
                        }
                    }
                }
                heatedPizzaSlice[yEnd+1][xEnd+1] = tomatoes;
                yStart++;
                xStart++;
                yEnd++;
                xEnd++;
            }

            String cacheKey = sliceRows+":"+sliceCols;
            pizzaCache.put(cacheKey, heatedPizzaSlice);
        }
        heatedPizza = pizzaCache;
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


        List<Slice> options = new ArrayList<>();
        for (int i = high; i >= 1; i--) {
            for (int j = 1; j <= high; j++) {
                if (i > pizzaRows || j > pizzaCols) {
                    continue;
                }
                if (i * j <= high) {
                    Slice size = new Slice(i, j);
                    options.add(size);
                }
            }
        }
        options.sort(Collections.reverseOrder());
        sizes = options;
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
//        PizzaSlicer ps = new PizzaSlicer(7, 6);
        Pizza pizza = new Pizza(ps.grid);
        pizza.printPizza();
        System.out.println(ps.sizes);
        System.out.println(Arrays.deepToString(ps.heatedPizza.get("1:3")));

    }

}
