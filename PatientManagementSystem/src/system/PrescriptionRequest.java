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
public class PrescriptionRequest extends Prescription {
    public static PrescriptionRequest[] prescriptionRequests;
    
    public PrescriptionRequest(Doctor Doctor, Patient Patient, String Notes, Medicine Medicine, int Quantity, String Dosage) {
        super(Doctor, Patient, Notes, Medicine, Quantity, Dosage);
    }
    
    public void removePrescriptionRequest(PrescriptionRequest removePrescriptionRequest)
    {
        PrescriptionRequest[] temp = new PrescriptionRequest[prescriptionRequests.length - 1];
        int i = 0;
        for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests)
        {
            if(prescriptionRequest != removePrescriptionRequest)
            {
                temp[i] = prescriptionRequest;
                i++;
            }
        }
        prescriptionRequests = temp;
        
        savePrescriptionRequests();
        getPrescriptionRequests();
    }
    
    public static void addPrescriptionRequest(PrescriptionRequest newPrescriptionRequest)
    {
        PrescriptionRequest[] temp = new PrescriptionRequest[prescriptionRequests.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = prescriptionRequests[i];
        }
        
        temp[i] = newPrescriptionRequest;
        prescriptionRequests = temp;
        
        savePrescriptionRequests();
        getPrescriptionRequests();
    }
    
    public static void getPrescriptionRequests()
    {
        PrescriptionRequest[] temp = null;
        String filename = "data/prescriptionRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (PrescriptionRequest[])in.readObject(); 
              
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
        
        prescriptionRequests = temp;
    }
    
    public static void savePrescriptionRequests()
    {
        String filename = "data/prescriptionRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(prescriptionRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        }
    }
    
    public static void setPrescriptionRequests()
    {
        PrescriptionRequest[] temp = {
            new PrescriptionRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"),
                new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", "F", "13/06/1998"),
                "N/A", new Medicine("Penicillin", 3), 5, "1 EVERY 12 HOURS")
        };
        
        prescriptionRequests = temp;
    }
}
