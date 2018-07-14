package cn.styxs.calculator;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame appFrame = new JFrame();
                appFrame.setTitle("SimpleCalculator");
                CalculatorPanel panel = new CalculatorPanel();
                appFrame.add(panel);
                appFrame.pack();
                appFrame.setSize(appFrame.getWidth()*2,appFrame.getHeight());
                appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                appFrame.setVisible(true);
            }
        });

    }
}
