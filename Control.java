import java.io.IOException;
import java.util.ArrayList;

public class Control implements Controllable
{
    @Override
    public boolean[] getCatalog() {
        return new boolean[0];
    }

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        return new int[0][];
    }

    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException {

    }

    @Override
    public boolean[][] verifyGame(int[][] game) {
        return new boolean[0][];
    }

    @Override
    public int[][] solveGame(int[][] game) throws InvalidGame {
        return new int[0][];
    }

    @Override
    public int[] undo() throws IOException
    {
        LogFile f = new LogFile();

        String lastLine = f.returnLastLine() ;

        int[] arr = new int[4] ;
        int index = 0 ;

        int i = 0 ;
        while(i < lastLine.length())
        {
            Character c = lastLine.charAt(i++);
            if (c != '(' && c != ')' && c != ',' && c != ' ')
            {
                arr[index++] = Integer.parseInt(c.toString()) ;
            }
        }
        f.removeLastLine();
        return arr ;
    }

    @Override
    public void logUserAction(UserAction userAction) throws IOException {

    }
}
