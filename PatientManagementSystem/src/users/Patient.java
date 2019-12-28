package users;

public class Patient extends User {
    public static Patient[] patients;
    
    private String Gender;

    private String DOB;

    public Patient(String ID, String Forename, String Surname, String Address, String Gender, String DOB) {
        super(ID, Forename, Surname, Address);
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
