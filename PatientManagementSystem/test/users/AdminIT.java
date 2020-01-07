/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LoL-1
 */
public class AdminIT {
    
    public AdminIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addAdmin method, of class Admin.
     */
    @Test
    public void testAddAdmin() {
        System.out.println("addAdmin");
        
        //Add new Admin to end of User array
        Admin newAdmin = new Admin("P004", "password", 
            "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", null);
        newAdmin.addUser(newAdmin);
        
        boolean error;
        //If User at end of User array is the new Admin
        if(User.users[User.users.length - 1].getID().equals(newAdmin.getID()))
        {
            //Admin has been added
            error = false;
        }
        else
        {
            //Admin hasn't been added
            error = true;
        }
        
        //Pass if Admin has been added to User array
        assertEquals(false, error);
    }
    
}
