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
public class Prescription {
    private String DoctorID;
    private String PatientID;
    private String Notes;
    private Medicine Medicine;
    private String Quantity;
    private String Dosage;

    public Prescription(String DoctorID, String PatientID, String Notes, Medicine Medicine, String Quantity, String Dosage) {
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.Notes = Notes;
        this.Medicine = Medicine;
        this.Quantity = Quantity;
        this.Dosage = Dosage;
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

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public Medicine getMedicine() {
        return Medicine;
    }

    public void setMedicine(Medicine Medicine) {
        this.Medicine = Medicine;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String Dosage) {
        this.Dosage = Dosage;
    }
    
    
}
