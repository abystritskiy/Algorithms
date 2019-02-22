package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {
    /** Input/output object */
    Input input;

    /* slices left-top right bottom coordinates (y,x) */
    public List<int[]> results;

    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String dataFile = "input/hashcode/pizza/10x10.in";

        Input input = new Input(dataFile);


        int size = 5;
        int yBlocks = input.grid.length / size;
        if (yBlocks * size != input.grid.length) {
            yBlocks++;
        }
        int xBlocks = input.grid[0].length / size;
        if (xBlocks * size != input.grid[0].length) {
            xBlocks++;
        }


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
                Pizza smallpizza = new Pizza(subgrid);
                smallpizza.printPizza();

            }
        }
        System.out.println("----------");
        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.TOP_LEFT);
        Pizza pizza = new Pizza(ps.grid);


        pizza.printPizza();

        List<Integer> firstStartPoint = new ArrayList<>();
        firstStartPoint.add(0);
        firstStartPoint.add(0);
//        firstStartPoint.add(pizza.rows-1);
//        firstStartPoint.add(pizza.cols-1);

        List<List<Integer>> next = new ArrayList<>();
        next.add(firstStartPoint);

        ps.slice(next);
        System.out.println("Max: " + ps.max);
        System.out.println();
        for (int[] coord : ps.coordinates) {
            System.out.println(Arrays.toString(coord));
        }

        long endTime = System.currentTimeMillis();

        System.out.println("execution time: " + (endTime - startTime));
        System.out.println();

        ps.showCovered(ps.coordinates);
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
