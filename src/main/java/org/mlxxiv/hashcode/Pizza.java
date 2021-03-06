package org.mlxxiv.hashcode;

public class Pizza {
    public final int rows;
    public final int cols;
    public final char[][] grid;

    public static char TOMATO = 'T';
    public static char MUSHROOM = 'M';
    public static char EMPTY = 176;

    /**
     * Construct the pizza object
     * @param grid  array with pizza components
     */
    public Pizza(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    /**
     * Print pizza
     */
    public void printPizza() {
        System.out.println();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
