package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class  westminsterSkinConsultationManager  implements skinConsultationManager {
    public static ArrayList<Doctor> doctorStat = new ArrayList<> ();  //we need to access both static and nonstatic areas
    public static ArrayList <Consultation> consult = new ArrayList <>();
    public static void sort(ArrayList<Doctor> list) {

        list.sort((o1, o2)
                -> o1.getSurName().compareTo(
                o2.getSurName()));   //conperision is done here acoding to alphabalitical order.O1 & O2 are objects
    }


    private static final File file = new File ("Store.txt");
    public static westminsterSkinConsultationManager westminsterSkinConsultationManager=new westminsterSkinConsultationManager();

    public void addDoctor (Doctor doctor) {
        doctorStat.add (doctor);
        System.out.println ("congratulations!! successfully added a doctor " + doctorStat.size ());
    }

    public void isEmpty() throws IOException {
        if (doctorStat.isEmpty ()) {
            System.out.println ("NO Doctor HAS BEEN REGISTERed YET !! SO THIS OPTION IS UNAVAILABLE");
            System.out.print ("Do you want to add a new Doctor ? Then enter \"y\" or enter \"n\" for navigate to main menu : " );
            if (navigate (Main1.scanner).equalsIgnoreCase("y")) {
                Main1.AddANewDoctor (Main1.scanner);
            } else {
                Main1.controlMenu (Main1.scanner);
            }
        }
    }

    public String navigate (Scanner scanner) {
        boolean repeater=false;
        String choice;
        do {
            choice = scanner.nextLine ().toLowerCase (Locale.ROOT);
            if (choice.equals ("y")) {
                return "y";
            } else if (choice.equals ("n")) {
                return "n";
            } else {
                repeater = true;
                System.out.print ("input invalid !!!!! please enter your choice again : ");
            }
        } while (repeater);
        return choice;
    }

    public void saveData () throws FileNotFoundException, IOException  {
        FileOutputStream fileOutputStream = new FileOutputStream (file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        try{
            objectOutputStream.writeObject (doctorStat);
        }

        catch (IOException e) {
            e.printStackTrace ();  //print the place of the loaction and error type
        }
        finally{
            objectOutputStream.close();
            fileOutputStream.close();
        }

    }


    public void deleteDoctor () throws IOException {
        isEmpty ();
        String alignToLeft = "|%-30s  %-21s %n";
        System.out.println ("NOW YOU CAN DELETE A DOCTOR FROM THE LIST");
        System.out.println ("THIS IS THE DOCTOR'S LIST");
        System.out.format ("-------------------------------------------------%n");
        System.out.format ("  Doctor Name                      Liscene Number %n");
        System.out.format ("-------------------------------------------------%n");
        doctorStat.forEach (doctor -> System.out.format (alignToLeft, doctor.getName () , doctor.getMedicalLicenceNumber()));
        boolean x = false;
        System.out.print ("\nEnter the doctor's liscence number to delete the doctor from the list : ");
        do {
            String number = Main1.scanner.nextLine ().toLowerCase(Locale.ROOT);
            for (Doctor doctor : doctorStat) {
                if (number.equals (doctor.getMedicalLicenceNumber ())) {
                    doctorStat.remove (doctor);
                    x = true;
                    break;
                }
            }
            if (!x) {
                System.out.println ("Entered doctor is not registered ");
                System.out.print ("please try again with a existing doctor : ");

            }
        } while (!x);
        saveData ();//saving the deletion
        System.out.println ("\n!!!SUCCESSFULLY DOCTOR HAS BEEN DELETED!!!!\n");
        System.out.print ("Do you want to delete another  doctor? \nThen press \"y\" or press \"n\" to navigate main menu : ");

        // this is navigator guide to run through program
        //bellow code clock helps to delete a driver again or go to main menu
        if (navigate (Main1.scanner).equalsIgnoreCase ("y")) {
            deleteDoctor ();
        } else {
            Main1.controlMenu (Main1.scanner);
        }
    }





    public void displayDoctorTable () throws IOException {
        isEmpty ();
        sort(doctorStat);
        System.out.println ("                                                       DOCTOR TABLE");
        String alignToLeft = "| %-21s | %-21s |%-16s |%-13s |%-14s |%-14s|%n";//https://stackoverflow.com/questions/12684368/how-to-left-align-a-fixed-width-string
        System.out.format ("___________________________________________________________________________________________________________________________%n");
        System.out.format("|       Doctor's Surname   |     Doctor's name  | Liscence Number | dataOfBirth  | mobileNumber  |  Specialization  | %n");
        System.out.format("|_______________________   |____________________|_________________|______________|____________   |_______________      |%n");


        //doctorStat.sort (Collections.reverseOrder ());//sorting with positionChanger variables placed as descending oder
        doctorStat.forEach ((Doctor doctor) -> {
            System.out.format (alignToLeft, ((Doctor) doctor).getSurName (),((Doctor) doctor).getName (),((Doctor) doctor).getMedicalLicenceNumber(),
                    ((Doctor) doctor).getDateOfBirth(), ((Doctor) doctor).getMobileNumber(),((Doctor) doctor).getSpecialisation() );
        });//



        System.out.print ("\nDo you want to navigate main menu ? \nThen press \"y\" or press \"n\" to exit the program : ");
        if (navigate (Main1.scanner).equalsIgnoreCase ("y")) {
            Main1.controlMenu (Main1.scanner);
        } else {
            System.out.println ("SAD TO SEE YOU LEAVE GOOD BYE !!!");
            System.exit (1);
        }
    }

    // @Override
    public static void loadData () {
        try {
            FileInputStream fileInputStream = new FileInputStream (file);
            ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);
            doctorStat = (ArrayList<Doctor>) objectInputStream.readObject ();  //we have type cast here using doctor type
            fileInputStream.close ();
            objectInputStream.close ();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }
}
