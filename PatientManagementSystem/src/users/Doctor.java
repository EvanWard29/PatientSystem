package users;

public class Doctor extends User {
    public static Doctor[] doctors;
    
    public Doctor(String ID, String Password, String Forename, String Surname, String Address) {
        super(ID, Password, Forename, Surname, Address);
    }
    
    public void addDoctor(Doctor newDoctor)
    {
        Doctor[] temp = new Doctor[doctors.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = doctors[i];
        }
        
        temp[i] = newDoctor;
        doctors = temp;
    }
}
