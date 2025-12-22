import java.util.HashMap;

public class combinationsFactory {
    private static final HashMap<int[][],combinations> combinationsMap = new HashMap();

    public static combinations getcombinatio(int[][] board) {
        combinations combination = combinationsMap.get(board);
        if(combination == null) {
            combination = new combinations(board);
            combinationsMap.put(board, combination);
        }
        return combination;
    }
}