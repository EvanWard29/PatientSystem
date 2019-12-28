/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author LoL-1
 */
public class AccountRequest {
    private String Forename;
    private String Surname;
    private String Address;
    private String Gender;
    private String DOB;

    public AccountRequest(String Forename, String Surname, String Address, String Gender, String DOB) {
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
        this.Gender = Gender;
        this.DOB = DOB;
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
    
    
}
