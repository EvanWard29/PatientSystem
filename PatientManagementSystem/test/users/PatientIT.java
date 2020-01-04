/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LoL-1
 */
public class PatientIT {
    
    public PatientIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getGender method, of class Patient.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        Login.defaultData();
        
        Patient instance = (Patient) User.users[0];
        String expResult = "M"; //Default Gender of first user is "M"
        
        //Get first users gender
        String result = instance.getGender();
        
        //Pass if result is "M"
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class Patient.
     */
    @Test
    public void testSetGender() {
        System.out.println("setGender");
        
        //Set new Gender
        String Gender = "F";
        Patient instance = (Patient)User.users[0];
        instance.setGender(Gender);
        
        //Get updated Patient and pass if gender is "F"
        Patient newInstance = (Patient)User.users[0];
        assertEquals(Gender, newInstance.getGender());
    }

    /**
     * Test of getDOB method, of class Patient.
     */
    @Test
    public void testGetDOB() {
        System.out.println("getDOB");
        
        //By defualt first Patient DOB is "29/02/2000"
        Patient instance = (Patient)User.users[0];
        String expResult = "29/02/2000";
        
        //Get DOB and compare
        String result = instance.getDOB();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDOB method, of class Patient.
     */
    @Test
    public void testSetDOB() {
        System.out.println("setDOB");
        
        //Set new DOB
        String DOB = "01/03/2000";
        Patient instance = (Patient)User.users[0];
        instance.setDOB(DOB);
        
        //Pass if new DOB set
        Patient newInstance = (Patient)User.users[0];
        assertEquals(DOB, newInstance.getDOB());
    }

    /**
     * Test of removePatient method, of class Patient.
     */
    @Test
    public void testRemovePatient() {
        System.out.println("removePatient");
        
        //Remove first patient from array
        Patient removePatient = (Patient)User.users[0];
        removePatient.removeUser(removePatient);
        
        boolean error;
        //If first User is not removed Patient
        if(!(removePatient.getID().equals(User.users[0].getID())))
        {
            //Patient has been removed
            error = false;
        }
        else
        {
            //Patient hasn't been removed
            error = true;
        }
        
        //Pass if error is false
        assertEquals(false, error);
    }

    /**
     * Test of addPatient method, of class Patient.
     */
    @Test
    public void testAddPatient() {
        System.out.println("addPatient");
        
        //Add new Patient to end of User array
        Patient newPatient = new Patient("P004", "password", 
            "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", "M", "29/02/2000");
        newPatient.addUser(newPatient);
        
        boolean error;
        //If User at end of User array is the new Patient
        if(User.users[User.users.length - 1].getID().equals(newPatient.getID()))
        {
            //Patient has been added
            error = false;
        }
        else
        {
            //Patient hasn't been added
            error = true;
        }
        
        //Pass if Patient has been added to User array
        assertEquals(false, error);
        
    }
    
}
