import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;



import static java.nio.file.Files.readAllLines;

public class loadFromFile
{
    private String filename ;
    int[][] content ;



    public int[][] getContent(String filename) throws IOException {
        this.filename = filename;
        int[][] sudokuBoard = new int[9][9];
        String line;
        int row = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {

            while ((line = br.readLine()) != null) {

                // Split line into values
                String[] values = line.split(",");

                // Parse numbers in this row
                for (int col = 0; col < 9; col++) {
                    try {
                        sudokuBoard[row][col] = Integer.parseInt(values[col].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "Invalid formatting in row "+(row + 1) +" and Col " + (col + 1));
                    }
                }
                // move to next row
                row++;
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + this.filename, e);
        }

        this.content = sudokuBoard;
        return sudokuBoard;
    }

    public int[] getRow ( int index )
    {
        return this.content[index] ;
    }
    public int[] getColumn ( int index )
    {
        int [] column = new int [9] ;
        for ( int i = 0 ; i < 9 ; i++ )
        {
            column[i] = this.content[i][index] ;
        }
        return column ;
    }
    public int[] getBox ( int index )
    {
        int [] box = new int [9] ;
        int rowStart = ( index / 3 ) * 3 ;
        int colStart = ( index % 3 ) * 3 ;
        int count = 0 ;
        for ( int i = rowStart ; i < rowStart + 3 ; i++ )
        {
            for ( int j = colStart ; j < colStart + 3 ; j++ )
            {
                box[count] = this.content[i][j] ;
                count++ ;
            }
        } 
        return box ;
    }

    public void saveToFile ( String filename, int[][] content ) throws IOException
    {
        StringBuilder sb = new StringBuilder() ;
        for ( int i = 0 ; i < 9 ; i ++ )
        {
            for ( int j = 0 ; j < 9 ; j ++ )
            {
                sb.append(content[i][j]) ;
                if ( j != 8 )
                {
                    sb.append(",") ;
                }
            }
            sb.append("\n") ;
        }
        java.nio.file.Files.writeString(Paths.get(filename), sb.toString()) ;
    }
}