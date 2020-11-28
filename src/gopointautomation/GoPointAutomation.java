package gopointautomation;

public class GoPointAutomation
{

    public static AddToken AddToken = new AddToken();
    public static AddPoint AddPoint = new AddPoint();

    public static void main(String[] args)
    {
        if (args[0].equals("token"))
        {
            AddToken.Run(args);
        }
        else if (args[0].equals("point"))
        {
            AddPoint.Run(args);
        }
    }
}
