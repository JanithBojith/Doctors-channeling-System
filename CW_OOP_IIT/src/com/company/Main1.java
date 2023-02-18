package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Scanner;

import static com.company.westminsterSkinConsultationManager.doctorStat;
import static java.lang.System.exit;

public class Main1 implements Serializable {

    public static Scanner scanner = new Scanner(System.in);
    public static boolean repeater;
    public static String detail;
    public static String enterData;
    public static String reEnter;
    public static String doctorName;
    public static String doctorSurName;
    public static String doctorDateOfBirth;
    public static String doctorMobileNumber;
    public static String medicalLicenceNumber;
    public static String specialisation;

    // checking  the inputs using wild card characters
    public static String validatorString = "[a-zA-Z. ]*";
    public static String validate = "[0-9/]*";
    public static  westminsterSkinConsultationManager westminsterSkinConsultationManager = new westminsterSkinConsultationManager ();
    public static void main(String[] args) throws IOException {
        westminsterSkinConsultationManager.loadData();
        controlMenu(scanner);
        String choice=scanner.nextLine();



    }

    public static void controlMenu(Scanner scanner) throws IOException {
        System.out.println();
        System.out.println("A.Add a new doctor ");
        System.out.println("B.Delete a doctor ");
        System.out.println("C.Display the list");
        System.out.println("D.Exit the program");
        System.out.println("E.Call the GUI Console");
        while (!repeater) {
            System.out.println("Please enter your choice : ");
            String choice = scanner.nextLine().toLowerCase(Locale.ROOT);

            while (!repeater) {
                switch (choice) {
                    case "a" : {
                        System.out.println("Do you want to add a doctor ?");
                        AddANewDoctor(scanner);
                        repeater = true;
                        break;
                    }

                    case "b" :{
                        System.out.println("Do you want to delete a doctor");
                        westminsterSkinConsultationManager.deleteDoctor();
                        repeater=true;
                        break;
                    }
                    case "c":{
                        System.out.println("Do you want to display the list of the doctors'");
                        westminsterSkinConsultationManager.displayDoctorTable();
                        repeater=true;
                        break;
                    }
                    case "d":{
                        System.out.println("Do you want to exit the program ");
                        exit(0);
                    }
                    case "e":{
                        System.out.println("Calling gui console");
                        new Gui_main();
                        repeater=true;
                        break;
                    }
                    default : System.out.println("\nplease read the instruction and try a again \n");
                        break;
                }


            }

        }
    }

    public static void doctorInformationCollector (Scanner scanner, String wordChanger,String validator) {
        System.out.print ("Please enter "+wordChanger);
        detail = scanner.nextLine ();//

        //wrong input  catcher
        if (detail == null || !detail.matches (validator)||  detail.equals ("")) {
            System.out.println ("please enter your "+wordChanger+"correctly ");
            doctorInformationCollector (scanner, reEnter,validator);
        }
        enterData = detail.toLowerCase(Locale.ROOT);
    }

    public static void AddANewDoctor(Scanner scanner) throws IOException {
        reEnter = "doctor Name: ";
        doctorInformationCollector (scanner, "doctor Name : ",validatorString);
        doctorName = enterData;


        /*westminsterSkinConsultationManager.doctorStat.forEach(hospital -> {
            if (Doctor.MedicalLicenceNumber.toString().equals(detail)) {
                System.out.println("Doctor's name is already entered");
                addDoctor(scanner);
            }
        });*/
        reEnter = "doctor SurName: ";
        doctorInformationCollector (scanner, "doctor SurName : ",validatorString);
        doctorSurName = enterData;

        reEnter = "doctor data Of Birth: ";
        doctorInformationCollector (scanner, "doctor data Of Birth : ",validate);
        doctorDateOfBirth = enterData;

        reEnter = "doctor mobile Number: ";
        doctorInformationCollector (scanner, "doctor mobile Number : ",validate);
        doctorMobileNumber = enterData;

        reEnter = "doctor medical Licence Number: ";
        doctorInformationCollector (scanner, "doctor medical Licence Number : ",validate);
        medicalLicenceNumber = enterData;

        reEnter = "doctor specialisation: ";
        doctorInformationCollector (scanner, "doctor specialisation : ",validatorString);
        specialisation = enterData;

        Doctor doctor = new Doctor (doctorName, doctorSurName, doctorDateOfBirth, doctorMobileNumber,medicalLicenceNumber, specialisation);
        westminsterSkinConsultationManager.addDoctor(doctor);//saving method
        System.out.println ("Do you want to add another doctor ? :");
        System.out.print ("if it is yes ? then press \"y\" or enter \"n\" to navigate main menu : ");
        boolean repeater = false;
        if(doctorStat.size() <10){
            do {
                String choice = scanner.nextLine ().toLowerCase (Locale.ROOT);
                if (choice.equals ("y")) {
                    AddANewDoctor (scanner);
                } else if (choice.equals ("n")) {
                    westminsterSkinConsultationManager.saveData();

                    controlMenu (scanner);
                } else {
                    repeater = true;
                    System.out.print ("input invalid !!!!! please enter your choice again : ");
                }
            } while (repeater);
        }
        else
        {System.out.println("you can only  register a maximum of 10 doctors only");}

    }
}
