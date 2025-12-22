public class UserAction
{
    private int x;
    private int y;
    private int val;
    private int prev;

    public UserAction(int x, int y, int val, int prev)
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

