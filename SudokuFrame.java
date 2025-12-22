import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.swing.event.*;

public class SudokuFrame extends JFrame {

    private JTextField[][] cells = new JTextField[9][9];
    private JButton solveButton = new JButton("Solve");
    private JButton verifyButton = new JButton("Verify");
    private JButton undoButton = new JButton("Undo");
    private JButton easyButton = new JButton("Easy");
    private JButton mediumButton = new JButton("Medium");
    private JButton hardButton = new JButton("Hard");
    private static int[][] prevBoard;
    private Facade f;
    private boolean isUpdating = false;

    public SudokuFrame() throws IOException {
        this.f = new Facade();
        setTitle("Sudoku");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        homeList();

        setVisible(true);
    }

    public void sudokuBoard(int[][] board) throws IOException {
        prevBoard = board;
        getContentPane().removeAll();

        setTitle("Sudoku");
        setSize(500, 500);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);

                if (board[row][col] != 0) {
                    cell.setText(String.valueOf(board[row][col]));
                    cell.setEditable(false);
                    cell.setBackground(new Color(220, 220, 220));
                } else {
                    addCellListener(cell, row, col);
                }

                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (row == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;

                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                cells[row][col] = cell;
                grid.add(cell);
            }
        }

        add(grid, BorderLayout.CENTER);
        allButtons();
        updateSolveButton();
        revalidate();
        repaint();
    }

    private int[][] readFromBoard() {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText().trim();
                board[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
            }
        }
        return board;
    }

    public void writeToBoard(int[][] board) {
        for (int i = 0; i < 9; i++)
        {
            for  (int j = 0; j < 9; j++)
            {
                cells[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    public void allButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(solveButton);
        buttonPanel.add(verifyButton);
        buttonPanel.add(undoButton);

        solveButton.addActionListener(e -> {
            try { onSolve(); } catch (IOException | InvalidGame | InterruptedException ex) { ex.printStackTrace(); }
        });
        verifyButton.addActionListener(e -> {
            try { onVerify(); } catch (IOException ex) { ex.printStackTrace(); }
        });
        undoButton.addActionListener(e -> {
            try { onUndo(); } catch (IOException ex) { ex.printStackTrace(); }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void onSolve() throws IOException, InvalidGame, InterruptedException
    {
        int[][] board = f.SolveGame(readFromBoard());
        writeToBoard(board);
    }

    public void onVerify() throws IOException
    {
       System.out.println( f.verifyGame(new Game(readFromBoard(),null))); ;
    }

    public void onUndo() throws IOException {
//        int row = 0 ;
//        int col = 0 ;
//        int val = 0 ;
//
//        int[] lastAction = f.undoLastAction();
//        if (lastAction != null) {
//            row = lastAction[0];
//            col = lastAction[1];
//            val = lastAction[2];
//            cells[row][col].setText(val == 0 ? "" : String.valueOf(val));
//        }
//        f.GetBoardFromFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv")[row][col] = prevBoard[row][col];
        int[] lastAction = f.undoLastAction() ;


        if(lastAction!=null){
            int row = lastAction[0];
            int col = lastAction[1];
            int current = lastAction[2];
            int prev = lastAction[3];

            if (prev == '0'||Undo.consprev=='0' ) {
                cells[row][col].setText(null);
            } else {
                isUpdating = true;
                cells[row][col].setText(String.valueOf(Undo.consprev));
                isUpdating = false;
            }


        }
        else
        {
            int row = lastAction[0];
            int col = lastAction[1];

            cells[row][col].setText(null);
        }



//        prevBoard = readFromBoard();

        //addCellListener(cells[row][col], row, col);
        //sudokuBoard(readFromBoard());
    }

    private void addCellListener(JTextField cell, int row, int col) {
        if (isUpdating) { return ;}
        cell.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { refresh(); }
            public void removeUpdate(DocumentEvent e) { refresh(); }
            public void changedUpdate(DocumentEvent e) { refresh(); }

            private void refresh() {
                try {
                    if(isUpdating){return;}
                    int[][] currentBoard = readFromBoard();
                    //String newValue = cell.getText();
                    String newValue ;
                    String temp = cell.getText();
                    if ( temp.isEmpty() )
                    {
                        newValue = "0";
                    }
                    else
                    {
                        newValue = temp;
                    }

                    f.SaveTOFile("levels/current.csv", currentBoard);
                    updateSolveButton();

                    f.AddLineToLog(f.convert(row,col,newValue,prevBoard[row][col]));
                    f.SaveToLog();
                    prevBoard = currentBoard;


                } catch (IOException ignored) {}
            }
        });
    }

    public void updateSolveButton() throws IOException {
        int[][] content = f.GetBoardFromFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv");
        List<int[]> zeros = f.GetZerosFromBoard(content);
        solveButton.setEnabled(zeros.size() == 5);
    }

    public void homeList() throws IOException {
        try {
            int[][] saved = f.GetBoardFromFile("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\current.csv");
            int choice = JOptionPane.showConfirmDialog(this, "There is a game in progress. Do you want to continue?", "Game in Progress", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                sudokuBoard(saved);
                return;
            }
        } catch (Exception ignored) {}

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to Sudoku Game!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        homePanel.add(Box.createVerticalStrut(20));
        homePanel.add(welcomeLabel);
        homePanel.add(Box.createVerticalStrut(30));

        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homePanel.add(easyButton);
        homePanel.add(Box.createVerticalStrut(15));
        homePanel.add(mediumButton);
        homePanel.add(Box.createVerticalStrut(15));
        homePanel.add(hardButton);

        easyButton.addActionListener(e -> onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\easy.csv"));
        mediumButton.addActionListener(e -> onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\medium.csv"));
        hardButton.addActionListener(e -> onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\hard.csv"));

        add(homePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void onDifficultySelected(String path) {
        try {
            sudokuBoard(f.GetBoardFromFile(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onEasy() throws IOException { onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\easy.csv"); }
    public void onMedium() throws IOException { onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\medium.csv"); }
    public void onHard() throws IOException { onDifficultySelected("D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\hard.csv"); }
}