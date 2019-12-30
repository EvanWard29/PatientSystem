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
public class AppointmentRequest extends Appointment{

    public AppointmentRequest(Doctor Doctor, users.Patient Patient, String Date) {
        super(Doctor, Patient, Date);
    }
}
