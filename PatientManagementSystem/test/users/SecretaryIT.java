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
public class SecretaryIT {
    
    public SecretaryIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addSecretary method, of class Secretary.
     */
    @Test
    public void testAddSecretary() {
        System.out.println("addSecretary");
        
        //Add new Secretary to end of User array
        Secretary newSecretary = new Secretary("P004", "password", 
            "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH");
        newSecretary.addUser(newSecretary);
        
        boolean error;
        //If User at end of User array is the new Secretary
        if(User.users[User.users.length - 1].getID().equals(newSecretary.getID()))
        {
            //Secretary has been added
            error = false;
        }
        else
        {
            //Secretary hasn't been added
            error = true;
        }
        
        //Pass if Secretary has been added to User array
        assertEquals(false, error);
    }
    
}
