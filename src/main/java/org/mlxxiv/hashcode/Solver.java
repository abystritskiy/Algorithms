package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class can read data input, cut pizza in pieces, process each piece as independent pizza and aggregate results
 */
public class Solver {
    /* slices left-top right bottom coordinates full list (y,x) */
    public static List<int[]> results;

    /* total maximum area of the slices */
    public static int globalMax = 0;

    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        // replace with your data file path or move it to args variable
        String dataFile = args[0];

        // start time to measure performance
        long startTime = System.currentTimeMillis();

        // utility class, that will read and write data/results
        Input input = new Input(dataFile);

        // surprisingly, this will store calculation results
        results = new ArrayList<>();

        // size of the sub-piece, we're going to cut big pizza into.
        // on typical data set with 5-15 max slice size, different values between 10 and 15 performs the best,
        // (in reasonable amount of time) but whoever has a lot of time can play with this value
        int size = Integer.parseInt(args[1]);

        // number of blocks on y-scale
        int yBlocks = (input.grid.length + size - 1) / size;

        // number of blocks on x-scale
        int xBlocks = (input.grid[0].length + size - 1) / size;

        // boolean grid with the same size as pizza - to check if slices do not overlap
        boolean[][] sliced = new boolean[input.grid.length][input.grid[0].length];

        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.TOP_LEFT, sliced);

        // breaking pizza into smaller square size x size pieces (otherwise exponential complexity will kill us!)
        for (int yI = 0; yI < yBlocks; yI++) {
            for (int xI = 0; xI < xBlocks; xI++) {
                // check if it is full-sized (size X size) sub-piece or not - ones at the end
                int ySize = (yI * size + size <= input.grid.length) ? size : input.grid.length - yI * size;
                int xSize = (xI * size + size <= input.grid[0].length) ? size : input.grid[0].length - xI * size;

                // sub-piece of pizza we're going to check separately
                // it is possible do not create separate array 'subgrid', just path block size and left-top coordinates,
                // but for the sake of simplicity and easy debug it is done like this
                char[][] subGrid = new char[ySize][xSize];
                for (int subY = yI * size; subY < (yI + 1) * size && subY < input.grid.length; subY++) {
                    for (int subX = xI * size; subX < (xI + 1) * size && subX < input.grid[0].length; subX++) {
                        subGrid[subY - yI * size][subX - xI * size] = input.grid[subY][subX];
                    }
                }

                for (int[] coordinates : ps.getBest(subGrid, input.low, input.high)) {
                    int[] corrected = new int[]{
                            coordinates[0] + yI * size,
                            coordinates[1] + xI * size,
                            coordinates[2] + yI * size,
                            coordinates[3] + xI * size,
                    };
                    results.add(corrected);
                    for (int ySliced = corrected[0]; ySliced <= corrected[2]; ySliced++) {
                        for (int xSliced = corrected[1]; xSliced <= corrected[3] ; xSliced++) {
                            sliced[ySliced][xSliced] = true;
                        }
                    }
                    globalMax += PizzaSlicer.getSliceSize(corrected);
                }
            }
        }


        // try once more - checking what has left
        List<int[]> remnants = ps.getRemnants(ps.getCutPizza(results, input.grid).grid);

        for (int[] remnant : remnants) {
            int y0 = remnant[0];
            int x0 = remnant[1];
            int y1 = remnant[2];
            int x1 = remnant[3];

            char[][] subGrid = new char[y1 - y0 + 1][x1 - x0 + 1];
            for (int y = y0; y <= y1; y++) {
                for (int x = x0; x <= x1; x++) {
                    subGrid[y - y0][x - x0] = input.grid[y][x];
                }
            }

            for (int[] subCoordinates : ps.getBest(subGrid, input.low, input.high)) {
                int[] coordinatesWithCorrectedPosition = new int[]{
                        subCoordinates[0] + y0,
                        subCoordinates[1] + x0,
                        subCoordinates[2] + y0,
                        subCoordinates[3] + x0,
                };
                results.add(coordinatesWithCorrectedPosition);
                globalMax += PizzaSlicer.getSliceSize(coordinatesWithCorrectedPosition);
            }
        }

//        uncomment if you want to see what was covered
//        ps.getCutPizza(results,input.grid).printPizza();

        System.out.println("Total Coverage: " + globalMax);
        System.out.println("Total Coverage Percent: " +
                ((float) globalMax * 100 ) / (input.grid.length * input.grid[0].length) + "%");
        System.out.println("Execution Time: " + (System.currentTimeMillis() - startTime) + " ms");


        // write output to the file with the same name and *.out instead of *.in
        input.writeOutput(results);
    }

    /**
     * Orientation defines in which direction new slices are placed and
     * which corner is defined with y0, x0 coordinates
     */
    enum Orientation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
