import java.io.*;
import java.util.ArrayList;

public class LogFile {
    String filename = "D:\\programming\\java\\lab10\\Lab10-main_downloaded_from_github_v1\\levels\\log.txt";
    // FIX: Initialize the list here
    ArrayList<String> content = new ArrayList<>();

    public void loadFromFile() throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;

        content.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line); // Fixed logic: previously skipped lines
            }
        }
    }

    public void saveToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String s : content) {
                if ( s.equals(content.get(content.size()-1)) )
                {
                    bw.write(s);
                }
                else
                {
                    bw.write(s);
                    bw.newLine();
                }
            }
        }
    }

    public void addLine(String line) {
        content.add(line);
    }

    public void removeLastLine() {
        if (!content.isEmpty()) content.remove(content.size() - 1);
    }

    public String returnLastLine() {
        if (content.isEmpty()) return null;
        return content.getLast();
    }
    public String returnBeforeLastLine()
    {
        if (content.isEmpty() || content.size() < 2) return null;
        return content.get(content.size() - 2);
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public String convert(int x , int y, String newValue , int prev )
    {
        return "(" + x + "," + y + "," + newValue + "," + prev+")";
    }
}