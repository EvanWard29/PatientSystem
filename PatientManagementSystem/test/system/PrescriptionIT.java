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
public class PrescriptionIT {
    
    public PrescriptionIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getDoctor method, of class Prescription.
     */
    @Test
    public void testGetDoctor() {
        Login.defaultData();
        System.out.println("getDoctor");
        
        //Default Doctor of first Prescription:
        Prescription instance = Prescription.prescriptions[0];
        Doctor expResult = new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null);
        Doctor result = instance.getDoctor();
        
        //Pass if expected Doctor is in array
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setDoctor method, of class Prescription.
     */
    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        
        //Set new Doctor:
        Doctor Doctor = new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null);
        Prescription instance = Prescription.prescriptions[0];
        instance.setDoctor(Doctor);
        
        //Pass if new Doctor set
        assertEquals(Doctor, instance.getDoctor());
    }

    /**
     * Test of getPatient method, of class Prescription.
     */
    @Test
    public void testGetPatient() {
        Login.defaultData();
        System.out.println("getPatient");
        
        //Default Patient of first Prescription:
        Prescription instance = Prescription.prescriptions[0];
        Patient expResult = new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000");
        Patient result = instance.getPatient();
        
        
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setPatient method, of class Prescription.
     */
    @Test
    public void testSetPatient() {
        System.out.println("setPatient");
        
        //Set new Patient:
        Patient Patient = new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", null, "F", "13/06/1998");
        Prescription instance = Prescription.prescriptions[0];
        instance.setPatient(Patient);
        
        //Pass if new Patient set
        assertEquals(Patient, instance.getPatient());
    }

    /**
     * Test of getNotes method, of class Prescription.
     */
    @Test
    public void testGetNotes() {
        Login.defaultData();
        System.out.println("getNotes");
        
        //Default values for first Prescription in array:
        Prescription instance = Prescription.prescriptions[0];
        String expResult = "Showing signs of health improving.\nOn course for a full recovery.";
        String result = instance.getNotes();
        
        //Pass if expected Notes is in array
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotes method, of class Prescription.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        
        //Set new Note:
        String Notes = "Very bad Doctor!";
        Prescription instance = Prescription.prescriptions[0];
        instance.setNotes(Notes);
        
        //Pass if new Note is set
        assertEquals(Notes, instance.getNotes());
    }

    /**
     * Test of getMedicine method, of class Prescription.
     */
    @Test
    public void testGetMedicine() {
        Login.defaultData();
        System.out.println("getMedicine");
        
        //Default first Prescription Medicine:
        Prescription instance = Prescription.prescriptions[0];
        Medicine expResult = new Medicine("Chlorpromazine", 5);
        Medicine result = instance.getMedicine();
        
        //Pass if expected Medicine is in array
        assertEquals(expResult.getName(), result.getName());
    }

    /**
     * Test of setMedicine method, of class Prescription.
     */
    @Test
    public void testSetMedicine() {
        System.out.println("setMedicine");
        
        //Set new Medicine
        Medicine Medicine = new Medicine("Iburprofen", 8);
        Prescription instance = Prescription.prescriptions[0];
        instance.setMedicine(Medicine);

        //Pass if new Medicine set
        assertEquals(Medicine.getName(), instance.getMedicine().getName());
    }

    /**
     * Test of getQuantity method, of class Prescription.
     */
    @Test
    public void testGetQuantity() {
        Login.defaultData();
        System.out.println("getQuantity");
        
        //Default first Prescription in array:
        Prescription instance = Prescription.prescriptions[0];
        int expResult = 4;
        int result = instance.getQuantity();
        
        //Pass if expected Quantity is in array
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class Prescription.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        
        //Set new Quantity for first Prescription in array
        int Quantity = 0;
        Prescription instance = Prescription.prescriptions[0];
        instance.setQuantity(Quantity);
        
        //Pass if new Quantity set
        assertEquals(Quantity, instance.getQuantity());
    }

    /**
     * Test of getDosage method, of class Prescription.
     */
    @Test
    public void testGetDosage() {
        Login.defaultData();
        System.out.println("getDosage");
        
        //Default value of first Prescription in array:
        Prescription instance = Prescription.prescriptions[0];
        String expResult = "1 EVERY 24 HOURS";
        String result = instance.getDosage();
        
        //Pass if expected Dosage is in array
        assertEquals(expResult, result);
    }

    /**
     * Test of setDosage method, of class Prescription.
     */
    @Test
    public void testSetDosage() {
        System.out.println("setDosage");
        
        //Set new Dosage
        String Dosage = "1 EVERY 4 HOURS";
        Prescription instance = Prescription.prescriptions[0];
        instance.setDosage(Dosage);
        
        //Pass if new Dosage set
        assertEquals(Dosage, instance.getDosage());
    }

    /**
     * Test of removePrescription method, of class Prescription.
     */
    @Test
    public void testRemovePrescription() {
        System.out.println("removePrescription");
        Prescription removePrescription = Prescription.prescriptions[0];
        boolean error;
        
        //Remove user from array
        removePrescription.removePrescription(removePrescription);
        
        //If Appointment[0] has changed
        if(!(Prescription.prescriptions[0].getPatient().getID().equals(removePrescription.getPatient().getID())))
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
     * Test of addPrescription method, of class Prescription.
     */
    @Test
    public void testAddPrescription() {
        Login.defaultData();
        System.out.println("addPrescription");
        Prescription newPrescription = new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                    new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Penicillin", 5), 6, "1 EVERY 48 HOURS");
        newPrescription.addPrescription(newPrescription);
        
        Prescription instance = Prescription.prescriptions[Prescription.prescriptions.length - 1];
        
        boolean error;
        if(((newPrescription.getDoctor().getID()).equals(instance.getDoctor().getID())) && 
                (newPrescription.getPatient().getID().equals(instance.getPatient().getID())))
        {
            error = false;
        }
        else
        {
            error = true;
        }
        
        //Pass if prescription added
        assertEquals(false, error);
    }

    /**
     * Test of savePrescriptions method, of class Prescription.
     */
    @Test
    public void testSavePrescriptions() {
        System.out.println("savePrescriptions");
        
        //Save Prescription array to file
        Prescription.savePrescriptions();
        boolean error;
        
        String filename = "data/prescriptions.ser";

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
     * Test of getPrescriptions method, of class Prescription.
     */
    @Test
    public void testGetPrescriptions() {
        System.out.println("getPrescriptions");
        
        //Gets Prescription array from file
        Prescription.getPrescriptions();
        boolean error;
        
        //If Prescription array isn't empty
        if(Prescription.prescriptions != null)
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
     * Test of setPrescriptions method, of class Prescription.
     */
    @Test
    public void testSetPrescriptions() {
        System.out.println("setPrescriptions");
        Prescription.setPrescriptions();
        boolean error;
        
        //If Prescription array isn't empty
        if(Prescription.prescriptions != null)
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
