import java.util.ArrayList;
import java.util.List;

public class Solve {

    public List<int[]> getZeros(int[][] content) {
        List<int[]> zeros = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                if (content[i][j] == 0) {
                    zeros.add(new int[]{i, j, (i / 3) * 3 + (j / 3)});
                }
            }
        }
        return zeros;
    }
    
}
