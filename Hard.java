import java.io.IOException;

public class Hard {

     private RemoveRandomly rr ;
    private int[][] hardContent ;
    private loadFromFile loader = new loadFromFile();

    public Hard() throws IOException {
        this.rr = new RemoveRandomly() ;
        this.hardContent = rr.removeRandomly(25) ;
        loader.saveToFile("levels/hard.csv",this.hardContent) ;
        
    }
    
}
