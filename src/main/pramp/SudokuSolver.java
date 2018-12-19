public class SudokuSolver {
    static boolean sudokuSolve(char[][] board) {
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
            board[r][c] = Character.forDigit(i, 10);
            if (sudokuSolve(board)) {
                return true;
            } else {
                board[r][c] = '.';
            }
        }

        return  false;
    }

    /**
     * Check if candidate is eligible for cell
     * @param board
     * @param r
     * @param c
     * @param i
     * @return
     */
    public static boolean isEligible(char[][] board, int r, int c, int i) {
        for (int j = 0; j < board.length; j++) {
            if (Character.getNumericValue(board[r][j]) == i) {
                return false;
            }
            if (Character.getNumericValue(board[j][c]) == i) {
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
                if (Character.getNumericValue(board[y][x]) ==  i) {
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
    public static int[] getEmpty(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '.') {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void printSudoku(char[][] board) {
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
        char[][] sample = new char[][] {
            {'2', '9', '5',     '7', '4', '3',      '.', '.', '.'},
            {'4', '3', '1',     '.', '6', '.',      '4', '8', '.'},
            {'8', '7', '6',     '.', '7', '.',      '.', '6', '9'},

            {'.', '3', '.',     '.', '9', '.',      '8', '.', '.'},
            {'.', '5', '1',     '.', '.', '.',      '.', '9', '.'},
            {'.', '.', '.',     '6', '.', '8',      '.', '5', '7'},

            {'.', '1', '.',     '3', '8', '.',      '.', '.', '2'},
            {'3', '.', '.',     '.', '.', '7',      '9', '.', '6'},
            {'7', '.', '2',     '.', '.', '.',      '3', '.', '.'}
        };

        char[][] sample1 = new char[][] {
            {'.', '4', '.'},
            {'5', '3', '.'},
            {'.', '.', '6'}
        };
       // https://dingo.sbs.arizona.edu/~sandiway/sudoku/wildcatjan17.gif
        char[][] sample3 = new char[][] {
            {'.', '.', '5',     '2', '.', '.',      '7', '4', '.'},
            {'.', '.', '.',     '.', '5', '.',      '6', '.', '9'},
            {'9', '6', '2',     '.', '.', '7',      '.', '.', '.'},

            {'.', '4', '3',     '.', '.', '2',      '.', '.', '8'},
            {'.', '.', '8',     '.', '.', '.',      '9', '.', '2'},
            {'.', '.', '6',     '5', '1', '8',      '.', '.', '.'},

            {'6', '.', '.',     '.', '4', '.',      '.', '5', '.'},
            {'.', '7', '.',     '.', '8', '.',      '2', '.', '.'},
            {'8', '5', '.',     '6', '.', '.',      '.', '3', '.'}
        };
        SudokuSolver.printSudoku(sample3);
        System.out.println(SudokuSolver.sudokuSolve(sample3));
//        System.out.println(Character.forDigit(1, 10));

    }

}
