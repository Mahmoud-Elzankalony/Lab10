import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Facade
{

    private final Adapter adapter;
    private final loadFromFile loader;
    private final Undo undoer;
    private final LogFile loger ;
    private final Solve solver;

    public Facade() throws IOException {
        adapter = new Adapter() ;
        loader = new loadFromFile() ;
        undoer = new Undo() ;
        loger = new LogFile() ;
        solver = new Solve() ;
    }
//    public Facade()
//    {
//        this(new Adapter(), new loadFromFile(), new Undo() , new LogFile() , new Solve());
//    }

//    public Facade(Adapter adapter, loadFromFile loader, Undo undoer, LogFile loger, Solve solver)
//    {
////        this.adapter = adapter != null ? adapter : new Adapter();
////        this.loader = loader != null ? loader : new loadFromFile();
////        this.undoer = undoer != null ? undoer : new Undo();
//            this.adapter = adapter;
//            this.loader = loader;
//            this.undoer = undoer;
//            this.loger = loger;
//            this.solver = solver;
//    }

//    public Catalog getCatalog() {
//        try {
//            return adapter.getCatalog_view();
//        } catch (Throwable t) {
//            Catalog c = new Catalog();
//            c.current = false;
//            c.allModesExist = false;
//            return c;
//        }
//    }

    public boolean[] getCatalogControl() {
        return adapter.getCatalog_control();
    }

    public Game getGame(DifficultyEnum level) throws NotFoundException, IOException {
        return adapter.getGame(level);
    }

//    public int[][] getGame(char level) throws NotFoundException, IOException {
//        return adapter.getGame(level);
//    }

//    public void driveGames(Game sourceGame) throws SolutionInvalidException, IOException {
//        adapter.driveGames(sourceGame);
//    }

    public void driveGames(String sourcePath) throws SolutionInvalidException, IOException {
        adapter.driveGames(sourcePath);
    }

    public String verifyGame(Game game) throws IOException {
        return adapter.verifyGame(game);
    }

//    public boolean[][] verifyGame(int[][] game) throws IOException {
//        return adapter.verifyGame(game);
//    }

//    public int[] solveGame(Game game) throws InvalidGame {
//        throw new UnsupportedOperationException("solveGame(Game) not implemented in facade");
//    }
//
//    public int[][] solveGame(int[][] game) throws InvalidGame {
//        throw new UnsupportedOperationException("solveGame(int[][]) not implemented in facade");
//    }

    public void logUserAction(String userAction) throws IOException {
        adapter.logUserAction(userAction);
    }

//    public void logUserAction(logUserAction action) throws IOException {
//        adapter.logUserAction(action);
//    }

    public int[] undoLastAction() throws IOException {
        return undoer.undo();
    }

    public void SaveTOFile (String fileName , int[][] content) throws IOException
    {
        loader.saveToFile(fileName , content);
    }

    public void SaveToLog ( ) throws IOException {
        loger.saveToFile();
    }

    public void AddLineToLog(String line)
    {
        loger.addLine(line);
    }

    public int[][] GetBoardFromFile(String fileName) throws FileNotFoundException, IOException
    {
        return loader.getContent(fileName);
    }

    public ArrayList<int[]> GetZerosFromBoard(int[][] board)
    {
        return (ArrayList<int[]>) solver.getZeros(board);
    }
    public String convert ( int x , int y , String current , int prev )
    {
        return loger.convert(x,y,current,prev);
    }
    public int[][] SolveGame(int[][] board) throws InvalidGame, InterruptedException
    {
        return adapter.solveGame(board) ;
    }

    public String Verify(Game game) throws NotFoundException, SolutionInvalidException, InterruptedException, IOException {
        return adapter.verifyGame(game) ;
    }
}