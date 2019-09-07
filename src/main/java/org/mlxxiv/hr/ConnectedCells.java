package org.mlxxiv.hr;


import java.util.ArrayList;

public class ConnectedCells {
    public static void main(String[] args) {
        int[][] test1 = new int[][]{
            {1,1,0,0},
            {0,1,1,0},
            {0,0,1,0},
            {1,0,0,0}
        };
        int res1 = 5;
        System.out.println(connectedCell(test1) == res1);
    }


    static int connectedCell(int[][] matrix) {
        int result = 0;

        return result;
    }

    static class cell {
        ArrayList<cell> connections = new ArrayList<>();
        boolean visited = false;
        final int positionX;
        final int positionY;

        public cell(int row, int coll) {
            this.positionX = coll;
            this.positionY = row;
        }

        void connect(cell connection) {
            connections.add(connection);
        }
    }
}
