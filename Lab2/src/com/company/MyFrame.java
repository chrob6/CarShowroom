package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame(){

        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0x00468b));
        //frame.setResizable(false);
        //this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));
    }
}
