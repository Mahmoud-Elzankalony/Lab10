import java.io.IOException;

public class Hard extends Level
{
    public Hard() throws IOException
    {
        this.rr = new RemoveRandomly() ;
        this.Content = rr.removeRandomly(25) ;
        loader.saveToFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\hard.csv",this.Content) ;
    }
}