package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Gui_main extends GUI_table implements ActionListener {
    private JButton button1,button2;

    public Gui_main(){
        ImageIcon img = new ImageIcon("src/GUI/home.jpg");
        JLabel label = new JLabel();                                                      //Adding the Topic.
        label.setText("SKIN CONSULTATION MANAGER");
        label.setIcon(img);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setBounds(10,2,400,450);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setBackground(Color.white);
        label.setOpaque(true);

        JLabel label1 = new JLabel();
        label1.setBounds(0,0,1000,700);
        label1.setBackground(Color.white);
        label1.setOpaque(true);
        button();

        this.add(label);
        this.add(label1);

        window("Westminster Skin Consultation Manager",1000,700);
    }
    public void button_set(JButton button,String name , int y) {
        button.setBounds(420,y,350,40);
        button.setText(name);
        button.setFont(new Font(Font.SERIF,Font.BOLD,20));
        button.setForeground(new Color(0xFFFFFF));
        button.setFocusable(false);
        button.setBackground(Color.red);
        button.addActionListener(this);
        this.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1) {
            this.dispose();
            new Frame1();
        } else if (e.getSource()==button2) {
            this.dispose();
            new Frame2(true);

        }
    }
    @Override
    public void button() {
        button1 = new JButton();
        button_set(button1,"CONSULT A DOCTOR",300);
        button2 = new JButton();
        button_set(button2,"DOCTOR DETAILS",400);


    }
}