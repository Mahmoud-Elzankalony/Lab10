import java.io.IOException;

public class VerifyCell
{
    public boolean verify(int row, int column) throws IOException {
        loadFromFile file = new loadFromFile();
        file.content = file.getContent("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv") ;

        unitCheck u = new unitCheck();

        boolean flag_row = u.isValid(file.getRow(row));
        boolean flag_column = u.isValid(file.getColumn(column));
        boolean flag_box = u.isValid(u.get_box_from_row_and_column(row, column));

        if (flag_row && flag_column && flag_box) {
            return true;
        } else {
            return false;
        }
    }
}
