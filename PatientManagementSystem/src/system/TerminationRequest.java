/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import java.io.*;
import users.*;

/**
 *
 * @author LoL-1
 */
public class TerminationRequest implements Serializable{
    public static TerminationRequest[] terminationRequests;
    private Patient Patient;

    public TerminationRequest(Patient Patient) {
        this.Patient = Patient;
    }

    public Patient getPatient() {
        return Patient;
    }

    public void setPatientID(Patient Patient) {
        this.Patient = Patient;
    }
    
    public void removeTerminationRequest(TerminationRequest removeTerminationRequest)
    {
        TerminationRequest[] temp = new TerminationRequest[terminationRequests.length - 1];
        int i = 0;
        for(TerminationRequest terminationRequest : terminationRequests)
        {
            if(terminationRequest != removeTerminationRequest)
            {
                temp[i] = terminationRequest;
                i++;
            }
        }
        terminationRequests = temp;
        
        saveTerminationRequests();
        getTerminationRequests();
    }
    
    public void addTerminationRequest(TerminationRequest newTerminationRequest)
    {
        TerminationRequest[] temp = new TerminationRequest[terminationRequests.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = terminationRequests[i];
        }
        
        temp[i] = newTerminationRequest;
        terminationRequests = temp;
        
        saveTerminationRequests();
        getTerminationRequests();
    }
    
    public static void getTerminationRequests()
    {
        TerminationRequest[] temp = null;
        String filename = "data/terminationRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (TerminationRequest[])in.readObject(); 
              
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
        
        terminationRequests = temp;
    }
    
    public static void saveTerminationRequests()
    {
        String filename = "data/terminationRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(terminationRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setTerminationRequests()
    {
        TerminationRequest[] temp = {
            new TerminationRequest(new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                    "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", "F", "10/08/1992"))
        };
        terminationRequests = temp;
    }
}
