import java.io.IOException;

public class Easy {
  
    private RemoveRandomly rr ;
    private int[][] easyContent ;
    private loadFromFile loader = new loadFromFile();

    public Easy() throws IOException {
        this.rr = new RemoveRandomly() ;
        this.easyContent = rr.removeRandomly(10) ;
        loader.saveToFile("levels/easy.csv",this.easyContent) ;
        
    }

    
} 
