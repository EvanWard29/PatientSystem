public class Patient extends User {

    private String Gender;

    private int Age;

    public Patient(String Gender, int Age, int ID, String Name, String Address) {
        super(ID, Name, Address);
        this.Gender = Gender;
        this.Age = Age;
    }

    public String getGender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGender(String Gender) {
    }

    public int getAge() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAge(int Age) {
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
