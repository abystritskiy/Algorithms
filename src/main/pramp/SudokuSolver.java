import java.util.*;

/*
5 3 1
7 2 .
.  9 8   7 5 2

//getCandidates //1,2 4,6
//backtracking

/*
    # For some empty cell board[row][col], what possible
    # characters can be placed into this cell
    # that aren't already placed in the same row,
    # column, and sub-board?
   function getCandidates(board, row, col):
*/


public class SudokuSolver {
    static boolean sudokuSolve(char[][] board) {
        int[] empty = getEmpty(board);
        int r = empty[0];
        int c = empty[1];

        char[][] boardCopy = board.clone();

        if (r == -1 && c == -1) {
//            printSudoku(boardCopy);
            return true;
        } else {
            List<Integer> candidates = getCandidates(boardCopy, r, c);

            if (candidates.size() == 0) {
                return false;
            } else {
                int candidate = candidates.get(0);

//                for (int candidate: candidates) {
                    board[r][c] = Character.forDigit(candidate, 10);
                    sudokuSolve(boardCopy);
//                }
            }

        }

        return false;

    }

    /**
     * Get possible values than can fit cell (not in the same row, column or sub-sudoku)
     *
     * @param board
     * @param r
     * @param c
     * @return
     */
    public static List<Integer> getCandidates(char[][] board, int r, int c) {
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (isEligible(board, r, c, i)) {
                candidates.add(i);
            }
        }
        return candidates;
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
        if (i  == 2) {
            int bp = 0;
        }
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
            }
            System.out.println("\n");
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
            {'.', '3', '.'},
            {'5', '.', '.'},
            {'.', '.', '2'}
        };

        char[][] sample3 = new char[][] {
            {'5', '3', '.',     '.', '7', '.'},
            {'6', '.', '.',     '1', '9', '5'},
            {'.', '9', '8',     '.', '.', '.'},

            {'8', '.', '.',     '.', '6', '.'},
            {'4', '.', '.',     '8', '.', '3'},
            {'7', '.', '.',     '.', '2', '.'}
        };
//        SudokuSolver.printSudoku(sample1);
        System.out.println(SudokuSolver.sudokuSolve(sample1));
//        System.out.println(Character.forDigit(1, 10));

    }

}
