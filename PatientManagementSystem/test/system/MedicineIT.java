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
public class MedicineIT {
    
    public MedicineIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getName method, of class Medicine.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        //Default Name of first Medicine in array is "Chlorpromazine"
        Medicine instance = Medicine.medicines[0];
        String expResult = "Chlorpromazine";
        String result = instance.getName();
        
        //Pass if expected Name is correct
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Medicine.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        
        //Set new Name for first Medicine in array
        String Name = "DRUGS";
        Medicine instance = Medicine.medicines[0];
        instance.setName(Name);
 
        //Pass if new Medicine Name set
        assertEquals(Name, instance.getName());
    }

    /**
     * Test of getStock method, of class Medicine.
     */
    @Test
    public void testGetStock() {
        Login.defaultData();
        System.out.println("getStock");
        
        //First defualt Medicine in array has a Stock of 5
        Medicine instance = Medicine.medicines[0];
        int expResult = 5;
        int result = instance.getStock();
        
        //Pass if expected Stock is correct
        assertEquals(expResult, result);
    }

    /**
     * Test of setStock method, of class Medicine.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        
        //Set new Stock
        int Stock = 0;
        Medicine instance = Medicine.medicines[0];
        instance.setStock(Stock);
        
        //Pass if new Stock set
        assertEquals(Stock, instance.getStock());
    }
    
    /**
     * Test of addMedicine method, of class Medicine.
     */
    @Test
    public void testAddMedicine()
    {
        System.out.println("addMedicine");
        
        Medicine newMedicine = new Medicine("DRUGS", 22);
        newMedicine.addMedicine(newMedicine);
        
        Medicine instance = Medicine.medicines[Medicine.medicines.length -1];
        assertEquals(newMedicine.getName(), instance.getName());
    }

    /**
     * Test of getMedicine method, of class Medicine.
     */
    @Test
    public void testGetMedicine() {
        Login.defaultData();
        System.out.println("getMedicine");
        
        //Get Medicine array from file
        Medicine.getMedicine();
        boolean error;
        
        //If Medicine array isn't empty
        if(Medicine.medicines != null)
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
     * Test of saveMedicine method, of class Medicine.
     */
    @Test
    public void testSaveMedicine() {
        System.out.println("saveMedicine");
        
        //Save Medicine array to file
        Medicine.saveMedicine();
        boolean error;
        
        String filename = "data/medicines.ser";

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
     * Test of setMedicine method, of class Medicine.
     */
    @Test
    public void testSetMedicine() {
        System.out.println("setMedicine");
        
        //Set defualt array of Medicines
        Medicine.setMedicine();
        boolean error;
        
        //If Medicine array isn't empty
        if(Medicine.medicines != null)
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
