package cn.styxs.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class util {
    public static final String CAL_NUMBERS = "0123456789";
    public static final String CAL_OPERATORS = "+-*/";

    public static class Operator {
        private static int getLevel(String op) {
            if (op.equals("+") || op.equals("-"))
                return 1;
            else if (op.equals("*") || op.equals("/"))
                return 2;
            else return 0; // ???
        }

        public static boolean Bigger(String op1, String op2) {
            return getLevel(op1) > getLevel(op2);
        }

        public static double calculate(double n1, double n2, String op) {
            if (op.equals("+"))
                return n1 + n2;
            else if (op.equals("-"))
                return n1 - n2;
            else if (op.equals("*"))
                return n1 * n2;
            else if (op.equals("/"))
                return n1 / n2;
            else return 0; // ???
        }
    }

    public static String[] parseNumbersByDigitInExpression(String expression) {
        ArrayList<String> exp = new ArrayList<>();
        String num = new String();
        boolean inNum = false;
        for (char ch : expression.toCharArray()) {
            if (ch >= '0' && ch <= '9' || ch == '.') {
                inNum = true;
                num += ch;
            } else {
                inNum = false;
                exp.add(num);
                num = "";
                exp.add(String.valueOf(ch));
            }
        }
        if (inNum)
            exp.add(num);
        String[] T = new String[1];
        return exp.toArray(T);
    }


    public static double evaluationInfixExpression(String[] strs) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Double> numberStack = new Stack<>();
        for (String str : strs) {
            if (CAL_OPERATORS.contains(str)) {
                if (operatorStack.empty()) {
                    operatorStack.add(str);
                } else {
                    String last = operatorStack.peek();
                    if (!Operator.Bigger(str, last)) {
                        while (!Operator.Bigger(str, last)) {
                            double n2 = numberStack.pop(),
                                    n1 = numberStack.pop();
                            numberStack.push(Operator.calculate(n1, n2, last));
                            operatorStack.pop();
                            if (operatorStack.empty())
                                break;
                            last = operatorStack.peek();
                        }
                    }
                    operatorStack.push(str);
                }
            } else numberStack.push(Double.parseDouble(str));
        }
        while (!operatorStack.empty()) {
            double n2 = numberStack.pop(),
                    n1 = numberStack.pop();
            numberStack.push(Operator.calculate(n1, n2, operatorStack.pop()));
        }
        return numberStack.pop();
    }

    public static void main(String[] args) {
        String[] commands = {
                "1+1",
                "1*2",
                "1+2*5",
                "1+4/2",
                "1+4/2*5",
                "1+4/2*5+3*2",
                "1.0+3.6"
        };
        double[] answers = {
                1 + 1,
                1 * 2,
                1 + 2 * 5,
                1 + 4 / 2,
                1 + 4 / 2 * 5,
                1 + 4 / 2 * 5 + 3 * 2,
                1.0 + 3.6
        };
        for (int i = 0; i < commands.length; i++) {
            System.out.println(i + "- " + String.valueOf(answers[i]) + ":  " + evaluationInfixExpression
                    (parseNumbersByDigitInExpression(commands[i])));
        }
    }

}
