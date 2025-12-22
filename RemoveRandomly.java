import java.io.IOException;
import java.util.List;

public class RemoveRandomly {
    
  private loadFromFile loader = new loadFromFile();
  private int[][] content;

    public RemoveRandomly() throws IOException {
     this.content=loader.getContent("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\test1_valid.csv") ;
    
    }

    public int[][] removeRandomly(int n) throws IOException
    {

        RandomPairs rp = new RandomPairs();
        List<int[]> result = rp.generateDistinctPairs(n);
        int row , col;
        for (int i = 0 ; i< result.size() ; i++ )
        {
        row = result.get(i)[0] ;
        col = result.get(i)[1] ;
        this.content[row][col] = 0 ;
    
        }
        
        return this.content ;
    }

     public void displayContent() {

    
    for (int i = 0; i < this.content.length; i++) {
      for (int j = 0; j < this.content[i].length; j++) {
        System.out.print(this.content[i][j] + " ");
      }
      System.out.println();
      
}}


}
