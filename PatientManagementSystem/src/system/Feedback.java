/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import users.*;
import java.io.*;
/*
 *
 * @author LoL-1
 */
public class Feedback  implements Serializable{
    public static Feedback[] feedback;
    private Doctor Doctor;
    private int Rating;
    private String Notes;

    public Feedback(Doctor Doctor, int Rating, String Notes) {
        this.Doctor = Doctor;
        this.Rating = Rating;
        this.Notes = Notes;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }
    
    public void addFeedback(Feedback newFeedback)
    {
        Feedback[] temp = new Feedback[feedback.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = feedback[i];
        }
        
        temp[i] = newFeedback;
        feedback = temp;
        
        saveFeedback();
        getFeedback();
    }
    
    public static void getFeedback()
    {
        Feedback[] temp = null;
        String filename = "data/feedback.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (Feedback[])in.readObject(); 
              
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
        
        feedback = temp;
    }
    
    public static void saveFeedback()
    {
        String filename = "data/feedback.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(feedback); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setFeedback()
    {
        Feedback[] temp = {
            new Feedback(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                7, "Very kind and gentle person."),
            new Feedback(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                5, "Nice person but hard to understand."),
            new Feedback(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                3, "Seems very rough and confused half the time"),
            new Feedback(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                7, "Very nice doctor."),
            new Feedback(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                9, "Very kind and friendly doctor."),
            new Feedback(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                7, "Always a pleasure to have Dr. Brooks!")
        };
        feedback = temp;
    }
}
