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
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.runners.MethodSorters;

/**
 *
 * @author LoL-1
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserIT {
    
    public UserIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getID method, of class User.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        
        //First User ID is "P001" by default
        User instance = User.users[0];
        String expResult = "P001";
        String result = instance.getID();
        
        //Pass if stored User ID is "P001"
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class User.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        
        //Set first User's id to "ID"
        String ID = "ID";
        User instance = User.users[0];
        instance.setID(ID);
        
        //Pass if stored ID is "ID"
        assertEquals(ID, User.users[0].getID());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        //Default password is "password" but hashed
        User instance = User.users[0];
        String expResult = "5f4dcc3b5aa765d61d8327deb882cf99";
        //Get stored password
        String result = instance.getPassword();
        
        //Pass if stored password is "password" hashed
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        
        //By defualt all User passwords are "password" but hashed
        String Password = Login.hashPassword("password");
        User instance = User.users[0];
        
        //Set new password
        instance.setPassword(Password);
        
        //Pass id store password is hashed "password"
        assertEquals(Password, User.users[0].getPassword());
    }

    /**
     * Test of getForename method, of class User.
     */
    @Test
    public void testGetForename() {
        Login.defaultData();
        System.out.println("getForename");
        
        //Forename of first User is "Evan" by default
        User instance = User.users[0];
        String expResult = "Evan";
        String result = instance.getForename();
        
        //Pass if stored Forename is "Evan"
        assertEquals(expResult, result);
    }

    /**
     * Test of setForename method, of class User.
     */
    @Test
    public void testSetForename() {
        System.out.println("setForename");
        
        //Set Forename of first User to "Jack"
        String Forename = "Jack";
        User instance = User.users[0];
        instance.setForename(Forename);
        
        //Pass if stored forename is "Jack"
        assertEquals(Forename, User.users[0].getForename());
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        //Stored surname is "Ward" by default
        User instance = User.users[0];
        String expResult = "Ward";
        
        //Get stored surname
        String result = instance.getSurname();
        
        //Pass if stored Surname is "Ward"
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setSurname method, of class User.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String Surname = "Jones";
        User instance = User.users[0];
        
        //Set new Surname
        instance.setSurname(Surname);
        
        //Pass if new Surname is set
        assertEquals(Surname, User.users[0].getSurname());
    }

    /**
     * Test of getAddress method, of class User.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        //First user in array should have this address by default
        User instance = User.users[0];
        String expResult = "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ";
        
        //Get from first User in array
        String result = instance.getAddress();
        
        //Pass if address is expected address
        assertEquals(expResult, result); 
    }

    /**
     * Test of setAddress method, of class User.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String Address = "81 Greenwood Avenue\\nPontnewydd,\\nCwmbran,\\nNP44 5LH";
        //First user in array
        User instance = User.users[0];
        instance.setAddress(Address);
        
        //Pass if stored address is new one
        assertEquals(Address, User.users[0].getAddress());
    }

    /**
     * Test of removeUser method, of class User.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        User removeUser = User.users[2];
        boolean error;
        
        //Remove user from array
        removeUser.removeUser(removeUser);
        
        //If User[2] has changed
        if(!(User.users[2].getID().equals(removeUser.getID())))
        {
            //User removed
            error = false;
        }
        else
        {
            //User not removed
            error = true;
        }
        
        assertEquals(false, error);
        
    }

    /**
     * Test of addUser method, of class User.
     */
    @Test
    public void testAddUser() {
        //Reset data in array
        Login.defaultData();
        System.out.println("addUser");
        User newUser = new User("A002", "password", "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", null);
        
        //Add new User to end of array
        newUser.addUser(newUser);
        
        //Get last User in array
        assertEquals(newUser.getID(), User.users[User.users.length - 1].getID());
    }

    /**
     * Test of saveUsers method, of class User.
     */
    @Test
    public void testSaveUsers() {
        System.out.println("saveUsers");
        //Save User array to file
        User.saveUsers();
        boolean error;
        
        String filename = "data/users.ser";

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
     * Test of getUsers method, of class User.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        //Gets User array stored in file
        User.getUsers();
        boolean error;
        
        //If User array isn't empty
        if(User.users != null)
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
     * Test of setUsers method, of class User.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        //Set default hardcoded Users
        User.setUsers();
        boolean error;
        
        //If User array isn't empty
        if(User.users != null)
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
