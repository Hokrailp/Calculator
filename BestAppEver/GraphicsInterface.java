package BestAppEver;

public interface GraphicsInterface {

    //trim Y to window height
    int trimToWindow(double Y, double min, double step, int h);

    //replace 'X' in S to num (x0)
    String replaceX(String S, double x0);

}
