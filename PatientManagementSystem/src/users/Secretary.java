package users;

public class Secretary extends User {
    public static Secretary[] secretarys;
    
    public Secretary(String ID, String Password, String Forename, String Surname, String Address) {
        super(ID, Password, Forename, Surname, Address);
    }
    
    public void addSecretary(Secretary newSecretary)
    {
        Secretary[] temp = new Secretary[secretarys.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = secretarys[i];
        }
        
        temp[i] = newSecretary;
        secretarys = temp;
        
        addUser(newSecretary);
    }

    public void approvePatientAccount() {
    }

    public void recieveAppointmentRequest() {
    }

    public void createAppointment() {
    }

    public void giveMedicine() {
    }

    public void orderMedicine() {
    }

    public void removePatient() {
    }

    public void approvePatientAccountTermination() {
    }
}
