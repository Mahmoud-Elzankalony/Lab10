public class combinations {
    private final int[][]board;
    private int mycombination;
    public combinations(int[][] board) {
        this.board = board;
    }

    public int getMycombination() {
        return mycombination;
    }

    public void setMycombination(int mycombination) {
        this.mycombination = mycombination;
    }

    public int[][] getBoard() {
        return board;
    }

}