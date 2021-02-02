package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    int count =0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public GUI() {
        frame = new JFrame();

        JButton button = new JButton("Click me");
        button.addActionListener(this);

        label = new JLabel("Number of clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
        panel.setLayout(new GridLayout());
        panel.add(button);
        panel.add(label);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        count++;
        label.setText("Number of clicks" + count);
    }
}
