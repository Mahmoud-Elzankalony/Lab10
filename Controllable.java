import java.io.IOException;

interface  Controllable
{
boolean[] getCatalog_control();
int[][] getGame(char level) throws NotFoundException, IOException;
void driveGames(String sourcePath) throws SolutionInvalidException, IOException;
// A boolean array which says if a specifc cell is correct or invalid
boolean[][] verifyGame(int[][] game) throws IOException;
// contains the cell x, y and solution for each missing cell
int[][] solveGame(int[][] game) throws InvalidGame, InterruptedException;

// Logs the user action
void logUserAction(logUserAction userAction) throws IOException;


}
