package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public interface skinConsultationManager {
    public  static void sort(){}  // with static keyword we can not use static keyword
    public void addDoctor(Doctor doctor);
    public void isEmpty()throws IOException;  //IOException use foe reason of we are using Input output stream in here
    public String navigate (Scanner scanner);
    public void saveData () throws FileNotFoundException, IOException;
    public void deleteDoctor () throws IOException;
    public void displayDoctorTable () throws IOException;
    public static void loadData (){}
}
