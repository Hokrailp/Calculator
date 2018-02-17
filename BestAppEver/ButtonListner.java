package BestAppEver;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListner implements ActionListener   {

    private RPS calculator = null;
    private JLabel tLabel = null;
    private StringBuilder mS = null;
    //delete after "="
    private boolean flag = false;

    public void actionPerformed(ActionEvent e) {
        calculator = new RPS();
        tLabel = MWindow.mStr;
        mS = new StringBuilder("vbncv");

        mS.deleteCharAt(mS.length()-1);

        if (flag)
            MWindow.mStr.setText("");

        String tS;
        String command = e.getActionCommand();
        if (!command.equals("=")) {
            if (command.equals("ans")) {
                if (!MWindow.mRes.getText().equals("NaN")) {
                    tS = tLabel.getText() + String.valueOf(MWindow.mRes.getText());
                    MWindow.mStr.setText(tS);
                }
            } else if (command.equals("C") || command.equals("<-")){
                if (command.equals("C")) {
                    MWindow.mStr.setText(" ");
                } else {
                    char[] str = MWindow.mStr.getText().toCharArray();
                    tS = String.valueOf(str, 0, str.length-1);
                    MWindow.mStr.setText(tS);
                }
            } else {
                tS = tLabel.getText() + e.getActionCommand();
                MWindow.mStr.setText(tS);
            }
            flag = false;
        } else {
            //TODO check accuruty
            int len = MWindow.accuruty;
            String tResult = Double.toString(calculator.DoAllWork(tLabel.getText()));
            MWindow.mRes.setText(tResult);
            flag = true;
        }

    }

}
