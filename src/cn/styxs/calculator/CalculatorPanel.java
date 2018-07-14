package cn.styxs.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorPanel extends JPanel {
    private JButton dispaly; // 使用disable按钮显示信息
    private JPanel panel; //用于承载功能按钮的面板
    private double ans;
    private String expression;
    private ActionListener listener;


    public CalculatorPanel() {
        setLayout(new BorderLayout()); //边缘布局
        listener = new ButtonListener();

        ans = 0;
        expression = "";

        dispaly = new JButton("0");
        dispaly.setEnabled(false);
        add(dispaly, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        add(panel);

        String[] labels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String l : labels)
            addButton(l);
    }

    private void addButton(String label) {
        JButton button = new JButton(label);
        this.panel.add(button);
        button.addActionListener(listener);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String label = e.getActionCommand();
            if (label.equals("=")) {
                ans = calculate();
                dispaly.setText(Double.toString(ans));
                expression = "";

            } else {
                // 此时利用Ans代入计算
                if (expression.equals("") && util.CAL_OPERATORS.contains(label)) {
                    expression += "Ans";
                }
                expression += label;
                dispaly.setText(expression);
            }
        }
    }

    private double calculate() {
        System.out.print(expression +"= ");
        if (expression.contains("Ans")) {
            expression = expression.replaceAll("Ans", String.valueOf(ans));
        }
        String[] exp = util.parseNumbersByDigitInExpression(expression);
        double result = util.evaluationInfixExpression(exp);
        System.out.println(result);
        return result;
    }
}

