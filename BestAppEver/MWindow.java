package BestAppEver;

import javafx.application.Application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//check marks "//new components" when add new things))
public class MWindow implements ComponentListener {

    private static GridBagConstraints SetPos(int grX, int grY, int sizeH, int sizeW) {
        GridBagConstraints res = new GridBagConstraints();
        res.fill = GridBagConstraints.BOTH;
        res.gridx = grX;
        res.gridy = grY;
        res.gridwidth = sizeW;
        res.gridheight = sizeH;
        res.weightx = 1.0;
        res.weighty = 1.0;
        return res;
    }

    private static GridBagConstraints SetPos(int grX, int grY) {
        GridBagConstraints res = new GridBagConstraints();
        res.fill = GridBagConstraints.BOTH;
        res.gridx = grX;
        res.gridy = grY;
        res.gridwidth = 1;
        res.gridheight = 1;
        res.weightx = 1.0;
        res.weighty = 1.0;
        return res;
    }

    static int accuruty = 2;

    //im declorating my things
    //new components
    final static String CALCANEL = "Панель с калькулятором";
    final static String GRAFPANEL   = "Панель с графиком";

    static JComboBox<String> combobox;

    static JLabel mStr;
    static JLabel mRes;
    private static JButton button0;
    private static JButton button1; private static JButton button2; private static JButton button3;
    private static JButton button6; private static JButton button5; private static JButton button4;
    private static JButton button7; private static JButton button8; private static JButton button9;
    private static JButton buttonPi; private static JButton buttonE;
    private static JButton buttonPlus;
    private static JButton buttonMinus;
    private static JButton buttonDivide;
    private static JButton buttonMultiply;
    private static JButton buttonPow;
    private static JButton buttonEquall;
    private static JButton buttonOBrace;
    private static JButton buttonCBrace;
    private static JButton buttonPoint;
    private static JButton buttonDel;
    private static JButton buttonClear;
    private static JButton buttonSin;
    private static JButton buttonCos;
    private static JButton buttonTan;
    private static JButton buttonAsin;
    private static JButton buttonAcos;
    private static JButton buttonAtan;
    private static JButton buttonAns;
    private static JButton buttonLn;
    private static JButton buttonMod;
    private static JButton buttonaPP;
    private static JButton buttonaMM;

    public static void addComponentsToPane(Container pane) {
        //new components
        pane.setLayout(new GridBagLayout());
        ButtonListner listner = new ButtonListner();
        GridBagConstraints c = SetPos(0, 0, 1, 5);
        combobox= new JComboBox<>(new String[] {CALCANEL, GRAFPANEL});
        pane.add(combobox,c);


        int gridY = 1;
        c = SetPos(0, gridY++, 1, 5);
        c.fill = GridBagConstraints.NONE;
        mStr = new JLabel(" ");
        pane.add(mStr, c);

        c = SetPos(0, gridY++, 1, 5);
        c.fill = GridBagConstraints.NONE;
        mRes = new JLabel(" ");
        //System.out.println(mRes.getFont()); по дефолту 12
        pane.add(mRes, c);


        //y = 2
        c = SetPos(0, gridY, 1 , 2);
        buttonEquall = new JButton("=");
        buttonEquall.setBackground(Color.RED);
        pane.add(buttonEquall, c);

        c = SetPos(2, gridY);
        buttonAns = new JButton("ans");
        pane.add(buttonAns, c);

        c = SetPos(3, gridY);
        buttonaPP = new JButton("a++");
        pane.add(buttonaPP, c);

        c = SetPos(4, gridY);
        buttonaMM = new JButton("a--");
        pane.add(buttonaMM, c);

        gridY++;
        //Y = 3
        c = SetPos(0, gridY);
        button1 = new JButton("1");
        pane.add(button1, c);

        c = SetPos(1, gridY);
        button2 = new JButton("2");
        pane.add(button2, c);

        c = SetPos(2, gridY);
        button3 = new JButton("3");
        pane.add(button3, c);

        c = SetPos(3, gridY);
        buttonPlus = new JButton("+");
        pane.add(buttonPlus, c);

        c = SetPos(4, gridY);
        buttonMinus = new JButton("-");
        pane.add(buttonMinus, c);

        gridY++;
        //y = 4
        c = SetPos(0, gridY);
        button4 = new JButton("4");
        pane.add(button4, c);

        c = SetPos(1, gridY);
        button5 = new JButton("5");
        pane.add(button5, c);

        c = SetPos(2, gridY);
        button6 = new JButton("6");
        pane.add(button6, c);

        c = SetPos(4, gridY);
        buttonDivide = new JButton("/");
        pane.add(buttonDivide, c);

        c = SetPos(3, gridY);
        buttonMultiply = new JButton("*");
        pane.add(buttonMultiply, c);

        gridY++;
        //y = 5

        c = SetPos(0, gridY);
        button7 = new JButton("7");
        pane.add(button7, c);

        c = SetPos(1, gridY);
        button8 = new JButton("8");
        pane.add(button8, c);

        c = SetPos(2, gridY);
        button9 = new JButton("9");
        pane.add(button9, c);


        c = SetPos(3, gridY);
        buttonPow = new JButton("^");
        pane.add(buttonPow, c);

        c = SetPos(4, gridY);
        buttonDel = new JButton("<-");
        pane.add(buttonDel, c);

        gridY++;
        //y =6
        c = SetPos(0, gridY);
        buttonPoint = new JButton(".");
        pane.add(buttonPoint, c);

        c = SetPos(1, gridY);
        button0 = new JButton("0");
        pane.add(button0, c);

        c = SetPos(2, gridY);
        buttonOBrace = new JButton("(");
        pane.add(buttonOBrace, c);

        c = SetPos(3, gridY);
        buttonCBrace = new JButton(")");
        pane.add(buttonCBrace, c);

        c = SetPos(4, gridY);
        buttonClear = new JButton("C");
        pane.add(buttonClear, c);

        gridY++;
        //y=7
        c = SetPos(0, gridY);
        buttonSin = new JButton("sin");
        buttonSin.setActionCommand("sin(");
        pane.add(buttonSin, c);

        c = SetPos(1, gridY);
        buttonCos = new JButton("cos");
        buttonCos.setActionCommand("cos(");
        pane.add(buttonCos, c);

        c = SetPos(2, gridY);
        buttonTan = new JButton("tan");
        buttonTan.setActionCommand("tan(");
        pane.add(buttonTan, c);

        c = SetPos(3, gridY);
        buttonLn = new JButton("ln");
        buttonLn.setActionCommand("ln(");
        pane.add(buttonLn, c);

        c = SetPos(4, gridY);
        buttonMod = new JButton("%");
        pane.add(buttonMod, c);

        gridY++;
        //y=8
        c = SetPos(0, gridY);
        buttonAsin = new JButton("asin");
        buttonAsin.setActionCommand("asin(");
        pane.add(buttonAsin, c);

        c = SetPos(1, gridY);
        buttonAcos = new JButton("acos");
        buttonAcos.setActionCommand("acos(");
        pane.add(buttonAcos, c);

        c = SetPos(2, gridY);
        buttonAtan = new JButton("atan");
        buttonAtan.setActionCommand("atan(");
        pane.add(buttonAtan, c);

        c = SetPos(3, gridY);
        buttonPi = new JButton("pi");
        pane.add(buttonPi, c);

        c = SetPos(4, gridY);
        buttonE = new JButton("e");
        pane.add(buttonE, c);

        addToAll(listner);
        //button = new JButton("wtf");
        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 0;       // установить первоночальный размер кнопки
        //c.weighty = 1.0;   // установить отступ
        // c.anchor = GridBagConstraints.PAGE_END; // установить кнопку в конец окна
        //c.insets = new Insets(10, 0, 0, 0);  // поставить заглушку
        //c.gridx = 1;       // выравнять компонент по Button 2
        //c.gridwidth = 2;   // установить в 2 колонку
        //c.gridy = 2;       // и 3 столбец
        //pane.add(button, c);

    }

    private static void addToAll(ActionListener actionListener) {
        //new components
        button0.addActionListener(actionListener);
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        button3.addActionListener(actionListener);
        button6.addActionListener(actionListener);
        button5.addActionListener(actionListener);
        button4.addActionListener(actionListener);
        button7.addActionListener(actionListener);
        button8.addActionListener(actionListener);
        button9.addActionListener(actionListener);
        buttonPi.addActionListener(actionListener);
        buttonE.addActionListener(actionListener);
        buttonPlus.addActionListener(actionListener);
        buttonMinus.addActionListener(actionListener);
        buttonDivide.addActionListener(actionListener);
        buttonMultiply.addActionListener(actionListener);
        buttonPow.addActionListener(actionListener);
        buttonEquall.addActionListener(actionListener);
        buttonOBrace.addActionListener(actionListener);
        buttonCBrace.addActionListener(actionListener);
        buttonPoint.addActionListener(actionListener);
        buttonDel.addActionListener(actionListener);
        buttonClear.addActionListener(actionListener);
        buttonSin.addActionListener(actionListener);
        buttonCos.addActionListener(actionListener);
        buttonTan.addActionListener(actionListener);
        buttonAsin.addActionListener(actionListener);
        buttonAcos.addActionListener(actionListener);
        buttonAtan.addActionListener(actionListener);
        buttonAns.addActionListener(actionListener);
        buttonLn.addActionListener(actionListener);
        buttonMod.addActionListener(actionListener);
        buttonaPP.addActionListener(actionListener);
        buttonaMM.addActionListener(actionListener);
    }

    private void createAndShowGUI() {
        // Создание окна
        JFrame frame = new JFrame("Calculator??");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addComponentListener(this);

        // Установить панель содержания
        addComponentsToPane(frame.getContentPane());

        // Показать окно
        frame.pack();
        frame.setVisible(true);
        System.out.println(frame.getSize());
        Image image = new ImageIcon(MWindow.class.getResource("mIcon.png")).getImage();
        frame.setIconImage(image);
        frame.setSize(500,500);
    }

    public void componentResized(ComponentEvent e) {
        final int w = 200;
        final int h = 200;
        //final int S = w*h;
        int wid = e.getComponent().getSize().width;
        int heg = e.getComponent().getSize().height;
        int startS = 12;
        double ka = Math.sqrt(wid*heg/(w*h));
        double newSize = startS*ka;
        FontAll((int)newSize);
    }

    private void FontAll(int nSize) {
        //new components
        mStr.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        mRes.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonAns.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button0.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button1.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button2.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button3.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button6.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button5.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button4.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button7.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button8.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        button9.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonPi.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonE.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonPlus.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonMinus.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonDivide.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonMultiply.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonPow.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonEquall.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonOBrace.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonCBrace.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonPoint.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonDel.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonClear.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonSin.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonCos.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonTan.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonAsin.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonAcos.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonAtan.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonLn.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonMod.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonaPP.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));
        buttonaMM.setFont(new Font(Font.DIALOG, Font.BOLD, nSize));

    }
    /**
     * Invoked when the component's position changes.
     */
    public void componentMoved(ComponentEvent e){}

    /**
     * Invoked when the component has been made visible.
     */
    public void componentShown(ComponentEvent e){}

    /**
     * Invoked when the component has been made invisible.
     */
    public void componentHidden(ComponentEvent e){}


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            MWindow Window = new MWindow();
            Window.createAndShowGUI();
        });
        javax.swing.SwingUtilities.invokeLater(()-> {
            GraphicsBuilder graphic = new GraphicsBuilder("sin(x)*x");
            graphic.setVisible(true);
        });

    }
}