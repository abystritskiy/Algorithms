package org.mlxxiv.lc;

import java.util.Arrays;

public class Interview1_2 {
    public static void main(String[] args) {
        int[][] t1 = generateMatrix(5);
        for (int i = 0; i < t1.length; i++) {
            System.out.println(Arrays.toString(t1[i]));
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][];

        int y = 0;
        int x = 0;

        res[y] = new int[n];

        int k = 1;
        res[y][x] = k;

        k++;
        char direction = 'r';


        while(true) {
            if (direction == 'r') {
                if (y+1 < n && res[y+1]==null) {
                    res[y+1] = new int[n];
                }
                if (x+1 < n && res[y][x+1] == 0) {
                    res[y][x+1] = k;
                    x++;
                    k++;
                } else if (y+1<n && res[y+1][x] == 0) {
                    direction = 'b';
                    res[y+1][x] = k;
                    y++;
                    k++;
                } else {
                    return res;
                }
            } else if (direction == 'b') {

                if (y+1 < n && res[y+1]==null) {
                    res[y+1] = new int[n];
                }
                if (y+1 < n && res[y+1][x] == 0) {
                    res[y+1][x] = k;
                    y++;
                    k++;
                } else if (x-1>=0 && res[y][x-1] == 0) {
                    direction = 'l';
                    res[y][x-1] = k;
                    x--;
                    k++;
                } else {
                    return res;
                }
            } else if (direction == 'l') {
                if (x-1 >= 0 && res[y][x-1] == 0) {
                    res[y][x-1] = k;
                    x--;
                    k++;
                } else if (y-1>=0 && res[y-1][x] == 0) {
                    direction = 't';
                    res[y-1][x] = k;
                    y--;
                    k++;
                } else {
                    return res;
                }
            } else if (direction == 't') {
                if (y-1 >= 0 && res[y-1][x] == 0) {
                    res[y-1][x] = k;
                    y--;
                    k++;
                } else if (x+1 < n && res[y][x+1] == 0) {
                    direction = 'r';
                    res[y][x+1] = k;
                    x++;
                    k++;
                } else {
                    return res;
                }
            }

        }
    }
}
