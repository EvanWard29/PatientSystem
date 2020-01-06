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
import guis.Login;

/**
 *
 * @author LoL-1
 */
public class MedicineRequestIT {
    
    public MedicineRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void removeMedicineRequest()
    {
        Login.defaultData();
        System.out.println("removeMedicineRequest");
        
        MedicineRequest removeMedicineRequest = MedicineRequest.medicineRequests[0];
        boolean error;
        
        //Remove user from array
        removeMedicineRequest.removeMedicineRequest(removeMedicineRequest);
        
        //If Appointment[0] has changed
        if(!(MedicineRequest.medicineRequests[0].getName().equals(removeMedicineRequest.getName())))
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
     * Test of addMedicineRequest method, of class MedicineRequest.
     */
    @Test
    public void testAddMedicineRequest() {
        System.out.println("addMedicineRequest");
        
        MedicineRequest newMedicine = new MedicineRequest("DRUGS", 34);
        newMedicine.addMedicineRequest(newMedicine);
        
        MedicineRequest instance = MedicineRequest.medicineRequests[MedicineRequest.medicineRequests.length - 1];
        
        //Pass if new MedicineRequest added
        assertEquals(newMedicine.getName(), instance.getName());
       
    }

    /**
     * Test of getMedicineRequests method, of class MedicineRequest.
     */
    @Test
    public void testGetMedicineRequests() {
        Login.defaultData();
        System.out.println("getMedicineRequests");
        
        //Get Medicine array from file
        MedicineRequest.getMedicineRequests();
        boolean error;
        
        //If Medicine array isn't empty
        if(MedicineRequest.medicineRequests != null)
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
     * Test of saveMedicineRequests method, of class MedicineRequest.
     */
    @Test
    public void testSaveMedicineRequests() {
        System.out.println("saveMedicineRequests");
        
        //Save Medicine array to file
        MedicineRequest.saveMedicineRequests();
        boolean error;
        
        String filename = "data/medicineRequests.ser";

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
     * Test of setMedicineRequests method, of class MedicineRequest.
     */
    @Test
    public void testSetMedicineRequests() {
        System.out.println("setMedicineRequests");
        MedicineRequest.setMedicineRequests();
        boolean error;
        
        //If MedicineRequest array isn't empty
        if(MedicineRequest.medicineRequests != null)
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
