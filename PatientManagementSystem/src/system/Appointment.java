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
public class Appointment {
    public static Appointment[] appointments;
    private String DoctorID;
    private String PatientID;
    private String Date;

    public Appointment(String DoctorID, String PatientID, String Date) {
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.Date = Date;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    
}
