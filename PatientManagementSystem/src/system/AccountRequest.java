/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import java.io.*;

/**
 *
 * @author LoL-1
 */
public class AccountRequest implements Serializable{
    public static AccountRequest[] accountRequests;
    
    private String Password;
    private String Forename;
    private String Surname;
    private String Address;
    private String Gender;
    private String DOB;

    public AccountRequest(String Password, String Forename, String Surname, String Address, String Gender, String DOB) {
        this.Password = Password;
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
        this.Gender = Gender;
        this.DOB = DOB;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getForename() {
        return Forename;
    }

    public void setName(String Name) {
        this.Forename = Forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    public static void removeAccountRequest(AccountRequest removeAccountRequest)
    {
        AccountRequest[] temp = new AccountRequest[accountRequests.length - 1];
        int i = 0;
        
        for(AccountRequest accountRequest : AccountRequest.accountRequests)
        {
            if(accountRequest != removeAccountRequest)
            {
                temp[i] = accountRequest;
                i++;
            }
        }
        accountRequests = temp;
        
        saveAccountRequests();
        getAccountRequests();
    }
    
    public static void getAccountRequests()
    {
        AccountRequest[] temp = null;
        String filename = "data/accountRequests.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (AccountRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " + ex); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        accountRequests = temp;
    }
    
    public static void saveAccountRequests()
    {
        String filename = "data/accountRequests.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(accountRequests); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setAccountRequests()
    {
        AccountRequest[] temp = {
            new AccountRequest("5f4dcc3b5aa765d61d8327deb882cf99","George", "Humphries", "91 New George St,\nPlymouth,\nPL1 1RQ", "M", "16/05/1994"),
            new AccountRequest("5f4dcc3b5aa765d61d8327deb882cf99", "Emilie", "Huntley", "14-16,\nUnion St,\nPlymouth,\nPL1 2SR", "F", "13/11/1998")
        };
        
        accountRequests = temp;
    }
}
