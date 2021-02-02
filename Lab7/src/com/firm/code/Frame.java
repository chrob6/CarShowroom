package com.firm.code;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,100,500,500);
        this.setLayout(new GridLayout(4,1));
        this.setVisible(true);
    }
}
