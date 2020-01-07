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

/**
 *
 * @author LoL-1
 */
public class AccountRequestIT {
    
    public AccountRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPassword method, of class AccountRequest.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        //Default password is "password" but hashed
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "5f4dcc3b5aa765d61d8327deb882cf99";
        //Get stored password
        String result = instance.getPassword();
        
        //Pass if stored password is "password" hashed
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class AccountRequest.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        
        //By defualt all User passwords are "password" but hashed
        String Password = Login.hashPassword("password");
        AccountRequest instance = AccountRequest.accountRequests[0];
        
        //Set new password
        instance.setPassword(Password);
        
        //Pass id store password is hashed "password"
        assertEquals(Password, AccountRequest.accountRequests[0].getPassword());
    }

    /**
     * Test of getForename method, of class AccountRequest.
     */
    @Test
    public void testGetForename() {
        Login.defaultData();
        System.out.println("getForename");
        
        //Forename of first AccountRequest is "George" by default
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "George";
        String result = instance.getForename();
        
        //Pass if stored Forename is "Evan"
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class AccountRequest.
     */
    @Test
    public void testSetForename() {
        System.out.println("setName");
        //Set Forename of first AccountRequest to "Jack"
        String Forename = "Jack";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setForename(Forename);
        
        //Pass if stored forename is "Jack"
        assertEquals(Forename, AccountRequest.accountRequests[0].getForename());
    }

    /**
     * Test of getSurname method, of class AccountRequest.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        //Stored surname is "Humphries" by default
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "Humphries";
        
        //Get stored surname
        String result = instance.getSurname();
        
        //Pass if stored Surname is "Ward"
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurname method, of class AccountRequest.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String Surname = "Jones";
        AccountRequest instance = AccountRequest.accountRequests[0];
        
        //Set new Surname
        instance.setSurname(Surname);
        
        //Pass if new Surname is set
        assertEquals(Surname, AccountRequest.accountRequests[0].getSurname());
    }

    /**
     * Test of getAddress method, of class AccountRequest.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        //First AccountRequest in array should have this address by default
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "91 New George St,\nPlymouth,\nPL1 1RQ";
        
        //Get address from first AccountRequest in array
        String result = instance.getAddress();
        
        //Pass if address is expected address
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class AccountRequest.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        
        String Address = "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH";
        //First AccountRequest in array
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setAddress(Address);
        
        //Pass if stored address is new one
        assertEquals(Address, AccountRequest.accountRequests[0].getAddress());
    }

    /**
     * Test of getGender method, of class AccountRequest.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        Login.defaultData();
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "M"; //Default Gender of first AccountRequest is "M"
        
        //Get first users gender
        String result = instance.getGender();
        
        //Pass if result is "M"
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class AccountRequest.
     */
    @Test
    public void testSetGender() {
        System.out.println("setGender");
        
        //Set new Gender
        String Gender = "F";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setGender(Gender);
        
        //Get updated AccountRequest and pass if gender is "F"
        AccountRequest newInstance = AccountRequest.accountRequests[0];
        assertEquals(Gender, newInstance.getGender());
    }

    /**
     * Test of getDOB method, of class AccountRequest.
     */
    @Test
    public void testGetDOB() {
        Login.defaultData();
        System.out.println("getDOB");
        
        //By defualt first AccountRequest DOB is "16/05/1994"
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "16/05/1994";
        
        //Get DOB and compare
        String result = instance.getDOB();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDOB method, of class AccountRequest.
     */
    @Test
    public void testSetDOB() {
        System.out.println("setDOB");
        
        //Set new DOB
        String DOB = "01/03/2000";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setDOB(DOB);
        
        //Pass if new DOB set
        AccountRequest newInstance = AccountRequest.accountRequests[0];
        assertEquals(DOB, newInstance.getDOB());
    }

    /**
     * Test of addAccountRequest method, of class AccountRequest.
     */
    @Test
    public void testAddAccountRequest() {
        System.out.println("addAccountRequest");
        AccountRequest newAccountRequest = new AccountRequest("password", "Evan", "Ward", 
            "81 Greenwood Avenue,\n Pontnewydd,\nCwmbran,\nNP44 5LH", "M", "29/02/2000");
        
        //Add new data to end of array
        newAccountRequest.addAccountRequest(newAccountRequest);
        
        //Get last data in array
        assertEquals(newAccountRequest.getForename(), 
                AccountRequest.accountRequests[AccountRequest.accountRequests.length - 1].getForename());
    }

    /**
     * Test of removeAccountRequest method, of class AccountRequest.
     */
    @Test
    public void testRemoveAccountRequest() {
        System.out.println("removeAccountRequest");
        AccountRequest removeAccountRequest = AccountRequest.accountRequests[0];
        boolean error;
        
        //Remove user from array
        removeAccountRequest.removeAccountRequest(removeAccountRequest);
        
        //If AccountRequest[0] has changed
        if(!(AccountRequest.accountRequests[0].getForename().equals(removeAccountRequest.getForename())))
        {
            //Data removed
            error = false;
        }
        else
        {
            //Data not removed
            error = true;
        }
        
        assertEquals(false, error);
    }

    /**
     * Test of getAccountRequests method, of class AccountRequest.
     */
    @Test
    public void testGetAccountRequests() {
        System.out.println("getAccountRequests");
        
        //Get saved data from file
        AccountRequest.getAccountRequests();
        boolean error;
        
        //If User array isn't empty
        if(AccountRequest.accountRequests != null)
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
     * Test of saveAccountRequests method, of class AccountRequest.
     */
    @Test
    public void testSaveAccountRequests() {
        System.out.println("saveAccountRequests");
        AccountRequest.saveAccountRequests();
        
        String filename = "data/accountRequests.ser";
        boolean error;

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
     * Test of setAccountRequests method, of class AccountRequest.
     */
    @Test
    public void testSetAccountRequests() {
        System.out.println("setAccountRequests");
        
        //Set default AccountRequests
        AccountRequest.setAccountRequests();
        boolean error;
        
        if(AccountRequest.accountRequests != null)
        {
            //Array is full
            error = false;
        }
        else
        {
            //Array is empty/null
            error = true;
        }
        
        
        //Pass if AccountRequest array is not empty/null
        assertEquals(false, error);
    }
    
}
