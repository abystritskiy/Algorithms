public class SudokuSoverInt {
    static boolean sudokuSolve(int[][] board) {
        int[] empty = getEmpty(board);
        int r = empty[0];
        int c = empty[1];

        if (r==-1 && c==-1) {
            printSudoku(board);
            return true;
        }

        for (int i=1; i<=9; i++) {
            if (!isEligible(board, r, c, i)) {
                continue;
            }
            board[r][c] = i;

            if (sudokuSolve(board)) {
                return true;
            } else {
                board[r][c] = 0;
            }
        }
        return false;
    }

    /**
     * Check if candidate is eligible for cell
     * @param board
     * @param r
     * @param c
     * @param i
     * @return
     */
    public static boolean isEligible(int[][] board, int r, int c, int i) {
        for (int j = 0; j < board.length; j++) {
            if (board[r][j] == i) {
                return false;
            }
            if (board[j][c] == i) {
                return false;
            }
        }

        int rowMin, rowMax, colMin, colMax;
        if (r < 3) {
            rowMin = 0;
            rowMax = 2;
        } else if (r >= 3 && r < 6) {
            rowMin = 3;
            rowMax = 5;
        } else {
            rowMin = 6;
            rowMax = 8;
        }

        if (c < 3) {
            colMin = 0;
            colMax = 2;
        } else if (c >= 3 && c < 6) {
            colMin = 3;
            colMax = 5;
        } else {
            colMin = 6;
            colMax = 8;
        }

        for (int y=rowMin; y<=rowMax; y++) {
            for (int x=colMin; x<=colMax; x++) {
                if (board[y][x] ==  i) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return next empty cell
     *
     * @param board
     * @return
     */
    public static int[] getEmpty(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void printSudoku(int[][] board) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                System.out.print(board[i][j] + " ");
                if ((j+1)%3 == 0) {
                    System.out.print("\t");
                }
            }
            if ((i+1)%3 == 0) {
                System.out.println("\n");
            } else {
                System.out.println("");
            }
        }
        System.out.println("\n-----------------\n");
    }

    public static void main(String[] args) {
        int[][] sample = new int[][] {
                {2, 9, 5,     7, 4, 3,      0, 0, 0},
                {4, 3, 1,     0, 6, 0,      4, 8, 0},
                {8, 7, 6,     0, 7, 0,      0, 6, 9},

                {0, 3, 0,     0, 9, 0,      8, 0, 0},
                {0, 5, 1,     0, 0, 0,      0, 9, 0},
                {0, 0, 0,     6, 0, 8,      0, 5, 7},

                {0, 1, 0,     3, 8, 0,      0, 0, 2},
                {3, 0, 0,     0, 0, 7,      9, 0, 6},
                {7, 0, 2,     0, 0, 0,      3, 0, 0}
        };

        int[][] sample1 = new int[][] {
                {0, 4, 0},
                {5, 3, 0},
                {0, 0, 6}
        };
        // https://dingo.sbs.arizona.edu/~sandiway/sudoku/wildcatjan17.gif
        int[][] sample3 = new int[][] {
                {4, 3, 0,     2, 6, 0,      7, 0, 1},
                {6, 0, 2,     0, 7, 1,      0, 9, 0},
                {1, 9, 0,     8, 0, 4,      5, 0, 0},

                {8, 2, 0,     0, 9, 5,      0, 4, 0},
                {3, 0, 0,     0, 8, 2,      9, 0, 0},
                {9, 0, 1,     7, 4, 0,      0, 2, 8},

                {0, 0, 9,     3, 0, 0,      0, 7, 4},
                {0, 4, 0,     0, 5, 0,      0, 3, 6},
                {7, 0, 0,     0, 1, 8,      0, 0, 0}
        };


        String[] str = "3 0 6 5 0 8 4 0 0 5 2 0 0 0 0 0 0 0 0 8 7 0 0 0 0 3 1 0 0 3 0 1 0 0 8 0 9 0 0 8 6 3 0 0 5 0 5 0 0 9 0 6 0 0 1 3 0 0 0 0 2 5 0 0 0 0 0 0 0 0 7 4 0 0 5 2 0 6 3 0 0".split(" ");
        int[][] sample4 = new int[9][9];
        int j=0;
        for (int y=0; y<9; y++) {
            for (int x=0; x<9; x++) {
                String s = str[j];
                j++;
                if (s.length()>0) {
                    sample4[y][x] = Integer.parseInt(s);
                }
            }
        }


        int[][] sample5 = new int[][] {
                {0, 0, 5,     2, 0, 0,      7, 4, 0},
                {0, 0, 0,     0, 5, 0,      6, 0, 9},
                {9, 6, 2,     0, 0, 7,      0, 0, 0},

                {0, 4, 3,     0, 0, 2,      0, 0, 8},
                {0, 0, 8,     0, 0, 0,      9, 0, 2},
                {0, 0, 6,     5, 1, 8,      0, 0, 0},

                {6, 0, 0,     0, 4, 0,      0, 5, 0},
                {0, 7, 0,     0, 8, 0,      2, 0, 0},
                {8, 5, 0,     6, 0, 0,      0, 3, 0}
        };

        SudokuSoverInt.printSudoku(sample4);
        System.out.println(SudokuSoverInt.sudokuSolve(sample5));
//        System.out.println(Character.forDigit(1, 10));


    }
}
