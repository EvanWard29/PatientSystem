/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import users.*;
import java.io.*;

/**
 *
 * @author LoL-1
 */
public class Appointment implements Serializable{
    public static Appointment[] appointments;
    private Doctor Doctor;
    private Patient Patient;
    private String Date;

    public Appointment(Doctor Doctor, Patient Patient, String Date) {
        this.Doctor = Doctor;
        this.Patient = Patient;
        this.Date = Date;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public Patient getPatient() {
        return Patient;
    }

    public void setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    public void addAppointment(Appointment newAppointment)
    {
        Appointment[] temp = new Appointment[appointments.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = appointments[i];
        }
        
        temp[i] = newAppointment;
        appointments = temp;
        
        saveAppointments();
        getAppointments();
    }
    
    public void removeAppointment(Appointment removeAppointment)
    {
        Appointment[] temp = new Appointment[appointments.length - 1];
        int i = 0;
        
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment != removeAppointment)
            {
                temp[i] = appointment;
                i++;
            }
        }
        appointments = temp;
        saveAppointments();
        getAppointments();
    }
    
    public static void getAppointments()
    {
        Appointment[] temp = null;
        String filename = "data/appointments.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (Appointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " + ex); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        Appointment.appointments = temp;
    }
    
    public static void saveAppointments()
    {
        String filename = "data/appointments.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(appointments); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setAppointments()
    {   
        Appointment[] temp = {
            new Appointment(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
                    new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", "F", "10/08/1992"),
                    "27/12/2019 12:45"),
            new Appointment(
                    new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU"),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"),
                    "28/12/2019 13:35"),
            new Appointment(
                    new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"), 
                    new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", "F", "13/06/1998"), 
                    "01/01/2020 9:00"),
            new Appointment(
                    new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"), 
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"), 
                    "3/01/2020 10:30")
        };
        
        appointments = temp;
    }
}
