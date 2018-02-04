import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//TODO этот класс рисует окошко с кнопками и тд. (пользовательский интерфейс, распихай все по контейнерам, которые сделаешь себе ниже)
//TODO сделать класс BackEnd, который будет обрабатывать все события

public class MWindow implements ActionListener {


    //поле ввода чисел
    JLabel jNumbers;
    //Кнопки от 0 до 9

    // теперь для математических действий(+; -; /; *; ^)

    // и равно(=)

    //ну и скобки

    //И точка

    //теперь метка(введите текст)
    JLabel MyText;

    //Теперь все для парсера, дабы можно было что-то считать
    String BeforePars = "";
    String znak;

    int MyParser() {
        int Result = -1;
        BeforePars += "!";
        //Используем BeforePars и считаем возвращаем int
        int N1 = 0;
        int N2 = 0;
        char ch;

        int i = 0;
        ch = BeforePars.charAt(i);
        //Считываем первое число
        while ((ch >= '0') && (ch <= '9')) {

            N1 = N1 * 10 + Integer.valueOf(Character.toString(BeforePars.charAt(i)));
            i++;
            ch = BeforePars.charAt(i);
        }
        // Запомним действие
        //znak = Character.toString(BeforePars.charAt(i));
        i++;
        //Второе число
        ch = BeforePars.charAt(i);
        while ((ch >= '0') && (ch <= '9')) {

            N2 = N2 * 10 + Integer.valueOf(Character.toString(BeforePars.charAt(i)));
            i++;
            ch = BeforePars.charAt(i);
        }
        //Выполняем действие
        switch (znak) {
            case "+":
                Result = N1 + N2;
                MyText.setText("Metka     ");
                break;
            case "-":
                Result = N1 - N2;
                MyText.setText("Metka     ");
                break;
            case "x":
                Result = N1 * N2;
                MyText.setText("Metka     ");
                break;
            case "/":
                try {
                    Result = N1 / N2;
                    MyText.setText("Metka     ");
                } catch (Exception exc) {
                    MyText.setText("Делишь на 0");
                    Result = Integer.MAX_VALUE;
                }
                break;
            default:
                MyText.setText("Сам считай");
                break;
        }
        return Result;
    }

    //Возвращает истину, если нет знака в строке
    boolean NoSign() {
        char s;
        for (int i = 0; i < BeforePars.length(); i++) {
            s = BeforePars.charAt(i);
            if (!((s >= '0') && (s <= '9'))) {
                return false;
            }
        }
        return true;
    }

    //Если есть знак - false, иначе true
    boolean NoPointBeforeSign() {
        int ind = -1;
        char s;
        // Сейчас запомню индекс знака
        for (int i = 0; i < BeforePars.length(); i++) {
            s = BeforePars.charAt(i);
            if (!((s >= '0') && (s <= '9')) || s != '.') {
                ind = i;
            }
        }
        for (int i = 0; i < ind; i++) {
            s = BeforePars.charAt(i);
            if (s == '.')
                return false;
        }
        return true;
    }

    //Если есть знак - false, иначе true
    boolean NoPointAfterSign() {
        int ind = -1;
        char s;
        // Сейчас запомню индекс знака
        for (int i = 0; i < BeforePars.length(); i++) {
            s = BeforePars.charAt(i);
            if (!((s >= '0') && (s <= '9')) || s != '.') {
                ind = i;
            }
        }
        for (int i = ind; i < BeforePars.length(); i++) {
            s = BeforePars.charAt(i);
            if (s == '.')
                return false;
        }
        return true;
    }

    //Меняем знак на Sign
    void ChangeSign(char Sign) {
        char[] MyBuffer = new char[BeforePars.length()];
        int index = -1;
        //Копирую все в массив, а потом обратно, но со сменой знака
        for (int i = 0; i < BeforePars.length(); i++) {
            MyBuffer[i] = BeforePars.charAt(i);
            if ((MyBuffer[i] == '+') || (MyBuffer[i] == '-') ||
                    (MyBuffer[i] == 'x') || (MyBuffer[i] == '/')) {
                index = i;
            }
        }//Теперь у меня есть скопированный массив и индекс знака
        BeforePars = "";
        for (int i = 0; i < MyBuffer.length; i++) {
            if (i != index) {
                BeforePars += MyBuffer[i];
            } else {
                BeforePars += Sign;
            }
        } // Мы все скопировали и поменяли знак на требуемый
    }

    // Теперь конструкотор
    MWindow() {
        //Это главный контейнер!!!
        JFrame myFrm = new JFrame("Kалькулятор");
        myFrm.setLayout(new FlowLayout());
        //myFrm.setBounds(150, 150, 230, 240 );
       // myFrm.setSize(230, 210);// w=230, h=240
        myFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Поле ввода на 15(наеюсь хватит)
        jNumbers = new JLabel();


        //Создаю кнопки
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonDivide = new JButton("/");
        JButton buttonMultiply = new JButton("x");
        buttonMultiply.setActionCommand("*");
        JButton buttonPow = new JButton("^");
        JButton buttonEquall = new JButton("=");
        JButton buttonOBrace = new JButton("(");
        JButton buttonCBrace = new JButton(")");
        JButton buttonPoint = new JButton(".");

        //Регистрирую приемники событий
        //jNumbers.addActionListener(this);
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonDivide.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonPow.addActionListener(this);
        buttonEquall.addActionListener(this);
        buttonOBrace.addActionListener(this);
        buttonCBrace.addActionListener(this);
        buttonPoint.addActionListener(this);

        //Теперь делаю метку и добавляю компоненты на экран
        //MyText = new JLabel("Metka     ");
        myFrm.add(jNumbers);
        //myFrm.add(MyText);
        myFrm.add(button1);
        myFrm.add(button2);
        myFrm.add(button3);
        myFrm.add(button4);
        myFrm.add(button5);
        myFrm.add(button6);
        myFrm.add(button7);
        myFrm.add(button8);
        myFrm.add(button9);
        myFrm.add(button0);

        myFrm.add(buttonPlus);
        myFrm.add(buttonMinus);
        myFrm.add(buttonMultiply);
        myFrm.add(buttonDivide);
        myFrm.add(buttonPow);

        myFrm.add(buttonOBrace);
        myFrm.add(buttonCBrace);

        myFrm.add(buttonPoint);
        myFrm.add(buttonEquall);

        //Делаю экран видемым
        myFrm.setVisible(true);
    }//Конец конструктора

    public void actionPerformed(ActionEvent ae) {
        String myComand = ae.getActionCommand();
        //Тут будут новые знаки!!(надеюсь) TODO норм считалка
        if (myComand.equals("=")) {
            try {
                RPS Worker = new RPS(BeforePars);
                String res = Double.toString(Worker.result);
                BeforePars = "";
                BeforePars += res;
                jNumbers.setText(BeforePars);
            } catch (Exception exc) {
                BeforePars = "";
                jNumbers.setText(BeforePars);
                MyText.setText("Sth wrong cause of you");
            }
        } else {
            BeforePars += myComand;
            jNumbers.setText(BeforePars);
        }

    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MWindow();
            }
        });
    }


}
