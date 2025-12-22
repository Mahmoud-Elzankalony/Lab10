import java.io.IOException;

public class Undo
{
    public static char consprev ;
    public int[] undo() throws IOException
    {
        LogFile f = new LogFile();
        f.loadFromFile();


        String lastLine = f.returnLastLine() ;
        String beforeLastLine = f.returnBeforeLastLine() ;

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
        if(beforeLastLine==null){

            consprev='0';
            return null;

        }
        if ( beforeLastLine.charAt(5) == '0' )
        {
            consprev=beforeLastLine.charAt(7);
            f.removeLastLine();
            f.removeLastLine();
        }
        else
        {
            f.removeLastLine();
        }
        f.saveToFile();
        return arr ;
        
    }
}