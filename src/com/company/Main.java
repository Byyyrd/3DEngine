package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);

    }
}
