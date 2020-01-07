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
public class DoctorIT {
    
    public DoctorIT() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addDoctor method, of class Doctor.
     */
    @Test
    public void testAddDoctor() {
        System.out.println("addDoctor");
        
        //Add new Doctor to end of User array
        Doctor newDoctor = new Doctor("P004", "password", 
            "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", null);
        newDoctor.addUser(newDoctor);
        
        boolean error;
        //If User at end of User array is the new Doctor
        if(User.users[User.users.length - 1].getID().equals(newDoctor.getID()))
        {
            //Doctor has been added
            error = false;
        }
        else
        {
            //Doctor hasn't been added
            error = true;
        }
        
        //Pass if Doctor has been added to User array
        assertEquals(false, error);
    }
    
}
