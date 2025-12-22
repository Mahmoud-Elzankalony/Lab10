import java.io.IOException;

public class Level
{
    RemoveRandomly rr ;
    int[][] Content ;
    loadFromFile loader = new loadFromFile();

    public Level() throws IOException
    {
        this.rr = new RemoveRandomly() ;
        this.loader = new loadFromFile();
    }
}
