package users;

public class Patient extends User {
    public static Patient[] patients;
    
    private String Gender;

    private String DOB;

    public Patient(String ID, String Password, String Forename, String Surname, String Address, String Gender, String DOB) {
        super(ID, Password, Forename, Surname, Address);
        this.Gender = Gender;
        this.DOB = DOB;
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
    
    public void addPatient(Patient newPatient)
    {
        Patient[] temp = new Patient[patients.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = patients[i];
        }
        
        temp[i] = newPatient;
        patients = temp;
    }

    public void requestAccountCreation() {
    }

    public void rateDoctor(int doctorID, String note) {
    }

    public void viewDoctorRating(int doctorID) {
    }

    public void requestAppointment() {
    }

    public void viewPatientHistory() {
    }

    public void viewAppointment() {
    }

    public void viewPrescription() {
    }

    public void requestTermination() {
    }
}
