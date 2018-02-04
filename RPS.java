import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;

class RPS {
    double result;
    // Wanna read one double numeric////////////////////////// NOT THIS, ITS USEFULL ////////////////

    //ant transform to Numeric
    private Numeric ReadMyNum(int ind, String sz) {
        char cH;
        int i = ind;
        boolean flag = true;
        double dRes = 0d;
        double exponent = 0.1;
        cH = sz.charAt(ind);
        while ((cH >= '0') && (cH <= '9') || (cH == '.')) {
            if ((cH != '.') && (flag)) {

                dRes = dRes * 10 + Character.digit(cH, 10);
            } else { //read fraction
                if (cH == '.') {
                    flag = false;
                } else {
                    dRes += Character.digit(cH, 10) * exponent;
                    exponent = exponent / 10;
                    flag = false;
                }
            }
            if (ind < sz.length() - 1) {
                ind++;
                cH = sz.charAt(ind);
            } else {
                ind++;
                break;
            }

        }

        Numeric thisRes = new Numeric(dRes);
        thisRes.length = ind-i;
        return thisRes;

    }

    //transform to Action
    private Action ReadMyAct(int ind, String sz) {
        char cH;
        int i = ind;
        String cRes = "";
        cH = sz.charAt(ind);
        int probLen = 1;

        if (    cH == 's' ||
                cH == 'c' ||
                cH == 't')
            probLen = 3;
        else if (cH == 'a')
            probLen = 4;

        while (ind-i < probLen) {
            cRes += cH;

            if (ind < sz.length() - 1) {
                ind++;
                cH = sz.charAt(ind);
            } else {
                ind++;
                break;
            }

        }

        Action thisRes = new Action(cRes);
        thisRes.length = ind-i;
        return thisRes;

    }


    // Common class for my tree
    abstract class Stuff {
        double me;
        int priority;
        String action;

    }

    class Numeric extends Stuff {

        Numeric(double val) {
            me = val;
        }

        int length;
    }

    // Only '+''-' '/' '*' '^' and sin, cos, tan, atan, asin, acos
    class Action extends Stuff {

        boolean isTrigonometryF(String actq) {
        //only sin, cos, tan, atan, asin, acos
            if (    actq.equals("sin") ||
                    actq.equals("cos") ||
                    actq.equals("tan") ||
                    actq.equals("atan")||
                    actq.equals("asin")||
                    actq.equals("acos"))
                return true;
            return false;
        }

        Action(String act) {
            action = act;
            if (action.equals("^"))
                priority = 5;
            else if (isTrigonometryF(action))
                priority = 4;
            else if ((action.equals("*")) || (action.equals("/")))
                priority = 3;
            else if ((action.equals("+")) || (action.equals("-")))
                priority = 2;
            else
                priority = 1; //только у скобок
            // 0 только у чисел
        }

        int length;
    }


    private String S;

    private void setS(String ss) {
        S = ss;
    }

    private ArrayList<Stuff> stack = new ArrayList<>();

    // change String to Stuff
    private ArrayList<Stuff> InputS = new ArrayList<>();
    private ArrayList<Stuff> OutputS = new ArrayList<>();

    //don`t touch it
    private void TransformSrtToStuff() {
        int i = 0;
        char ch;
        while (i < S.length()) {
            ch = S.charAt(i);
            if ((ch >= '0') && (ch <= '9')) {
                Numeric TempNum = ReadMyNum(i, S);
                InputS.add(TempNum);
                i += TempNum.length;
            } else {
                Action TempAct = ReadMyAct(i, S);
                InputS.add(TempAct);
                i += TempAct.length;;
            }
        }
    }

    private void TransformListToRPN() {
        int i = 0;
        //InputS.get(i) - current Symbol
        //stack.get(stack.size() - 1) - act2 on top of stack
        //while is Symbol to read - do ...
        while (i < InputS.size()) {
            //If num - add to output
            if (InputS.get(i).priority == 0) {
                OutputS.add(InputS.get(i));
            } else if (InputS.get(i).priority == 1) {
                // If '('...
                if (InputS.get(i).action.equals("("))
                    stack.add(InputS.get(i));
                    //Or ')' ...
                else if (InputS.get(i).action.equals(")")) {
                    //до открывающей скобки выпихиваем в output
                    while (!stack.get(stack.size() - 1).action.equals("(")) {
                        OutputS.add(stack.get(stack.size() - 1));
                        stack.remove(stack.size() - 1);
                    }
                    //Delete '(' from stack
                    stack.remove(stack.size() - 1);
                }
            } else {
                // If act - ...
                try {
                    boolean RedFlag = (stack.get(stack.size() - 1) == null);
                    if (RedFlag) {
                        stack.add(InputS.get(i));
                    } else { // while priority act1 <= act2 on top of stack - act2 to output
                        while ((InputS.get(i).priority <= stack.get(stack.size() - 1).priority)
                                && (stack.get(stack.size() - 1).priority > 1)) {
                            OutputS.add(stack.get(stack.size() - 1));
                            stack.remove(stack.size() - 1);
                        }
                        //stack.add(InputS.get(i));
                    }
                    stack.add(InputS.get(i));
                } catch (Exception exc) {
                    stack.add(InputS.get(i));
                }
            }
            i++;
        }
        //push actions from stack to output
        i = stack.size();
        while (i > 0) {
            i--;
            OutputS.add(stack.get(i));
        }
    }

    // Gonna calculate reverse polish notation, +-*/^
    private double CalculateWithRPN() {
        ArrayList<Stuff> TempStack = new ArrayList<>();
        //OutputS is input here)
        int i = 0;
        while (i < OutputS.size()) {
            if (OutputS.get(i).priority == 0) {
                TempStack.add(OutputS.get(i));
            } else {
                Numeric TempNum = new Numeric(0);
                switch (OutputS.get(i).action) {
                    case "+":
                        TempNum.me = TempStack.get(TempStack.size() - 2).me + TempStack.get(TempStack.size() - 1).me;
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "-":
                        TempNum.me = TempStack.get(TempStack.size() - 2).me - TempStack.get(TempStack.size() - 1).me;
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "*":
                        TempNum.me = TempStack.get(TempStack.size() - 2).me * TempStack.get(TempStack.size() - 1).me;
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "/":
                        TempNum.me = TempStack.get(TempStack.size() - 2).me / TempStack.get(TempStack.size() - 1).me;
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "^":
                        TempNum.me = Math.pow(TempStack.get(TempStack.size() - 2).me, TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "sin":
                        TempNum.me = Math.sin(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "cos":
                        TempNum.me = Math.cos(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "tan":
                        TempNum.me = Math.tan(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "atan":
                        TempNum.me = Math.atan(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "asin":
                        TempNum.me = Math.asin(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                        break;
                    case "acos":
                        TempNum.me = Math.acos(TempStack.get(TempStack.size() - 1).me);
                        TempStack.remove(TempStack.size() - 1);
                        TempStack.add(TempNum);
                    default:
                        break;
                }
            }
            i++;
        }
        if (TempStack.size() == 1) {
            return TempStack.get(0).me;
        } else {
            System.out.println("Error");
            return -1d;
        }
    }

    //returns result of S
    double DoAllWork(String S) {
        setS(S);
        TransformSrtToStuff();
        TransformListToRPN();
        return CalculateWithRPN();
    }

    RPS(String St) {
        result = DoAllWork(St);
    }
    RPS() {
        result =0;
    }

}
