/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import guis.Login;

/**
 *
 * @author LoL-1
 */
public class FeedbackIT {
    
    public FeedbackIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getDoctor method, of class Feedback.
     */
    @Test
    public void testGetDoctor() {
        Login.defaultData();
        System.out.println("getDoctor");
        
        //Default Doctor of first Feedback in array
        Feedback instance = Feedback.feedback[0];
        Doctor expResult = new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null);
        Doctor result = instance.getDoctor();
        
        //Pass if expected Doctor is in first Feedback of array
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setDoctor method, of class Feedback.
     */
    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        Doctor Doctor = new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null);
        Feedback instance = Feedback.feedback[0];
        instance.setDoctor(Doctor);
        
        //Pass if new Doctor is set
        assertEquals(Doctor.getID(), instance.getDoctor().getID());
    }

    /**
     * Test of getRating method, of class Feedback.
     */
    @Test
    public void testGetRating() {
        Login.defaultData();
        System.out.println("getRating");
        
        //Default rating of first Feedback in array is 7
        Feedback instance = Feedback.feedback[0];
        String expResult = "7.0";
        String result = Double.toString(instance.getRating());
         
        
        //Pass if rating is 7
        assertEquals(expResult, result);
    }

    /**
     * Test of setRating method, of class Feedback.
     */
    @Test
    public void testSetRating() {
        System.out.println("setRating");
        
        //Set new Rating
        double Rating = 0.0;
        Feedback instance = Feedback.feedback[0];
        instance.setRating(Rating);
        
        //Pass if new rating set
        assertEquals(Double.toString(Rating), Double.toString(instance.getRating()));
    }

    /**
     * Test of getNotes method, of class Feedback.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        
        //Default Notes for first Feedback in array
        Feedback instance = Feedback.feedback[0];
        String expResult = "Very kind and gentle person.";
        
        //Get Notes from first Feedback in array
        String result = instance.getNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotes method, of class Feedback.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        
        //Set new Note
        String note = "Very bad doctor!";
        Feedback instance = Feedback.feedback[0];
        instance.setNotes(note);
        
        //Pass if new Note set
        Feedback newInstance = Feedback.feedback[0];
        assertEquals(note, newInstance.getNotes());
    }

    /**
     * Test of addFeedback method, of class Feedback.
     */
    @Test
    public void testAddFeedback() {
        Login.defaultData();
        System.out.println("addFeedback");
        Feedback newFeedback = new Feedback(
                                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", 
                                        "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                                    0, "Very nice Doctor");

        newFeedback.addFeedback(newFeedback);
        
        assertEquals(Feedback.feedback[Feedback.feedback.length - 1].getDoctor().getID(),
                newFeedback.getDoctor().getID());
        
    }

    /**
     * Test of getFeedback method, of class Feedback.
     */
    @Test
    public void testGetFeedback() {
        Login.defaultData();
        System.out.println("getFeedback");
        
        Feedback.getFeedback();
        boolean error;
        
        //If Feedback array isn't empty
        if(Feedback.feedback != null)
        {
            //Data has been retrieved
            error = false;
        }
        else
        {
            //Data has not been retrieved
            error = true;
        }
        
        assertEquals(false, error);
    }

    /**
     * Test of saveFeedback method, of class Feedback.
     */
    @Test
    public void testSaveFeedback() {
        System.out.println("saveFeedback");
        
        //Save Feedback array to file
        Feedback.saveFeedback();
        boolean error;
        
        String filename = "data/feedback.ser";

        File file = new File(filename);
        
        //If the file isn't empty
        if(file.length() == 0)
        {
            //Data saved
            error = true;
        } 
        else
        {
            //Data not saved
            error = false;
        }
        
        assertEquals(false, error);
    }

    /**
     * Test of setFeedback method, of class Feedback.
     */
    @Test
    public void testSetFeedback() {
        System.out.println("setFeedback");
        
        //Set default hardcoded Appointments
        Feedback.setFeedback();
        boolean error;
        
        //If Feedback array isn't empty
        if(Feedback.feedback != null)
        {
            //Data has been set
            error = false;
        }
        else
        {
            //Data has not been set
            error = true;
        }
        
        //Data needs to have been set to pass
        assertEquals(false, error);
    }
    
}
