public class VerifySolver {


    public boolean verify(int row,
                          int col,
                          int[][] board,
                          int[] emptyCells,
                          int combination) {

        int value = getCellValue(row, col, board, emptyCells, combination);
        if (value == 0) return true;


        for (int j = 0; j < 9; j++) {
            if (j == col) continue;
            if (value == getCellValue(row, j, board, emptyCells, combination))
                return false;
        }


        for (int i = 0; i < 9; i++) {
            if (i == row) continue;
            if (value == getCellValue(i, col, board, emptyCells, combination))
                return false;
        }


        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int i = boxRow; i < boxRow + 3; i++)
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (i == row && j == col) continue;
                if (value == getCellValue(i, j, board, emptyCells, combination))
                    return false;
            }

        return true;
    }


    private int getCellValue(int row,
                             int col,
                             int[][] board,
                             int[] emptyCells,
                             int combination) {

        for (int i = 0; i < emptyCells.length; i++) {
            int r = emptyCells[i] / 10;
            int c = emptyCells[i] % 10;

            if (r == row && c == col) {
                return getDigitFromCombination(combination, i);
            }
        }
        return board[row][col];
    }


    private int getDigitFromCombination(int combination, int index) {
        for (int i = 0; i < 4 - index; i++)
            combination /= 10;

        return combination % 10;
    }
}