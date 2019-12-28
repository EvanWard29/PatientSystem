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
public class TerminationRequest {
    public static TerminationRequest[] requests;
    private String PatientID;

    public TerminationRequest(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }
}
