/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;
import java.io.*;

/**
 *
 * @author LoL-1
 */
public class AppointmentIT {
    
    public AppointmentIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getDoctor method, of class Appointment.
     */
    @Test
    public void testGetDoctor() {
        Login.defaultData();
        System.out.println("getDoctor");
        
        Appointment instance = Appointment.appointments[0];
        //Default stored doctor for first Appointment
        Doctor expResult = new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null);
        Doctor result = instance.getDoctor();
        
        //Pass if expected Doctor is equals to stored Doctor
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setDoctor method, of class Appointment.
     */
    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        
        Doctor Doctor = new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null); 
        Appointment instance = Appointment.appointments[0];
        instance.setDoctor(Doctor);
        boolean error;
        
        //If Doctor stored is equals to Doctor set
        if(instance.getDoctor().getID().equals(Doctor.getID()))
        {
            //Data was set
            error = false;
        }
        else
        {
            //Data was not set
            error = true;
        }
        
        //Pass if Doctor was set
        assertEquals(false, error);
    }

    /**
     * Test of getPatient method, of class Appointment.
     */
    @Test
    public void testGetPatient() {
        Login.defaultData();
        System.out.println("getPatient");
        
        //Default stored Patient for first appointment
        Appointment instance = Appointment.appointments[0];
        Patient expResult = new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", 
                "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992");
        Patient result = instance.getPatient();
        
        //Pass if stored Patient is equal to default stored Patient
        assertEquals(expResult.getID(), result.getID());
    }

    /**
     * Test of setPatient method, of class Appointment.
     */
    @Test
    public void testSetPatient() {
        System.out.println("setPatient");
        Patient Patient = new Patient("P004", "password", "Evan", "Ward",
                                "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", null, "M", "29/02/2000");
        Appointment instance = Appointment.appointments[0];
        instance.setPatient(Patient);
        boolean error;
        
        //If stored Patient is equal to set Patient
        if(instance.getPatient().getID().equals(Patient.getID()))
        {
            //Data was set
            error = false;
        }
        else
        {
            //Data was not set
            error = true;
        }
    }

    /**
     * Test of getDate method, of class Appointment.
     */
    @Test
    public void testGetDate() {
        Login.defaultData();
        System.out.println("getDate");
        
        //By defualt first AccountRequest date is "27/12/2019 12:45"
        Appointment instance = Appointment.appointments[0];
        String expResult = "27/12/2019 12:45";
        
        //Get DOB and compare
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Appointment.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        
        //Set new DOB
        String date = "06/01/2020 13:00";
        Appointment instance = Appointment.appointments[0];
        instance.setDate(date);
        
        //Pass if new DOB set
        Appointment newInstance = Appointment.appointments[0];
        assertEquals(date, newInstance.getDate());
    }

    /**
     * Test of addAppointment method, of class Appointment.
     */
    @Test
    public void testAddAppointment() {
        //Reset data in array
        Login.defaultData();
        System.out.println("addAppointment");
        Appointment newAppointment = new Appointment(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
            new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
            "06/01/2020");
        
        //Add new User to end of array
        newAppointment.addAppointment(newAppointment);
        
        //Get last User in array
        assertEquals(newAppointment.getPatient().getID(), 
                Appointment.appointments[Appointment.appointments.length - 1].getPatient().getID());
    }

    /**
     * Test of removeAppointment method, of class Appointment.
     */
    @Test
    public void testRemoveAppointment() {
        Login.defaultData();
        System.out.println("removeAppointment");
        
        Appointment removeAppointment = Appointment.appointments[0];
        boolean error;
        
        //Remove user from array
        removeAppointment.removeAppointment(removeAppointment);
        
        //If Appointment[0] has changed
        if(!(Appointment.appointments[0].getPatient().getID().equals(removeAppointment.getPatient().getID())))
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
     * Test of getAppointments method, of class Appointment.
     */
    @Test
    public void testGetAppointments() {
        System.out.println("getAppointments");
        
        //Gets Appointment array stored in file
        Appointment.getAppointments();
        boolean error;
        
        //If Appointment array isn't empty
        if(Appointment.appointments != null)
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
     * Test of saveAppointments method, of class Appointment.
     */
    @Test
    public void testSaveAppointments() {
        System.out.println("saveAppointments");
        
        //Save Appointment array to file
        Appointment.getAppointments();
        boolean error;
        
        String filename = "data/appointments.ser";

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
     * Test of setAppointments method, of class Appointment.
     */
    @Test
    public void testSetAppointments() {
        System.out.println("setAppointments");
        
        //Set default hardcoded Appointments
        Appointment.setAppointments();
        boolean error;
        
        //If Appointment array isn't empty
        if(Appointment.appointments != null)
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
