import java.io.IOException;

public class Medium extends Level {

    public Medium() throws IOException
    {
        this.rr = new RemoveRandomly() ;
        this.Content = rr.removeRandomly(20) ;
        loader.saveToFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\medium.csv",this.Content) ;
    }
}
