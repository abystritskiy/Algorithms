package org.mlxxiv.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver {
    /**
     * All the fun happens here
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String dataFile = "input/hashcode/pizza/example.in";

        Input input = new Input(dataFile);



        int size = 5;
        int yShift = 0;
        int xShift = 0;
        int y = 0;


        while (yShift + y < input.grid.length) {
            int ySize = (yShift + y + size <= input.grid.length) ? input.grid.length - yShift +1 : size;
            int xSize = (xShift + x + size <= input.grid[0].length) ? input.grid[0].length - xShift +1 : size;
            char[][] grid = new char[ySize][xSize];

            for (int y1=yShift-1; y1<ySize; y1++) {
                for (int x1=yShift-1; y1<ySize; y1++) {

                }
            }
        }



        PizzaSlicer ps = new PizzaSlicer(input.low, input.high, input.grid, Orientation.BOTTOM_RIGHT);
        Pizza pizza = new Pizza(ps.grid);



        pizza.printPizza();

        List<Integer> firstStartPoint = new ArrayList<>();
        firstStartPoint.add(2);
        firstStartPoint.add(4);
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

        System.out.println("execution time: " + (endTime-startTime));
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
