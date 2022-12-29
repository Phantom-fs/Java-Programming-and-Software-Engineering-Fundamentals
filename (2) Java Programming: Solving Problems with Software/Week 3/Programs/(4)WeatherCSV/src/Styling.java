import javax.swing.text.*;
import java.awt.*;

public class Styling
{
    static StyleContext sc = StyleContext.getDefaultStyleContext();

    public static Style headBoldStyle()
    {
        Style head = sc.addStyle("Head", null);
        StyleConstants.setBold(head, false);
        StyleConstants.setForeground(head, new Color(255, 255, 255));
        StyleConstants.setFontSize(head, 18);
        return head;
    }

    public static Style headSubStyle()
    {
        Style headSub = sc.addStyle("Head SUB", null);
        StyleConstants.setBold(headSub, false);
        StyleConstants.setForeground(headSub, new Color(201, 201, 201));
        StyleConstants.setFontSize(headSub, 14);
        return headSub;
    }

    public static Style Style1()
    {
        Style style1 = sc.addStyle("Style1", null);
        StyleConstants.setBold(style1, false);
        StyleConstants.setForeground(style1, new Color(255, 129, 54));
        StyleConstants.setFontSize(style1, 12);
        return style1;
    }

    public static Style Style2()
    {
        Style style2 = sc.addStyle("Style1", null);
        StyleConstants.setBold(style2, false);
        StyleConstants.setForeground(style2, new Color(83, 246, 3));
        StyleConstants.setFontSize(style2, 12);
        return style2;
    }

    public static Style Style3()
    {
        Style style3 = sc.addStyle("Style1", null);
        StyleConstants.setBold(style3, false);
        StyleConstants.setForeground(style3, new Color(23, 211, 211));
        StyleConstants.setFontSize(style3, 12);
        return style3;
    }

    public static Style Style4()
    {
        Style style4 = sc.addStyle("Style1", null);
        StyleConstants.setBold(style4, false);
        StyleConstants.setForeground(style4, new Color(210, 108, 255));
        StyleConstants.setFontSize(style4, 12);
        return style4;
    }
}
