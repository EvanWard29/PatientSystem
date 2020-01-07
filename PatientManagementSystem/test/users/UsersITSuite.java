/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author LoL-1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({users.DoctorIT.class, users.SecretaryIT.class, users.PatientIT.class, users.UserIT.class, users.AdminIT.class})
public class UsersITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
