import java.io.IOException;

interface  Controllable
{
boolean[] getCatalog();
int[][] getGame(char level) throws NotFoundException;
void driveGames(String sourcePath) throws SolutionInvalidException;
// A boolean array which says if a specifc cell is correct or invalid
boolean[][] verifyGame(int[][] game);
// contains the cell x, y and solution for each missing cell
int[][] solveGame(int[][] game) throws InvalidGame;

int[] undo() throws IOException;
// Logs the user action
void logUserAction(UserAction userAction) throws IOException;
}
