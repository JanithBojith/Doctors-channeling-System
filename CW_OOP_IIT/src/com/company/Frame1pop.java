package com.company;

import com.company.GUI_table;
import com.company.Gui_main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.westminsterSkinConsultationManager.consult;

class Frame1pop extends GUI_table implements ActionListener {
    private JButton back;
    private  JButton Cancel;
    private  JButton ok;

    JTextField getName;
    Frame1pop(){

        JLabel topic = new JLabel();
        topic.setText("YOUR CONSULTATION");
        topic.setBounds(290,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        int loc = (consult.size()-1);

        JLabel details = new JLabel();
        details.setText("<html>"+
                "01.Name    : "+consult.get(loc).getName()+"<br>"+
                "02.Surname : "+consult.get(loc).getSurName()+"</html>");
        details.setBounds(39, 10, 400, 300);
        details.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));

        this.add(topic);
        this.add(details);
        button();
    }

    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== ok) {
            this.dispose();
            new Gui_main();
        }


    }
    @Override
    public void button() {

        ok = new JButton();
        button_set(ok,"OK",290,300,80,30);

    }
}