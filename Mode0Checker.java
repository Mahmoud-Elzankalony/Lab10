import java.io.IOException;

public  class Mode0Checker {

   private loadFromFile loader = new loadFromFile();
   private final String filename ;
   
    public Mode0Checker(String filename) throws IOException {
         this.filename = filename;
         loader.getContent(filename) ;
        
    }


    public void check() {
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

    }

}
