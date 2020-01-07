/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import guis.Login;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;

/**
 *
 * @author LoL-1
 */
public class AppointmentRequestIT {
    
    public AppointmentRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addAppointmentRequest method, of class AppointmentRequest.
     */
    @Test
    public void testAddAppointmentRequest() {
        //Reset data in array
        Login.defaultData();
        System.out.println("addAppointmentRequest");
        AppointmentRequest newAppointmentRequest = new AppointmentRequest(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
            new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
            "06/01/2020");
        
        //Add new User to end of array
        newAppointmentRequest.addAppointmentRequest(newAppointmentRequest);
        
        //Get last User in array
        assertEquals(newAppointmentRequest.getPatient().getID(), 
                AppointmentRequest.appointmentRequests[AppointmentRequest.appointmentRequests.length - 1].getPatient().getID());
    }

    /**
     * Test of removeAppointmentRequest method, of class AppointmentRequest.
     */
    @Test
    public void testRemoveAppointmentRequest() {
        System.out.println("removeAppointmentRequest");
        
        AppointmentRequest removeAppointmentRequest = AppointmentRequest.appointmentRequests[0];
        boolean error;
        
        removeAppointmentRequest.removeAppointmentRequest(removeAppointmentRequest);
        
        if(!(AppointmentRequest.appointmentRequests[0].getPatient().getID().equals(removeAppointmentRequest.getPatient().getID())))
        {
            error = false;
        }
        else
        {
            error = true;
        }
    }

    /**
     * Test of saveAppointmentRequests method, of class AppointmentRequest.
     */
    @Test
    public void testSaveAppointmentRequests() {
        System.out.println("saveAppointmentRequests");
        
        //Save AppointmentRequest array to file
        AppointmentRequest.getAppointmentRequests();
        boolean error;
        
        String filename = "data/appointmentRequests.ser";

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
     * Test of getAppointmentRequests method, of class AppointmentRequest.
     */
    @Test
    public void testGetAppointmentRequests() {
        System.out.println("getAppointmentRequests");
        
        //Gets AppointmentRequest array stored in file
        AppointmentRequest.getAppointmentRequests();
        boolean error;
        
        //If AppointmentRequest array isn't empty
        if(AppointmentRequest.appointmentRequests != null)
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
     * Test of setAppointmentRequests method, of class AppointmentRequest.
     */
    @Test
    public void testSetAppointmentRequests() {
        System.out.println("setAppointmentRequests");
        
        //Set default hardcoded Appointments
        AppointmentRequest.setAppointmentRequests();
        boolean error;
        
        //If AppointmentRequest array isn't empty
        if(AppointmentRequest.appointmentRequests != null)
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
