//Program created to color the output and provide with more output readability
//Coder : Phantom-fs

public class Styling
{
    static String newline = System.getProperty("line.separator");

    public static void line()
    {
        System.out.print(newline+"--------------------------------------------------");
    }

    public static void lineTitle ()
    {
        System.out.print(newline+"=================================================="+newline);
    }

    public static void line (String text)
    {
        System.out.print(newline+"--------------------------------------------------"+newline);
        System.out.print(text);
        System.out.print(newline+"--------------------------------------------------"+newline);
    }

    public static void lineTitle (String text)
    {
        System.out.print(newline+"=================================================="+newline);
        System.out.print(text);
        System.out.print(newline+"=================================================="+newline);
    }

    public static void color (String text, String color)
    {
        switch (color)
        {
            case "red" -> System.out.print(newline+"\u001B[31m" + text + "\u001B[0m");
            case "green" -> System.out.print(newline+"\u001B[32m" + text + "\u001B[0m");
            case "yellow" -> System.out.print(newline+"\u001B[33m" + text + "\u001B[0m");
            case "blue" -> System.out.print(newline+"\u001B[34m" + text + "\u001B[0m");
            case "purple" -> System.out.print(newline+"\u001B[35m" + text + "\u001B[0m");
            case "cyan" -> System.out.print(newline+"\u001B[36m" + text + "\u001B[0m");
            case "white" -> System.out.print(newline+"\u001B[37m" + text + "\u001B[0m");
            default -> System.out.print(newline+"\u001B[30m" + text + "\u001B[0m");
        }
    }
}
