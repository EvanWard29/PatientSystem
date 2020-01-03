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
public class AppointmentRequest extends Appointment{
    public static AppointmentRequest[] appointmentRequests;
    
    public AppointmentRequest(Doctor Doctor, users.Patient Patient, String Date) {
        super(Doctor, Patient, Date);
    }
    
    public void addAppointmentRequest(AppointmentRequest newAppointmentRequest)
    {
        AppointmentRequest[] temp = new AppointmentRequest[appointmentRequests.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = appointmentRequests[i];
        }
        
        temp[i] = newAppointmentRequest;
        appointmentRequests = temp;
        
        saveAppointments();
        getAppointments();
    }
    
    public void removeAppointmentRequest(AppointmentRequest removeAppointmentRequest)
    {
        AppointmentRequest[] temp = new AppointmentRequest[appointmentRequests.length - 1];
        int i = 0;
        
        for(AppointmentRequest appointmentRequest : appointmentRequests)
        {
            if(appointmentRequest != removeAppointmentRequest)
            {
                temp[i] = appointmentRequest;
                i++;
            }
        }
        appointmentRequests = temp;
        saveAppointmentRequests();
    }
    
    public static void saveAppointmentRequests()
    {
        String filename = "data/appointmentRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(appointmentRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void getAppointmentRequests()
    {
        AppointmentRequest[] temp = null;
        String filename = "data/appointmentRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (AppointmentRequest[])in.readObject(); 
              
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
        
        appointmentRequests = temp;
    }
    
    public static void setAppointmentRequests()
    {
        AppointmentRequest[] temp = {
            new AppointmentRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"),
                "03/01/2020 12:45"),
            new AppointmentRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"),
                new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", "F", "13/06/1998"),
                "05/01/2020 13:00"),
            new AppointmentRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
                new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", "F", "10/08/1992"),
                "11/01/2020 11:30")
        };
        appointmentRequests = temp;
    }
}
