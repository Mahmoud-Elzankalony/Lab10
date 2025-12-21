import java.io.IOException;

public class Medium {

     private RemoveRandomly rr ;
    private int[][] mediumContent ;
    private loadFromFile loader = new loadFromFile();

    public Medium() throws IOException {
        this.rr = new RemoveRandomly() ;
        this.mediumContent = rr.removeRandomly(20) ;
        loader.saveToFile("levels/medium.csv",this.mediumContent) ;
        
    }
    
}
