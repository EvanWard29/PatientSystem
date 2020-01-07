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
import users.Patient;
import guis.Login;

/**
 *
 * @author LoL-1
 */
public class TerminationRequestIT {
    
    public TerminationRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPatient method, of class TerminationRequest.
     */
    @Test
    public void testGetPatient() {
        System.out.println("getPatient");
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        Patient expResult = new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992");
        Patient result = instance.getPatient();
        
        //Pass if Patient is in array
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setPatientID method, of class TerminationRequest.
     */
    @Test
    public void testSetPatientID() {
        Login.defaultData();
        System.out.println("setPatientID");
        Patient Patient = new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992");
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        instance.setPatientID(Patient);
        
        assertEquals(Patient.getID(), instance.getPatient().getID());
    }

    /**
     * Test of removeTerminationRequest method, of class TerminationRequest.
     */
    @Test
    public void testRemoveTerminationRequest() {
        System.out.println("removeTerminationRequest");
        TerminationRequest removeTerminationRequest = TerminationRequest.terminationRequests[0];
        boolean error;
        
        //Remove user from array
        removeTerminationRequest.removeTerminationRequest(removeTerminationRequest);
        
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        
        //If TerminationRequest[0] has changed
        if(!(removeTerminationRequest.getPatient().getID().equals(instance.getPatient().getID())))
        {
            //TermiantionRequest removed
            error = false;
        }
        else
        {
            //TerminationRequest not removed
            error = true;
        }
        
        assertEquals(false, error);
    }

    /**
     * Test of addTerminationRequest method, of class TerminationRequest.
     */
    @Test
    public void testAddTerminationRequest() {
        System.out.println("addTerminationRequest");
        
        //New TerminationRequest:
        TerminationRequest newTerminationRequest = new TerminationRequest(
            new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", null, "F", "13/06/1998"));
        newTerminationRequest.addTerminationRequest(newTerminationRequest);
       
        int i = TerminationRequest.terminationRequests.length - 1;
        TerminationRequest instance = TerminationRequest.terminationRequests[i];
        
        //Pass if TerminationRequest added to array
        assertEquals(newTerminationRequest.getPatient().getID(), instance.getPatient().getID());
    }

    /**
     * Test of getTerminationRequests method, of class TerminationRequest.
     */
    @Test
    public void testGetTerminationRequests() {
        System.out.println("getTerminationRequests");
        
        //Get TerminationRequest array from file
        TerminationRequest.getTerminationRequests();
        boolean error;
        
        //If TerminationRequest array isn't empty
        if(TerminationRequest.terminationRequests != null)
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
     * Test of saveTerminationRequests method, of class TerminationRequest.
     */
    @Test
    public void testSaveTerminationRequests() {
        System.out.println("saveTerminationRequests");
        
        //Save TerminationRequest array to file
        TerminationRequest.saveTerminationRequests();
        boolean error;
        
        String filename = "data/terminationRequests.ser";

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
     * Test of setTerminationRequests method, of class TerminationRequest.
     */
    @Test
    public void testSetTerminationRequests() {
        System.out.println("setTerminationRequests");
        
        //Set default Termination Requests
        TerminationRequest.setTerminationRequests();
        boolean error;
        
        //If TerminationRequest array isn't empty
        if(TerminationRequest.terminationRequests != null)
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
