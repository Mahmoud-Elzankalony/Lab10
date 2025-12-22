import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class unitCheck

{
    private  boolean checkValidity = true;
    private  boolean checkComplete= true;
    HashMap<Integer,ArrayList<Integer>> mapLocations = new HashMap<>();
    HashMap<Integer,Integer> mapValues = new HashMap<>();
    public boolean isValid (int[] numbers)
    {

        for ( int i = 1 ; i < 10 ; i++ )
        {
            mapValues.put( i , 0 );
            mapLocations.put( i , new ArrayList<Integer>() ) ;
        }
        int flag = 1 ;
        for ( int i = 0; i < numbers.length ; i++ )
        {
            if ( numbers[i] == 0 )
            {
                continue;
            }
            else
            {
                if (mapValues.get(numbers[i]) == 0 )
                {
                    mapValues.put( numbers[i], 1 );
                }
                else
                {
                    mapValues.put( numbers[i], mapValues.get(numbers[i]) + 1 );
                    flag = 0 ;
                }
                mapLocations.get(numbers[i]).add( i+1 );
            }
        }
       
        if ( flag == 1 )
        {
            return true ;
        }
        else
        {
            if(checkValidity== true)
            {
                //System.out.println("Invalid");
                checkValidity=false;
            }
            return false ;
        }
    }

    public void printPattern ( int type , int index , int duplicateNum , ArrayList<Integer> Locations )
    {
        String t = null ;
        switch ( type )
        {
            case 0 :
                t = "Row" ;
                break;
            case 1 :
                t = "Column" ;
                break;
            case 2 :
                t = "Box" ;
                break;
        }

        
        System.out.println( t + " " + (index+1) + " " + "#" + duplicateNum + " " + Locations ); ;
    }

    public  boolean isCheckValidity() {
        return checkValidity;
    }

    public  boolean isCheckComplete() {
        return checkComplete;
    }

    public void printAllsudokuDublicate(int type, int index)
    {


         for ( int i = 1 ; i < 10 ; i++ )
        {
            if ( mapValues.get(i) > 1 )
            {
                printPattern( type , index , i , mapLocations.get(i) );
            }
        }
    }

    public boolean isComplete(int[][] board){
            for(int i =0 ; i<9 ; i++)
                for (int j=0 ; j<9 ; j++)
                    if(board[i][j]==0)
                    {this.checkComplete=false;
                        return false;}
            return true;
    }

    public int[] get_box_from_row_and_column(int row, int column) throws IOException {
        loadFromFile file =  new loadFromFile();
        file.getContent("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv");

        if ( row >= 0 & row <= 2 )
        {
            if ( column >= 0 & column <=2 )
            {
                return file.getBox(0);
            }
            else if ( column >= 3 & column <=5 )
            {
                return file.getBox(1);
            }
            else
            {
                return file.getBox(2);
            }
        }
        else if ( row >= 3 & row <=5 )
        {
            if ( column >= 0 & column <=2 )
            {
                return file.getBox(3);
            }
            else if ( column >= 3 & column <=5 )
            {
                return file.getBox(4);
            }
            else
            {
                return file.getBox(5);
            }
        }
        else
        {
            if ( column >= 0 & column <=2 )
            {
                return file.getBox(6);
            }
            else if ( column >= 3 & column <=5 )
            {
                return file.getBox(7);
            }
            else
            {
                return file.getBox(8);
            }
        }
    }
}