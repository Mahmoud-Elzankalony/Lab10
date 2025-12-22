import java.io.*;
import java.util.ArrayList;

public class LogFile
{
    String filename = "FILENAME";
    ArrayList<String> content;

    // Default constructor
    public LogFile()
    {
        this.content = new ArrayList<>();
    }

    // Constructor with filename
    public LogFile(String filename)
    {
        this.filename = filename;
        this.content = new ArrayList<>();
    }

    public void loadFromFile() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename)) ;
        while (br.readLine() != null)
        {
            content.add(br.readLine());
        }
    }

    public void saveToFile() throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename)) ;
        for (String s : content)
        {
            bw.write(s);
        }
    }

    public ArrayList<String> getContent()
    {
        return content;
    }

    public void addLine (String line)
    {
        content.add(line);
    }

    public void removeLastLine ()
    {
        content.remove(content.size()-1);
    }

    public String returnLastLine ()
    {
        return content.get(content.size()-1);
    }

    // Append line directly to file (for immediate writing)
    public void appendLine(String line) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) // true = append mode
        {
            bw.write(line);
            bw.newLine();
        }
        // Also add to content for undo functionality
        if (content == null)
        {
            content = new ArrayList<>();
        }
        content.add(line);
    }

}
