package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.company.westminsterSkinConsultationManager.doctorStat;

class Frame2 extends GUI_table implements ActionListener {

    private JButton back,reset,sort;
    Frame2( boolean cat){

        JLabel topic = new JLabel();
        topic.setText("DOCTORS DETAILS");
        topic.setBounds(350,20,650,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));


        JLabel colum = new JLabel();
        colum.setText("|    Name    |   SurName   |    Birthday    | Phone-Number | Medical-Licence |Specialisation|");
        colum.setBounds(33,-70,850,300);
        colum.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        colum.setForeground(new Color(246, 10, 43, 255));


        DefaultTableModel tableModel = new DefaultTableModel(0,6);
        JTable table = new JTable(tableModel);
        table.setBounds(35,100,800,300);
        if (cat){
            for (Doctor doctor : doctorStat) {
                String[] details = {doctor.getName(), doctor.getSurName(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getMedicalLicenceNumber(), doctor.getSpecialisation()};
                tableModel.addRow(details);

            }
        }else{

            int i = doctorStat.size();
            String [] sort = new String[i];
            for (int j = 0; j < i;j++) {
                sort[j]=doctorStat.get(j).getSurName();
            }

            Arrays.sort(sort);
            for (String s : sort) {
                for (Doctor doctor : doctorStat) {
                    if (s.equals(doctor.getSurName())) {
                        String[] details = {doctor.getName(), doctor.getSurName(), String.valueOf(doctor.getDateOfBirth()),doctor.getMobileNumber(), doctor.getMedicalLicenceNumber(), doctor.getSpecialisation()};
                        tableModel.addRow(details);
                    }
                }
            }
        }

        this.add(topic);
        this.add(colum);
        this.add(table);
        button();
        window("DOCTOR DETAILS",900,500);

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
        if (e.getSource()==back) {
            this.dispose();
            new Gui_main();//animus class call
        } else if (e.getSource()==reset) {
            this.dispose();
            new Frame2(true);

        } else if (e.getSource()==sort){
            this.dispose();
            new Frame2(false);
        }

    }
    @Override
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        reset = new JButton();
        button_set(reset,"Reset",40,410,65,30);

        sort = new JButton();
        button_set(sort,"Sort",750,410,80,30);

    }
}
