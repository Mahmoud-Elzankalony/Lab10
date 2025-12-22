import java.io.IOException;

public class Sudokugamedriver {
    private int[][] board;
    private loadFromFile loader;
    public Sudokugamedriver(String source) {
        loader = new loadFromFile();
        try {
            board=loader.getContent(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void verify_source_solution()
    {
        unitCheck check = new unitCheck();

        for ( int i = 0 ; i < 9 ; i ++ )
        {
            if(!check.isValid(loader.getRow(i)) )
            {
                check .printAllsudokuDublicate(0,i) ;
            }


        }

        for ( int i = 0 ; i < 9 ; i ++ )
        {
            if(!check.isValid(loader.getColumn(i)) )
            {
                check.printAllsudokuDublicate(1,i); ;
            }

        }
        for ( int i = 0 ; i < 9 ; i ++ )
        {
            if(!check.isValid(loader.getBox(i)) )
            {
                check.printAllsudokuDublicate(2,i) ;
            }
        }

        if(check.isComplete(board)==false )
            throw new RuntimeException("the source game is not VALID ");
        if(check.isCheckValidity()==false)
            throw new RuntimeException("the source game is incomplete");

    }
}
