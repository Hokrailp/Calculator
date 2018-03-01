package BestAppEver;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListner implements ActionListener   {

    private RPS calculator = null;
    private String  tLabel = null;
    private StringBuilder mS = null;
    //delete after "="

    public void actionPerformed(ActionEvent e) {
        calculator = new RPS();
        tLabel= MWindow.mStr.getText();
        mS = new StringBuilder(tLabel);

        String command = e.getActionCommand();
        if (!command.equals("=")) {
            if (command.equals("a++") || command.equals("a--")) {
                if (command.equals("a++")) {
                    MWindow.accuruty++;
                    return;
                } else {
                    if (MWindow.accuruty > 0)
                        MWindow.accuruty--;
                    return;
                }
            }

            if (command.equals("ans")) {
                if (!MWindow.mRes.getText().equals("NaN")) {
                    mS.append(String.valueOf(MWindow.mRes.getText()));
                    MWindow.mStr.setText(mS.toString());
                    return;
                }
            } else if (command.equals("C") || command.equals("<-")){
                if (command.equals("C")) {
                    MWindow.mStr.setText(" ");
                    return;
                } else {
                    if (mS.length()>1) {
                        mS.deleteCharAt(mS.length()-1);
                    }
                    MWindow.mStr.setText(mS.toString());
                    return;
                }
            } else {
                mS.append(command);
                MWindow.mStr.setText(mS.toString());
                return;
            }
        } else {
            int len = MWindow.accuruty;
            StringBuilder tResult = new StringBuilder(Double.toString(calculator.DoAllWork(tLabel)));
            int ePlace = tResult.indexOf("E");
            String E123 = "";
            if (ePlace>0) {
                E123 = tResult.substring(ePlace);
            }
            int pPlace;
            if (len>0) {
                pPlace = tResult.indexOf(".");
                tResult.setLength(pPlace+len+1);
                tResult.append(E123);
            }
            MWindow.mRes.setText(tResult.toString());
        }

    }

}
