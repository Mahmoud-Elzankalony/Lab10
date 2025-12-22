import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        CreateLevels cl = new CreateLevels();
        cl.createLevels();
        
        SudokuFrame gui = new SudokuFrame();
        
    }
}
