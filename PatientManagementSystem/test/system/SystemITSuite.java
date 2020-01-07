/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author LoL-1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({system.AccountRequestIT.class, system.PastAppointmentIT.class, system.PrescriptionIT.class, system.PrescriptionRequestIT.class, system.MedicineIT.class, system.AppointmentRequestIT.class, system.AppointmentIT.class, system.TerminationRequestIT.class, system.MedicineRequestIT.class, system.FeedbackIT.class})
public class SystemITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
