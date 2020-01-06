/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import java.io.*;
/**
 *
 * @author LoL-1
 */
public class MedicineRequest extends Medicine{
    public static MedicineRequest[] medicineRequests;
    
    public MedicineRequest(String Name, int Stock) {
        super(Name, Stock);
    }
    
    public void removeMedicineRequest(MedicineRequest removeMedicineRequest)
    {
        MedicineRequest[] temp = new MedicineRequest[medicineRequests.length - 1];
        int i = 0;
        
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests)
        {
            if(medicineRequest != removeMedicineRequest)
            {
                temp[i] = medicineRequest;
                i++;
            }
        }
        medicineRequests = temp;
        saveMedicineRequests();
        getMedicineRequests();
    }
    
    public void addMedicineRequest(MedicineRequest newMedicine)
    {
        MedicineRequest[] temp = new MedicineRequest[medicineRequests.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = medicineRequests[i];
        }
        
        temp[i] = newMedicine;
        medicineRequests = temp;
        
        saveMedicineRequests();
        getMedicineRequests();
    }
    
    public static void getMedicineRequests()
    {
        MedicineRequest[] temp = null;
        String filename = "data/medicineRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (MedicineRequest[])in.readObject(); 
              
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
        
        medicineRequests = temp;
    }
    
    public static void saveMedicineRequests()
    {
        String filename = "data/medicineRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(medicineRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        }
    }
    
    public static void setMedicineRequests()
    {
        MedicineRequest[] temp = {
            new MedicineRequest("Paracetamol", 45),
            new MedicineRequest("Iburprofen", 33)
        };
        
        medicineRequests = temp;
    }
}
