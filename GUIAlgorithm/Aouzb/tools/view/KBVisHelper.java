package GUIAlgorithm.Aouzb.tools.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

import java.lang.InterruptedException;

public class KBVisHelper {

    private KBVisHelper(){}

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);

    public static final Color cRed = new Color(0x7bF44336, true);
    public static final Color cPink = new Color(0x7bE91E63, true);
    public static final Color cPurple = new Color(0x7b9C27B0, true);
    public static final Color cDeepPurple = new Color(0x7b673AB7, true);
    public static final Color cIndigo = new Color(0x7b3F51B5, true);
    public static final Color cBlue = new Color(0x7b2196F3, true);
    public static final Color cLightBlue = new Color(0x7b03A9F4, true);
    public static final Color cCyan = new Color(0x7b00BCD4, true);
    public static final Color cTeal = new Color(0x7b009688, true);
    public static final Color cGreen = new Color(0x7b4CAF50, true);
    public static final Color cLightGreen = new Color(0x7b8BC34A, true);
    public static final Color cLime = new Color(0x7bCDDC39, true);
    public static final Color cYellow = new Color(0x7bFFEB3B, true);
    public static final Color cAmber = new Color(0x7bFFC107, true);
    public static final Color cOrange = new Color(0x7bFF9800, true);
    public static final Color cDeepOrange = new Color(0x7bFF5722, true);
    public static final Color cBrown = new Color(0x7b795548, true);
    public static final Color cGrey = new Color(0x7b9E9E9E, true);
    public static final Color cBlueGrey = new Color(0x7b607D8B, true);
    public static final Color cBlack = new Color(0x7b000000, true);
    public static final Color cWhite = new Color(0x7bFFFFFF, true);

    /**
     * 颜色数组
     */
    public static final Color[] colors = {White, Brown, Purple, DeepPurple,
            Indigo, Blue, LightBlue, Cyan, Teal, Green, LightGreen, Lime,
            Yellow, Amber, Orange, BlueGrey, Red, Black
    };

    public static final Color[] cColors = {cWhite, cBrown, cPurple, cDeepPurple,
            cIndigo, cBlue, cLightBlue, cCyan, cTeal, cGreen, cLightGreen, cLime,
            cYellow, cAmber, cOrange, cBlueGrey,  cRed, cBlack
    };

    public static void strokeCircle(Graphics2D g, int x, int y, int r){

        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g.draw(circle);
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r){

        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g.fill(circle);
    }

    public static void strokeRectangle(Graphics2D g, int x, int y, int w, int h){

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.draw(rectangle);
    }

    public static void fillRectangle(Graphics2D g, int x, int y, int w, int h){

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.fill(rectangle);
    }

    public static void setColor(Graphics2D g, Color color){
        g.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D g, int w){
        int strokeWidth = w;
        g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }

    public static void putImage(Graphics2D g, int x, int y, String imageURL){

        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();

        g.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g, String text, int centerx, int centery){

        if(text == null) {
            throw new IllegalArgumentException("Text is null in drawText function!");
        }

        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g.drawString(text, centerx - w/2, centery + h);
    }

}
