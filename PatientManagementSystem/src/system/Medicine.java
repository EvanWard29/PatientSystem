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
public class Medicine implements Serializable{
    public static Medicine[] medicines; //GET MEDICINES FROM FILE
    
    private String Name;
    private int Stock;

    public Medicine(String Name, int Stock) {
        this.Name = Name;
        this.Stock = Stock;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    
    public static void getMedicine()
    {
        Medicine[] temp = null;
        String filename = "data/medicines.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (Medicine[])in.readObject(); 
              
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
        
        medicines = temp;
    }
    
    public static void saveMedicine()
    {
        String filename = "data/medicines.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(medicines); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setMedicine()
    {
        //DEFAULT OBJECTS SET ONLY ONCE FOR Medicine
        
        Medicine[] temp = {
            new Medicine("Chlorpromazine", 5),
            new Medicine("Polio Vaccine", 7),
            new Medicine("Oral Contraceptives", 3),
            new Medicine("Penicillin", 3),
            new Medicine("Beta Blocker", 15),
            new Medicine("Beta2 Agonists", 25),
            new Medicine("Tamoxifen", 3),
            new Medicine("Immunosuppressants", 27),
            new Medicine("HIV/AIDS Antiretrovirals", 18),
            new Medicine("MMR Vaccine", 11)
        };
        
        medicines = temp;
    }
}
