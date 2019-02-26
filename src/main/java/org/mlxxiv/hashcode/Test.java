package org.mlxxiv.hashcode;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Input in = new Input("input/hashcode/pizza/big.in");
        Pizza pizza = new Pizza(in.grid);
        int[][] coords = in.readResultsFile();
        for (int i =0; i<coords.length; i++) {
            int[] coord = coords[i];
            int tomatoes = 0;
            int area = (coord[2] - coord[0] + 1) * (coord[3] - coord[1] + 1);
            for (int y = coord[0]; y <=coord[2] ; y++) {
                for (int x = coord[1]; x <=coord[3] ; x++) {
                    if (in.grid[y][x] == Pizza.TOMATO) {
                        tomatoes++;
                    }
                }
            }
            if (tomatoes<6 || area - tomatoes < 6) {
                System.out.println(i+ ": fail!!! " + Arrays.toString(coord));
            }
        }
    }




}
