/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import users.*;
/**
 *
 * @author LoL-1
 */
public class Feedback {
    private Doctor Doctor;
    private String Rating;
    private String Notes;

    public Feedback(Doctor Doctor, String Rating, String Notes) {
        this.Doctor = Doctor;
        this.Rating = Rating;
        this.Notes = Notes;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }
    
    
}
