package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    /* slices left-top right bottom coordinates full list (y,x) */
    public static List<int[]> results;

    /* total maximum area of the slices */
    public static Integer globalMax = 0;

    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        // replace with your data file path or move it to args variable
        String dataFile = "input/hashcode/pizza/big.in";

        // start time to measure performance
        long startTime = System.currentTimeMillis();

        // utility class, that will read and write data/results
        Input input = new Input(dataFile);

        // surprisingly, this will store calculation results
        results = new ArrayList<>();

        // size of the sub-piece, we're going to cut big pizza into.
        // on typical data set with 5-15 max slice size, 10 performs the best,
        // but whoever has a lot of time, can play with this value
        int size = 10;

        // number of blocks on y-scale
        int yBlocks = (input.grid.length + size - 1) / size;

        // number of blocks on x-scale
        int xBlocks = (input.grid[0].length + size - 1) / size;

        // boolean grid with the same size as pizza - to check if slices do not overlap
        boolean[][] sliced = new boolean[input.grid.length][input.grid[0].length];

        // breaking pizza into smaller square size x size pieces (otherwise exponential complexity will kill us!)
        for (int yI = 0; yI < yBlocks; yI++) {
            for (int xI = 0; xI < xBlocks; xI++) {
                // check if it is full-sized (size X size) sub-piece or not - ones at the end
                int ySize = (yI * size + size <= input.grid.length) ? size : input.grid.length - yI * size;
                int xSize = (xI * size + size <= input.grid[0].length) ? size : input.grid[0].length - xI * size;

                // sub-piece of pizza we're going to check separately
                // it is possible do not create separate array 'subgrid', just path block size and left-top coordinates,
                // but for the sake of simplicity and easy debug it is done like this
                char[][] subgrid = new char[ySize][xSize];
                for (int subY = yI * size; subY < (yI + 1) * size && subY < input.grid.length; subY++) {
                    for (int subX = xI * size; subX < (xI + 1) * size && subX < input.grid[0].length; subX++) {
                        subgrid[subY - yI * size][subX - xI * size] = input.grid[subY][subX];
                    }
                }

                // orientation means from which corner of the pizza we start to put slices (left-right-top-bottom)
                Orientation orientation;

                // coordinates of the piece that will be "cut" first
                List<Integer> firstStartingPoint = new ArrayList<>();

                // Each block gets it's own orientation, in the sequence TL->TR->BL->BR.
                // In my opinion, it will  allow slightly better results, since it will be
                // possible to process empty (not covered) spaces on the borders of blocks
                if (yI % 2 == 0 && xI % 2 == 0) {
                    orientation = Orientation.TOP_LEFT;

                    firstStartingPoint.add(0);
                    firstStartingPoint.add(0);
                } else if (yI % 2 == 0 && xI % 2 == 1) {
                    orientation = Orientation.TOP_RIGHT;

                    firstStartingPoint.add(0);
                    firstStartingPoint.add(subgrid[0].length - 1);
                } else if (yI % 2 == 1 && xI % 2 == 0) {
                    orientation = Orientation.BOTTOM_LEFT;

                    firstStartingPoint.add(subgrid.length - 1);
                    firstStartingPoint.add(0);
                } else {
                    orientation = Orientation.BOTTOM_RIGHT;

                    firstStartingPoint.add(subgrid.length - 1);
                    firstStartingPoint.add(subgrid[0].length - 1);
                }

                PizzaSlicer pizzaSlicer = new PizzaSlicer(input.low, input.high, subgrid, orientation, sliced);
                List<List<Integer>> next = new ArrayList<>();
                next.add(firstStartingPoint);

                // all the magic is done hear (honestly - no magic, just backtracking with couple conditions)
                pizzaSlicer.slice(next);

                // summing up sliced area on the sub-pieces
                globalMax += pizzaSlicer.max;

                // adjusting to 'global' pizza coordinates (from smaller sub-pieces) and summing them up as a result
                for (int[] coordinates : pizzaSlicer.coordinates) {
                    int[] coordinatesWithCorrectedPosition = new int[]{
                            coordinates[0] + yI * size,
                            coordinates[1] + xI * size,
                            coordinates[2] + yI * size,
                            coordinates[3] + xI * size,
                    };
                    results.add(coordinatesWithCorrectedPosition);
                }
            }
        }

        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.TOP_LEFT, sliced);

        // visual results of what we've got (before optimization)
        Pizza pizza = ps.getCutPizza(results);
        pizza.printPizza();
        System.out.println("Total Coverage: " + globalMax);
        System.out.println("Total Coverage percent: " +
                ((float) globalMax * 100 ) / (input.grid.length * input.grid[0].length) + "%" );


        for (int[] remnant : ps.getRemnants(pizza.grid, size)) {
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

            for (int[] subCoordinates : getItAll(subGrid, input.low, input.high)) {
                int[] coordinatesWithCorrectedPosition = new int[]{
                        subCoordinates[0] + y0,
                        subCoordinates[1] + x0,
                        subCoordinates[2] + y0,
                        subCoordinates[3] + x0,
                };
                results.add(coordinatesWithCorrectedPosition);
            }
        }

        System.out.println("*****************************");
        System.out.println("*****************************");

        ps.getCutPizza(results).printPizza();

        System.out.println("Total Coverage (after remnants hunting): " + globalMax);
        System.out.println("Total Coverage percent (after remnants hunting): " +
                ((float) globalMax * 100 ) / (input.grid.length * input.grid[0].length) + "%");
        System.out.println("Execution Time: " + (System.currentTimeMillis() - startTime) + " ms");


        // write output to the file with the same name and *.out instead of *.in
        // input.writeOutput(results);
    }

    /**
     * Get the maximum of remnants, trying all the orientations
     *
     * @param grid
     * @param low
     * @param high
     * @return
     */
    private static List<int[]> getItAll(char[][] grid, int low, int high) {
        Orientation[] orientations = new Orientation[] {
            Orientation.TOP_LEFT, Orientation.TOP_RIGHT, Orientation.BOTTOM_LEFT, Orientation.BOTTOM_RIGHT
        };
        int[] ys = new int[] {0, 0, grid.length - 1, grid.length - 1};
        int[] xs = new int[] {0, grid[0].length - 1, 0, grid[0].length - 1};

        int max = 0;
        List<int[]> results = new ArrayList<>();

        for (int i = 0; i <= 3; i++) {
            Orientation orientation = orientations[i];
            int y0 = ys[i];
            int x0 = xs[i];

            boolean[][] subSliced = new boolean[grid.length][grid[0].length];
            List<Integer> firstStartingPoint = new ArrayList<>();
            firstStartingPoint.add(y0);
            firstStartingPoint.add(x0);

            PizzaSlicer pizzaSlicer = new PizzaSlicer(low, high, grid, orientation, subSliced);
            List<List<Integer>> next = new ArrayList<>();
            next.add(firstStartingPoint);
            pizzaSlicer.slice(next);

            if (pizzaSlicer.max > max) {
                max = pizzaSlicer.max;
                results = new ArrayList<>(pizzaSlicer.coordinates);
            }
        }
        globalMax += max;
        return results;
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
