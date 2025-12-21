import java.util.ArrayList;
import java.util.List;

public class Solve {

    public List<int[]> getZeros(int[][] content) {
        List<int[]> zeros = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                if (content[i][j] == 0) {
                   
                    if(i>=0 && i<=2)
                    {
                        if(j>=0 && j<=2)
                        {
                            zeros.add(new int[]{i, j,0}) ;
                        }
                        else if (j>=3 && j<=5)
                        {
                            zeros.add(new int[]{i, j,1}) ;
                        }
                        else if (j>=6 && j<=8)
                        {
                            zeros.add(new int[]{i, j,2}) ;
                        }
                    }
                    else if (i>=3 && i<=5)
                    {
                        if(j>=0 && j<=2)
                        {
                            zeros.add(new int[]{i, j,3}) ;
                        }
                        else if (j>=3 && j<=5)
                        {
                            zeros.add(new int[]{i, j,4}) ;
                        }
                        else if (j>=6 && j<=8)
                        {
                            zeros.add(new int[]{i, j,5}) ;
                        }
                    }
                    else if (i>=6 && i<=8)
                    {
                        if(j>=0 && j<=2)
                        {
                            zeros.add(new int[]{i, j,6}) ;
                        }
                        else if (j>=3 && j<=5)
                        {
                            zeros.add(new int[]{i, j,7}) ;
                        }
                        else if (j>=6 && j<=8)
                        {
                            zeros.add(new int[]{i, j,8}) ;
                        }

                    }
                }
            }
        }
        return zeros;
    }
    
}
