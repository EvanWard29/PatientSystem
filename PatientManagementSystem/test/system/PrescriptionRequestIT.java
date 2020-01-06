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
import users.Patient;
import guis.Login;

/**
 *
 * @author LoL-1
 */
public class PrescriptionRequestIT {
    
    public PrescriptionRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of removePrescriptionRequest method, of class PrescriptionRequest.
     */
    @Test
    public void testRemovePrescriptionRequest() {
        Login.defaultData();
        System.out.println("removePrescriptionRequest");
        
        //PrescriptionRequest to remove:
        PrescriptionRequest removePrescriptionRequest = PrescriptionRequest.prescriptionRequests[0];
        boolean error;
        
        //Remove user from array
        removePrescriptionRequest.removePrescriptionRequest(removePrescriptionRequest);
        
        PrescriptionRequest instance = PrescriptionRequest.prescriptionRequests[0];
        
        //If Appointment[0] has changed
        if(!(removePrescriptionRequest.getDoctor().getID().equals(instance.getDoctor().getID())))
        {
            //Appointment removed
            error = false;
        }
        else
        {
            //Appointment not removed
            error = true;
        }
        
        assertEquals(false, error);
    }

    /**
     * Test of addPrescriptionRequest method, of class PrescriptionRequest.
     */
    @Test
    public void testAddPrescriptionRequest() {
        Login.defaultData();
        System.out.println("addPrescriptionRequest");
        PrescriptionRequest newPrescriptionRequest = new PrescriptionRequest(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
            new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", "F", "10/08/1992"),
            "Health slowly decreasing.", new Medicine("Paracetemol", 18), 10, "2 EVERY 12 HOURS");
        newPrescriptionRequest.addPrescriptionRequest(newPrescriptionRequest);
        
        PrescriptionRequest instance = PrescriptionRequest.prescriptionRequests[PrescriptionRequest.prescriptionRequests.length - 1];
        
        assertEquals(newPrescriptionRequest.getDoctor().getID(), instance.getDoctor().getID());
    }

    /**
     * Test of getPrescriptionRequests method, of class PrescriptionRequest.
     */
    @Test
    public void testGetPrescriptionRequests() {
        System.out.println("getPrescriptionRequests");
        
        //Get PrescriptionRequest array from file
        PrescriptionRequest.getPrescriptionRequests();
        boolean error;
        
        //If PrescriptionRequest array isn't empty
        if(PrescriptionRequest.prescriptionRequests != null)
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
     * Test of savePrescriptionRequests method, of class PrescriptionRequest.
     */
    @Test
    public void testSavePrescriptionRequests() {
        System.out.println("savePrescriptionRequests");
        
        //Save PrescriptionRequest array to file
        PrescriptionRequest.savePrescriptionRequests();
        boolean error;
        
        String filename = "data/prescriptionRequests.ser";

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
     * Test of setPrescriptionRequests method, of class PrescriptionRequest.
     */
    @Test
    public void testSetPrescriptionRequests() {
        System.out.println("setPrescriptionRequests");
        
        //Set default Prescription Request array
        PrescriptionRequest.setPrescriptionRequests();
        boolean error;
        
        //If PrescriptionRequest array isn't empty
        if(PrescriptionRequest.prescriptionRequests != null)
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
