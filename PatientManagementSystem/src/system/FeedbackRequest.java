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
public class FeedbackRequest extends Feedback{
    public static FeedbackRequest[] feedbackRequests;
        
    public FeedbackRequest(users.Doctor Doctor, int Rating, String Notes) {
        super(Doctor, Rating, Notes);
    }
    
    public void addFeedbackRequest(FeedbackRequest newFeedbackRequest)
    {
        
    }
    
    public void removeFeedbackRequest(FeedbackRequest removeFeedbackRequest)
    {
        FeedbackRequest[] temp = new FeedbackRequest[feedbackRequests.length - 1];
        int i = 0;
        for(FeedbackRequest feedbackRequest : feedbackRequests)
        {
            if(feedbackRequest != removeFeedbackRequest)
            {
                temp[i] = feedbackRequest;
                i++;
            }
        }
        feedbackRequests = temp;
        
        saveFeedbackRequests();
        getFeedbackRequests();
    }
    
    public static void saveFeedbackRequests()
    {
        String filename = "data/feedbackRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(feedbackRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void getFeedbackRequests()
    {
        FeedbackRequest[] temp = null;
        String filename = "data/feedbackRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (FeedbackRequest[])in.readObject(); 
              
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
        
        feedbackRequests = temp;
    }
    
    public static void setFeedbackRequests()
    {
        FeedbackRequest[] temp ={
            new FeedbackRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                7, "Very nice doctor."),
            new FeedbackRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                9, "Very kind and friendly doctor."),
            new FeedbackRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                7, "Always a pleasure to have Dr. Brooks!")
        };
        feedbackRequests = temp;
    }
}
