import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
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
    private loadFromFile loader = new loadFromFile();
 
   


    public SudokuFrame() throws IOException {

    setTitle("Sudoku");
    setSize(500, 300);           
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    homeList();

    setVisible(true);
}

    

public void sudokuBoard(int [][]board) throws IOException
{
    setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
                }
                else {
                    addCellListener(cell);
                }
               

              
                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (row == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;

                cell.setBorder(
                        BorderFactory.createMatteBorder(
                                top, left, bottom, right, Color.BLACK)
                );

                cells[row][col] = cell;
                grid.add(cell);
            }
        }

        add(grid);
        setVisible(true);
        allButtons();
        updateSolveButton();
    }

    private int[][] readFromBoard() {

    int[][] board = new int[9][9];

    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {

            String text = cells[row][col].getText().trim();

            if (text.isEmpty()) {
                board[row][col] = 0;
            } else {
                board[row][col] = Integer.parseInt(text);
            }
        }
    }
    return board;
}


    public void allButtons(){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        

        buttonPanel.add(solveButton);
        buttonPanel.add(verifyButton);
        buttonPanel.add(undoButton);
       

        solveButton.addActionListener(e -> {
            
            
        });
        verifyButton.addActionListener(e -> {
            
            
        });
        undoButton.addActionListener(e -> {
           
            
        });
        

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void onSolve() throws IOException
    {
        

    }
    public void onVerify()throws IOException
    {
        
    }
    public void onUndo()throws IOException
    {
       
        
    }
   



    private void addCellListener(JTextField cell) {

        cell.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                refresh();
            }

            public void removeUpdate(DocumentEvent e) {
                refresh();
            }

            public void changedUpdate(DocumentEvent e) {
                refresh();
            }

            private void refresh() {
                try {
                    int[][] currentBoard = readFromBoard();
                    loader.saveToFile("levels/current.csv", currentBoard);
                    updateSolveButton();

                } catch (IOException ignored) {}
            }
        });
    }

   

    public void updateSolveButton() throws IOException {

        Solve solve = new Solve();
       
        int [][] content = loader.getContent("levels/current.csv") ;

        List<int[]> zeros = solve.getZeros(content) ;

       solveButton.setEnabled(zeros.size() == 5);
        
    }

   public void homeList() throws IOException {

    
    File file = new File("levels/current.csv");
    boolean hasSavedGame = file.exists() && file.length() > 0;

    if (hasSavedGame) {
        int choice = JOptionPane.showConfirmDialog(this,"There is a game in progress. Do you want to continue?","Game in Progress",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            sudokuBoard(new loadFromFile().getContent("levels/current.csv")); 
            return; 
        }
        
    }

   
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

   
    easyButton.addActionListener(e -> {
        try {
            onEasy();
            homePanel.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    mediumButton.addActionListener(e -> {
        try {
            onMedium();
            homePanel.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    hardButton.addActionListener(e -> {
        try {
            onHard();
            homePanel.setVisible(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

  
    add(homePanel, BorderLayout.CENTER);

    
    revalidate();
    repaint();
}


    public void onEasy() throws IOException
    {
    
        sudokuBoard(new loadFromFile().getContent("levels/easy.csv"));
        revalidate();
        repaint();
        
    }
    public void onMedium() throws IOException
    {
      
         sudokuBoard(new loadFromFile().getContent("levels/medium.csv"));

        revalidate();
        repaint();
        
    }
    public void onHard() throws IOException
    {
        
            sudokuBoard(new loadFromFile().getContent("levels/hard.csv"));
        revalidate();
        repaint();
        
    }

}

