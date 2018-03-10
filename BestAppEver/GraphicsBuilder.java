package BestAppEver;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;

public class GraphicsBuilder extends JFrame{

    static StringBuilder Str = new StringBuilder("График Функции y = "); //"5*x+6^x"
    //window size
    static int w = 500;
    static int h = 500;
    final int fullSlen = 16;

    static void setStr(String S) {
        Str = new StringBuilder(S);
    }
    //constructor - creates window and do all you need (don't forget to setVisible(true))
    public GraphicsBuilder(String fullStr) {
        super(Str.append(fullStr).toString());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setStr(fullStr);
        JPanel drawingPane = new JPanel(new BorderLayout());
        setContentPane(drawingPane);
        drawingPane.add(new DrawingComponent(), BorderLayout.CENTER);
        setSize(w, h);
    }
}

class DrawingComponent extends JPanel implements GraphicsInterface{
    //array of X and Y values, and your calculator
    private static int[] Xarray = new int[GraphicsBuilder.w];
    private static int[] Yarray = new int[Xarray.length];
    private static double[] dYarray = new double[Yarray.length];
    private RPS Calculater = new RPS();

    //start and end of your curve
    private static int Xstart = -10;
    private static int Xend = 10;
    private static double Step = 0.1;
    //min of function on segment
    private static double avg = 0;


    //replace 'X' in S to your num (x0)
    public String replaceX(String S, double x0) {
        StringBuilder mString = new StringBuilder(S);
        int index = mString.indexOf("x");
        while (index >= 0) {
            mString.replace(index, index+1, "("+String.valueOf(x0)+")");
            index = mString.indexOf("x");
        }
        return mString.toString();
    }

    //trim Y val to widow using start and step
    public int trimToWindow(double Y, double min, double step, int h) {
        double res = (Y-min)/step;
        return h-(int)res-50-MWindow.heigh;
    }

    //for every X - evaluation Y to plot it (from your range)
    protected void paintComponent(Graphics g) {
        Graphics2D painter = (Graphics2D)g;
        String Xexpression = GraphicsBuilder.Str.toString();
        String fX;
        double curX = Xstart;
        //find minY and fill dYarray
        for (int i = 0; i < Xarray.length; i++) {
            //replace "X" with value of X
            fX = replaceX(Xexpression, curX);
            dYarray[i] = Calculater.DoAllWork(fX);
            //calculate imagine avg
            avg += dYarray[i];
            Xarray[i] = i;
            curX += Step;
        }
        avg /= dYarray.length;
        //trim all Y
        for (int i = 0; i < Xarray.length; i++) {
            Yarray[i] = trimToWindow(dYarray[i], avg, Step, GraphicsBuilder.h);
            painter.drawLine(Xarray[i], Yarray[i], Xarray[i], Yarray[i]+1);
        }
        painter.drawPolyline(Xarray, Yarray, Xarray.length);
        /**
        int i = 0;
        while (true) {
            i++;
            System.out.println();
            if (i==Integer.MAX_VALUE-10) {
                System.out.println("Hello World");
                break;
            }
        }
        */

    }
}
