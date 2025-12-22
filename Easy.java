import java.io.IOException;

public class Easy extends Level {

    public Easy() throws IOException
    {
        this.rr = new RemoveRandomly() ;
        this.Content = rr.removeRandomly(10) ;
        loader.saveToFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\easy.csv",this.Content) ;
    }
} 
