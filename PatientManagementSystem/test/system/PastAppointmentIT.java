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
public class PastAppointmentIT {
    
    public PastAppointmentIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPastPrescription method, of class PastAppointment.
     */
    @Test
    public void testGetPastPrescription() {
        System.out.println("getPastPrescription");
        
        //Default first PastAppointment in array:
        Prescription expResult = new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
                    new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", "F", "10/08/1992"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Penicillin", 5), 6, "1 EVERY 48 HOURS");
        PastAppointment instance = PastAppointment.pastAppointments[0];
        
        Prescription result = instance.getPastPrescription();
        
        //Pass if PastPrescription is correct
        assertEquals(expResult.getDoctor().getID(), result.getDoctor().getID());
    }

    /**
     * Test of setPastPrescription method, of class PastAppointment.
     */
    @Test
    public void testSetPastPrescription() {
        System.out.println("setPastPrescription");
        PastAppointment pastAppointment = PastAppointment.pastAppointments[0];
        Prescription pastPrescription = new Prescription(new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"), 
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"),
                "Health is looking good.", new Medicine("Paracetemol", 15), 19, "2 EVERY 12 HOURS");
        pastAppointment.setPastPrescription(pastPrescription);
        
        assertEquals(pastAppointment.getPastPrescription().getDoctor().getID(), pastPrescription.getDoctor().getID());
    }

    /**
     * Test of addPastAppointment method, of class PastAppointment.
     */
    @Test
    public void testAddPastAppointment() {
        System.out.println("addPastAppointment");
        PastAppointment newPastAppointment = new PastAppointment(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ"),
            new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"),
            "06/01/2020",
            new Prescription(new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN"), 
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", "M", "29/02/2000"),
                "Health is looking good.", new Medicine("Paracetemol", 15), 19, "2 EVERY 12 HOURS"));
        
        //Add new User to end of array
        newPastAppointment.addPastAppointment(newPastAppointment);
        
        //Get last User in array
        assertEquals(newPastAppointment.getPatient().getID(), 
                PastAppointment.pastAppointments[PastAppointment.pastAppointments.length - 1].getPatient().getID());
    }

    /**
     * Test of getPastAppointments method, of class PastAppointment.
     */
    @Test
    public void testGetPastAppointments() {
        Login.defaultData();
        System.out.println("getPastAppointments");
        
        //Get PastAppoitnments array from file
        PastAppointment.getPastAppointments();
        boolean error;
        
        //If PastAppointment array isn't empty
        if(PastAppointment.pastAppointments != null)
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
     * Test of savePastAppointments method, of class PastAppointment.
     */
    @Test
    public void testSavePastAppointments() {
        System.out.println("savePastAppointments");
        
        //Save PastAppointment array to file
        PastAppointment.savePastAppointments();
        boolean error;
        
        String filename = "data/pastAppointments.ser";

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
     * Test of setPastAppointments method, of class PastAppointment.
     */
    @Test
    public void testSetPastAppointments() {
        System.out.println("setPastAppointments");
        PastAppointment.setPastAppointments();
        boolean error;
        
        //If PastAppointment array isn't empty
        if(PastAppointment.pastAppointments != null)
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
