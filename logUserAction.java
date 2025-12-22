import java.io.IOException;

public class logUserAction
{
    private int x;
    private int y;
    private int val;
    private int prev;

    public logUserAction(int x, int y, int val, int prev) throws IOException
    {
        this.x = x;
        this.y = y;
        this.val = val;
        this.prev = prev;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getVal()
    {
        return val;
    }

    public int getPrev()
    {
        return prev;
    }
}

