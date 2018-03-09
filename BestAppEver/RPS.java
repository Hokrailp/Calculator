package BestAppEver;

import com.sun.javafx.binding.DoubleConstant;
import sun.misc.DoubleConsts;

import java.util.*;

class RPS {

    protected static final double e = Math.E;
    protected static final double pi = Math.PI;
    private double result;
    private String S;
    private Stack<Stuff> stack = new Stack<>();//done
    // change String to Stuff
    private List<Stuff> InputS;//?
    private List<Stuff> OutputS = new LinkedList<>();//?

    //ant transform to Numeric
    private Numeric ReadMyNum(int ind, String sz) {
        char cH;
        int i = ind;

        if (sz.charAt(ind) == 'e') {
            Numeric thisRes = new Numeric(e);
            thisRes.length = 1;
            return thisRes;
        }
        if (sz.charAt(ind) == 'p') {
            Numeric thisRes = new Numeric(pi);
            thisRes.length = 2;
            return thisRes;
        }

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
        if (cH == 'E') {
            ind++;
            cH = sz.charAt(ind);
            double e10 = 0;
            while ((cH >= '0') && (cH <= '9') && (e10 < 10)) {
                e10 *= 10;
                e10 += Character.digit(cH, 10);

                if (ind < sz.length() - 1) {
                    ind++;
                    cH = sz.charAt(ind);
                } else {
                    ind++;
                    break;
                }
            }
            dRes *= Math.pow(10, e10);
        }
        Numeric thisRes = new Numeric(dRes);
        thisRes.length = ind - i;
        return thisRes;

    }

    //transform to Action
    private Action ReadMyAct(int ind, String sz) {
        char cH;
        int i = ind;
        String cRes = "";
        cH = sz.charAt(ind);
        int probLen = 1;

        if (cH == 's' ||
                cH == 'c' ||
                cH == 't')
            probLen = 3;
        else if (cH == 'a')
            probLen = 4;
            //ln
        else if (cH == 'l')
            probLen = 2;

        while (ind - i < probLen) {
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
        thisRes.length = ind - i;
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

    // Only '+''-' '/' '*' '^' and sin, cos, tan, atan, asin, acos, ln
    class Action extends Stuff {

        boolean isTrigonometryF(String actq) {
            //only sin, cos, tan, atan, asin, acos
            if (actq.equals("sin") ||
                    actq.equals("cos") ||
                    actq.equals("tan") ||
                    actq.equals("atan") ||
                    actq.equals("asin") ||
                    actq.equals("acos"))
                return true;
            return false;
        }

        Action(String act) {
            action = act;
            if (action.equals("^"))
                priority = 5;
            else if (isTrigonometryF(action) || action.equals("ln"))
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

    private void setS(String ss) {
        S = ss;
    }

    //don`t touch it
    private void TransformSrtToStuff() {
        if (S != null)
            InputS = new ArrayList<>(S.length() / 5);
        else InputS = new ArrayList<>();
        int i = 0;
        char ch;
        while (i < S.length()) {
            ch = S.charAt(i);
            if ((ch >= '0') && (ch <= '9') || ((ch == 'p') || (ch == 'e'))) {
                Numeric TempNum = ReadMyNum(i, S);
                InputS.add(TempNum);
                i += TempNum.length;
            } else {
                Action TempAct = ReadMyAct(i, S);
                InputS.add(TempAct);
                i += TempAct.length;
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
                    stack.push(InputS.get(i));
                    //Or ')' ...
                else if (InputS.get(i).action.equals(")")) {
                    //до открывающей скобки выпихиваем в output
                    while (!stack.peek().action.equals("(")) {
                        OutputS.add(stack.pop());
                    }
                    //Delete '(' from stack
                    stack.pop();
                }
            } else {
                // If act - ...
                try {
                    boolean RedFlag = (stack.peek() == null);
                    if (RedFlag) {
                        stack.push(InputS.get(i));
                    } else { // while priority act1 <= act2 on top of stack - act2 to output
                        while ((InputS.get(i).priority <= stack.peek().priority)
                                && (stack.peek().priority > 1)) {
                            OutputS.add(stack.pop());
                        }
                    }
                    stack.push(InputS.get(i));
                } catch (Exception exc) {
                    stack.push(InputS.get(i));
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

    // Gonna calculate reverse polish notation
    private double CalculateWithRPN() {
        Stack<Stuff> TempStack = new Stack<>();
        //OutputS is input here)
        int i = 0;
        Stuff Num1,Num2;
        while (i < OutputS.size()) {
            if (OutputS.get(i).priority == 0) {
                TempStack.push(OutputS.get(i));
            } else {
                Numeric TempNum = new Numeric(0);
                double rad;
                switch (OutputS.get(i).action) {
                    // be accurate with /, asin, acos, tg, ln!!!
                    case "+":
                        Num1 = TempStack.pop();
                        Num2 = TempStack.pop();
                        TempNum.me = Num2.me + Num1.me;
                        TempStack.push(TempNum);
                        break;
                    case "-":
                        Num1 = TempStack.pop();
                        Num2 = TempStack.pop();
                        TempNum.me = Num2.me - Num1.me;
                        TempStack.push(TempNum);
                        break;
                    case "*":
                        Num1 = TempStack.pop();
                        Num2 = TempStack.pop();
                        TempNum.me = Num2.me * Num1.me;
                        TempStack.push(TempNum);
                        break;
                    case "/":
                        try {
                            Num1 = TempStack.pop();
                            Num2 = TempStack.pop();
                            TempNum.me = Num2.me / Num1.me;
                            TempStack.push(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    case "^":
                        Num1 = TempStack.pop();
                        Num2 = TempStack.pop();
                        TempNum.me = Math.pow(Num2.me, Num1.me);
                        TempStack.push(TempNum);
                        break;
                    case "%":
                        Num1 = TempStack.pop();
                        Num2 = TempStack.pop();
                        TempNum.me = Num2.me % Num1.me;
                        TempStack.push(TempNum);
                        break;
                    case "sin":
                        //TODO: your choise
                        rad = TempStack.pop().me;
                        rad /= 180;
                        rad *= pi;
                        TempNum.me = Math.sin(rad);
                        TempStack.add(TempNum);
                        break;
                    case "cos":
                        rad = TempStack.pop().me;
                        rad /= 180;
                        rad *= pi;
                        TempNum.me = Math.cos(rad);
                        TempStack.add(TempNum);
                        break;
                    case "tan":
                        try {
                            rad = TempStack.pop().me;
                            rad /= 180;
                            rad *= pi;
                            TempNum.me = Math.tan(rad);
                            TempStack.add(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    case "atan":
                        try {
                            rad = TempStack.pop().me;
                            TempNum.me = Math.atan(rad);
                            TempNum.me *= 180;
                            TempNum.me /= pi;
                            TempStack.add(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    case "asin":
                        try {
                            rad = TempStack.pop().me;
                            TempNum.me = Math.asin(rad);
                            TempNum.me *= 180;
                            TempNum.me /= pi;
                            TempStack.add(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    case "acos":
                        try {
                            rad = TempStack.pop().me;
                            TempNum.me = Math.acos(rad);
                            TempNum.me *= 180;
                            TempNum.me /= pi;
                            TempStack.add(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    case "ln":
                        try {
                            TempNum.me = Math.log(TempStack.pop().me);
                            TempStack.add(TempNum);
                        } catch (Exception exc) {
                            return Double.NaN;
                        }
                        break;
                    default:
                        break;
                }
            }
            i++;
        }
        if (TempStack.size() == 1) {
            return TempStack.get(0).me;
        } else {
            //System.out.println("Error");
            return Double.NaN;
        }
    }

    //returns result of S
    double DoAllWork(String S) {
        setS(S);
        TransformSrtToStuff();
        TransformListToRPN();
        double res = CalculateWithRPN();
        stack.clear();
        InputS.clear();
        OutputS.clear();
        return res;
    }

    RPS(String St) {
        result = DoAllWork(St);
    }

    RPS() {
        result = 0;
    }

}
