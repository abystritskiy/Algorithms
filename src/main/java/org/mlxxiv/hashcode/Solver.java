package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {
    /** Input/output object */
    Input input;

    /* slices left-top right bottom coordinates (y,x) */
    public static List<int[]> results;

    /* total maximum area of the slices */
    public static Integer globalMax = 0;
    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String dataFile = "input/hashcode/pizza/medium.in";

        Input input = new Input(dataFile);

        results = new ArrayList<>();
        int size = 10;
        int yBlocks = input.grid.length / size;
        if (yBlocks * size != input.grid.length) {
            yBlocks++;
        }
        int xBlocks = input.grid[0].length / size;
        if (xBlocks * size != input.grid[0].length) {
            xBlocks++;
        }

        boolean[][] sliced = new boolean[input.grid.length][input.grid[0].length];
        for (int yI = 0; yI < yBlocks; yI++) {
            for (int xI = 0; xI < xBlocks; xI++) {
                int ySize = (yI * size + size <= input.grid.length) ? size : input.grid.length - yI * size;
                int xSize = (xI * size + size <= input.grid[0].length) ? size : input.grid[0].length - xI * size;
                char[][] subgrid = new char[ySize][xSize];
                for (int subY = yI * size; subY < (yI + 1) * size && subY < input.grid.length; subY++) {
                    for (int subX = xI * size; subX < (xI + 1) * size && subX < input.grid[0].length; subX++) {
                        subgrid[subY - yI * size][subX - xI * size] = input.grid[subY][subX];
                    }
                }

                Orientation orientation;
                List<Integer> firstStartPoint = new ArrayList<>();

                if (yI % 2 == 0 && xI % 2 == 0) {
                    orientation = Orientation.TOP_LEFT;

                    firstStartPoint.add(0);
                    firstStartPoint.add(0);
                } else if (yI % 2 == 0 && xI % 2 == 1) {
                    orientation = Orientation.TOP_RIGHT;

                    firstStartPoint.add(0);
                    firstStartPoint.add(subgrid[0].length-1);
                } else  if (yI % 2 == 1 && xI % 2 == 0) {
                    orientation = Orientation.BOTTOM_LEFT;

                    firstStartPoint.add(subgrid.length-1);
                    firstStartPoint.add(0);
                } else  {
                    orientation = Orientation.BOTTOM_RIGHT;

                    firstStartPoint.add(subgrid.length-1);
                    firstStartPoint.add(subgrid[0].length-1);
                }

                PizzaSlicer ps = new PizzaSlicer(input.low, input.high, subgrid, orientation, sliced);

                List<List<Integer>> next = new ArrayList<>();
                next.add(firstStartPoint);

                ps.slice(next);

                globalMax += ps.max;
                for (int[] coord: ps.coordinates) {
                    int[] coordinatesCorrectedPosition = new int[]{
                        coord[0] + yI * size,
                        coord[1] + xI * size,
                        coord[2] + yI * size,
                        coord[3] + xI * size,
                    };
                    results.add(coordinatesCorrectedPosition);
                }
            }
        }

        System.out.println("Max: " + globalMax);

        for (int[] coord : results) {
            System.out.println(Arrays.toString(coord));
        }

        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.TOP_LEFT, sliced);
        ps.showCovered(results);

        long endTime = System.currentTimeMillis();

        System.out.println("execution time: " + (endTime - startTime));

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
