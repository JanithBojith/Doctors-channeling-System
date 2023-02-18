package com.company;

import com.company.Consultation;
import com.company.Doctor;
import com.company.GUI_table;
import com.company.Gui_main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.westminsterSkinConsultationManager.consult;
import static com.company.westminsterSkinConsultationManager.doctorStat;

class Frame1 extends GUI_table implements ActionListener {


    private String name,surname,phoneNo,docConsulId,note,sttimeHou,sttimeMin,stasettime,endTimeHour,dateOfBirth;

    private String filename = null;

    private int cost,patId;
    private int again = 0;

    private LocalDate cousulDate;

    private LocalTime consulStart,consulEnd,ensettime;
    private JButton back,Cancel,submit,pic;
    JTextField getName,getBirthday,getId,getDate,getSurname,getPhone;
    JComboBox getDoc,getStartTime1,getStartTime2, getDura;
    JTextArea getNote;
    JLabel topic,colum,topic1,jName,birthday,id,time,date,jnote,addPho,addPhopath;
    Frame1(){


        topic = new JLabel();
        topic.setText("ALL DOCTORS DETAILS");
        set_lable(topic,290,20,550,20,20);


        colum = new JLabel();
        colum.setText("|     Name     |   SurName   | Mobile Number | MedicalLiscence| Specialisation |");
        colum.setForeground(new Color(246, 10, 43, 255));
        set_lable(colum,39, -70, 750, 300,15);


        DefaultTableModel tableModel = new DefaultTableModel(0,5);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 240);

        for (Doctor doctor : doctorStat) {
            String[] details = {doctor.getName(), doctor.getSurName(), doctor.getMobileNumber(), doctor.getMedicalLicenceNumber(), doctor.getSpecialisation()};
            tableModel.addRow(details);
        }

        topic1 = new JLabel();
        topic1.setText("DOCTOR CONSULTATION");
        set_lable(topic1,280, 360, 550, 20,20);


        jName = new JLabel();
        jName.setText("NAME                 :                    SURNAME           :");
        set_lable(jName,30, 420, 550, 20,15);


        getName = new JTextField();
        set_textfield(getName,240,425,150,20);

        getSurname = new JTextField();
        set_textfield(getSurname,600,425,150,20);


        birthday = new JLabel();
        birthday.setText("BIRTHDAY(YYYY-MM-DD)  :                    PHONE NUMBER      :");
        set_lable(birthday,30, 470, 550, 20,15);


        getBirthday = new JTextField();
        set_textfield(getBirthday,240,475,150,20);

        getPhone = new JTextField();
        set_textfield(getPhone,600,475,150,20);

        id = new JLabel();
        id.setText("PATIENT ID           :                    DOCTOR LICENCE ID :");
        set_lable(id,30, 520, 550, 20,15);


        getId = new JTextField();
        set_textfield(getId,240,525,150,20);


        String [] doc = new String[doctorStat.size()];
        for(int i = 0;doctorStat.size()>i;i++){
            doc[i]=doctorStat.get(i).getMedicalLicenceNumber();
        }
        getDoc =new JComboBox(doc);
        set_combobox(getDoc,600,525,150,20);


        time = new JLabel();
        time.setText("CONSULTATION START TIME:                 CONSULTATION DURATION:");
        set_lable(time,30, 570, 700, 20,15);


        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22","23" };
        String[] minutes = { "MM","00", "10", "20", "30","40","50" };
        String[] duration = { "HH","01", "02", "03", "04", "05"};

        //J combo box
        getStartTime1 =new JComboBox(hours);
        set_combobox(getStartTime1,260,575,50,20);


        getStartTime2 =new JComboBox(minutes);
        set_combobox(getStartTime2 ,340,575,50,20);

        getDura =new JComboBox(duration);
        set_combobox(getDura,600,575,150,20);

        date = new JLabel();
        date.setText("CONSULTATION DATE(YYYY-MM-DD):");
        set_lable(date,30, 620, 550, 20,15);


        getDate = new JTextField();
        set_textfield(getDate,300,625,150,20);

        jnote = new JLabel();
        jnote.setText("     NOTES :   ");
        set_lable(jnote,30, 670, 550, 20,15);


        getNote = new JTextArea();
        getNote.setBounds(30,700,500,200);
        getNote.setFont(new Font("console",Font.ITALIC,15));
        getNote.setLineWrap(true);

   /*     addpho = new JLabel();
        addpho.setBounds(540, 700, 220, 200);
        addpho.setBackground(Color.white);
        addpho.setOpaque(true);

        addphopath = new JLabel();
        addphopath.setBounds(540, 660, 150, 20);
        addphopath.setBackground(Color.white);
        addphopath.setOpaque(true);*/




        this.add(table);
        this.add(getNote);
        //  this.add(addpho);
        //  this.add(addphopath);
        button();
        window("Consultation",800,1000);
    }
    public void check_equal(){
        int timedura = Integer.parseInt(endTimeHour);
        boolean not_equal = true;
        for (Consultation consultation : consult) {
            if (docConsulId.equals(consultation.getdrID())) {
                if (consultation.getdate().isEqual(cousulDate)) {
                    if (!consultation.getConsulStart().isBefore(consulStart) && !consultation.getConsulEnd().isAfter(consulEnd)) {
                        not_equal = false;
                        break;
                    }
                }
            }
        }
        for(Consultation consultation : consult){
            if(Objects.equals(consultation.getpatientId(), patId)){
                again++;
            }
        }
        if(again==0){
            cost=(timedura*15);
            System.out.println(cost);

        }else{
            cost = 25*timedura;
            System.out.println(cost);
        }
        if(not_equal){
            consult.add(new Consultation(name, surname, dateOfBirth, phoneNo,patId,docConsulId, consulStart,consulEnd,cousulDate,note,cost));
        }else{
            int docsiz=doctorStat.size();
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                random[i] = doctorStat.get(i).getMedicalLicenceNumber();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(random.length);
            docConsulId = random[randomIndex];
            check_equal();
        }
    }
    public void set_combobox(JComboBox combo,int x, int y , int width , int height){
        combo.setBounds(x,y,width,height);
        combo.setBackground(new Color(0xFFFFFF));
        this.add(combo);
    }
    public void set_lable(JLabel label ,int x, int y , int width , int height,int font){
        label.setBounds(x,y,width,height);
        label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,font));
        this.add(label);
    }

    public void set_textfield(JTextField textfild,int x, int y , int width , int height){//150,20
        textfild.setBounds(x,y,width,height);
        textfild.setFont(new Font("console",Font.ITALIC,15));
        this.add(textfild);
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

        if (e.getSource()== submit) {
            try {
                String regex = "^[A-Za-z]\\w{2,29}$";
                Pattern p = Pattern.compile(regex);
                name = getName.getText().trim();
                surname = getSurname.getText().trim();
                Matcher f = p.matcher(name);
                Matcher s = p.matcher(surname);
                if (f.matches() && s.matches()) {
                    try {
                        dateOfBirth = getBirthday.getText().trim();
                        phoneNo = getPhone.getText().trim();
                        // validation for mobilenumber
                        if (10 == phoneNo.length()) {
                            try {
                                Integer.parseInt(phoneNo);
                                try {
                                    patId = Integer.parseInt(getId.getText());
                                    docConsulId = (String) getDoc.getSelectedItem();
                                    sttimeHou = (String) getStartTime1.getSelectedItem();
                                    sttimeMin = (String) getStartTime2.getSelectedItem();
                                    stasettime = sttimeHou + ":" + sttimeMin + ":00";
                                    try {
                                        consulStart = LocalTime.parse(stasettime);
                                        endTimeHour = (String) getDura.getSelectedItem();
                                        consulEnd = consulStart.plusHours(Long.parseLong(endTimeHour));
                                        System.out.println(consulEnd);
                                        try {
                                            cousulDate = LocalDate.parse(getDate.getText().trim());
                                            if (cousulDate.isAfter(LocalDate.now()) && cousulDate.isBefore(LocalDate.now().plusYears(3))) {
                                                note = getNote.getText();
                                                check_equal();
                                                System.out.println(consult.get(0));
                                                try {
                                                    FileInputStream file = new FileInputStream(addPhopath.getText());
                                                    byte data[] = new byte[file.available()];
                                                    file.read(data);
                                                    int i = 0;

                                                    for (byte b : data) {
                                                        data[i] = (byte) (b ^ patId);
                                                        i++;
                                                    }
                                                    FileOutputStream fos = new FileOutputStream(patId + name + again + ".jpg");

                                                    fos.write(data);
                                                    fos.close();
                                                    file.close();
                                                    // System.out.println("Encryption Done...");
                                                    this.dispose();
                                                    new Gui_main();

                                                } catch (Exception ignored) {
//                                                    this.dispose();
//                                                    new Frame1pop();
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Check You Entered Consultation Date", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            ///validate cousulDAte

                                        } catch (Exception ignored) {
                                            JOptionPane.showMessageDialog(null, "Check You Entered Date!!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (Exception ignored) {
                                        JOptionPane.showMessageDialog(null, "Check You Entered Time and Duration!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (Exception ignored) {
                                    System.out.println("ffff");
                                    JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }catch (Exception ignored){
                                JOptionPane.showMessageDialog(null, "Check Your Patient ID!!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null, "Check Your Birthday!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Check Your First Name And Surname!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"SOMETHING WRONG!!","Error", JOptionPane.ERROR_MESSAGE);
            }

            this.dispose();
            new Frame1pop();

        }else if (e.getSource()== pic){
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                addPho.setIcon(new ImageIcon(f.toString()));
                filename = f.getAbsolutePath();
                addPhopath.setText(filename);
            }catch(Exception ignored){}
        }else{
            this.dispose();
            new Gui_main();
        }

    }

    @Override
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back,"Back",30,20,65,30);

        submit = new JButton();
        button_set(submit,"Submit",550,920,80,30);

        Cancel = new JButton();
        button_set(Cancel,"Cancel",660,920,80,30);

    }
}