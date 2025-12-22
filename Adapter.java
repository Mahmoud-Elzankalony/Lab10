import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Adapter implements Controllable , Viewable
{
    /**********************************************************/


    /**********************************************************/

    @Override
    public boolean[] getCatalog_control() {
        Catalog c = getCatalog_view();
        return new boolean[]{c.current, c.allModesExist};
    }

    @Override
    public Catalog getCatalog_view() {
        Catalog c = new Catalog();
        // Logical check for existing games/modes would go here
        c.current = false;
        c.allModesExist = true;
        return c;
    }

    /**********************************************************/

    @Override
    public Game getGame(DifficultyEnum level) throws NotFoundException, IOException
    {
        try
        {
            LevelFactory f = new LevelFactory();
            Character l = f.GetDifficulty(level) ;
            return new Game(getGame(l) , l) ;
        }
        catch(NotFoundException e)
        {
            throw new NotFoundException("it can't get the game");
        }
        catch (IOException e)
        {
            throw new IOException();
        }
    }
    @Override
    public int[][] getGame(char level) throws NotFoundException, IOException
    {
        try
        {
            LevelFactory f =  new LevelFactory();
            return f.GetLevel(level).Content ;
        }
        catch(NotFoundException e)
        {
            throw new NotFoundException("it can't get the game");
        }
        catch (IOException e)
        {
            throw new IOException();
        }
    }

    /**********************************************************/

    @Override
    public void driveGames(Game sourceGame) throws SolutionInvalidException, IOException
    {
        try
        {
            new CreateLevels() ;
        }
        catch ( IOException e )
        {
            throw new IOException() ;
        }
        catch (SolutionInvalidException e)
        {
            throw new SolutionInvalidException("it can't drive levels");
        }
    }
    @Override
    public void driveGames(String sourcePath) throws SolutionInvalidException, IOException
    {
        try
        {
            loadFromFile file = new loadFromFile() ;
            Sudokugamedriver s = new  Sudokugamedriver(sourcePath) ;
            s.verify_source_solution();
            driveGames(new Game(file.getContent(sourcePath),null));
        }
        catch ( IOException e )
        {
            throw new IOException() ;
        }
        catch (SolutionInvalidException e)
        {
            throw new SolutionInvalidException("it can't drive levels");
        }
    }

    /**********************************************************/

    @Override
    public String verifyGame(Game game) throws IOException {

        unitCheck u = new unitCheck();


        if (!u.isComplete(game.board)) {
            return "INCOMPLETE GAME";
        }


        boolean[][] arr = verifyGame(game.board);
        ArrayList<String> invalidLocations = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!arr[i][j]) {
                    invalidLocations.add(i + "," + j);
                }
            }
        }


        if (invalidLocations.isEmpty()) {
            return "VALID GAME";
        } else {
//            for (String loc : invalidLocations) {
//                System.out.println(loc);
//            }
            new Mode0Checker("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv").check();
            return "INVALID GAME";
        }
    }

    @Override
    public boolean[][] verifyGame(int[][] game) throws IOException {
        boolean[][] ret = new boolean[9][9];
        VerifyCell v = new VerifyCell();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Pass the current game board for verification
                ret[i][j] = v.verify(i, j);
            }
        }
        return ret;
    }

    /**********************************************************/

    @Override
    public int[] solveGame(Game game) throws InvalidGame, InterruptedException {
        Solver s = new Solver() ;
        return s.solve(game) ;
    }
    @Override
    public int[][] solveGame(int[][] game) throws InvalidGame, InterruptedException
    {
        int[] comb = solveGame(new Game(game,null)) ;
        int k = 0 ;
        Solver s = new Solver() ;
        for ( int i = 0 ; i < 9 ; i++ )
        {
            for (  int j = 0 ; j < 9 ; j++ )
            {
                if (game[i][j] == 0)
                {
                    game[i][j] = comb[k++] ;
                }
            }
        }
        return game;
    }

    /**********************************************************/

    @Override
    public void logUserAction(String userAction) throws IOException
    {
        try
        {
            LogFile file = new LogFile() ;
            file.loadFromFile();
            file.getContent().add(userAction) ;
            file.saveToFile();
        }
        catch ( IOException e )
        {
            throw new IOException() ;
        }
    }
    @Override
    public void logUserAction(logUserAction userAction) throws IOException
    {

    }

    /**********************************************************/

}