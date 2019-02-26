package org.mlxxiv.hashcode;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Input in = new Input("input/hashcode/pizza/medium.in");

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
            if (tomatoes<in.low || area - tomatoes < in.low) {
                System.out.println(i+ ": fail: " + Arrays.toString(coord));
                System.out.println();
                for (int y = coord[0]; y <=coord[2] ; y++) {
                    System.out.println();
                    for (int x = coord[1]; x <=coord[3] ; x++) {
                        System.out.print(in.grid[y][x]);
                    }
                }
            }


            boolean overlap = false;
            for (int y = coord[0]; y <=coord[2] ; y++) {
                for (int x = coord[1]; x <=coord[3] ; x++) {
                    if (in.grid[y][x] == Pizza.EMPTY) {
                        overlap = true;
                    } else {
                        in.grid[y][x] = Pizza.EMPTY;
                    }
                }
            }
            if (overlap) {
                System.out.println(i+ ": overlap " + Arrays.toString(coord));
            }


        }
    }




}
