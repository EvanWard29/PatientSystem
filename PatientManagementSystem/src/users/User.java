package users;
import java.io.*;
import system.Notification;

public class User implements Serializable{
    public static User[] users;
    public static User loggedUser;
    private String ID;
    private String Password;
    private String Forename;
    private String Surname;
    private String Address;
    private Notification Notification;

    public User(String ID, String Password, String Forename, String Surname, String Address, Notification Notification) {
        this.ID = ID;
        this.Password = Password;
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
        this.Notification = Notification;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String Forename) {
        this.Forename = Forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Notification getNotification() {
        return Notification;
    }

    public void setNotification(Notification Notification) {
        this.Notification = Notification;
    }
    
    public void removeUser(User removeUser)
    {
        User[] temp = new User[users.length - 1];
        int i = 0;
        
        for(User user : users)
        {
            if(user != removeUser)
            {
                temp[i] = user;
                i++;
            }
        }
        users = temp;
        
        saveUsers();
    }
    
    public void addUser(User newUser)
    {
        User[] temp = new User[users.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = users[i];
        }
        
        temp[i] = newUser;
        users = temp;
        
        saveUsers();
    }

    public static void saveUsers()
    {
        String filename = "data/users.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(users); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
    }
    
    public static void getUsers()
    {
        User[] temp = null;
        String filename = "data/users.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (User[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " + ex); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        User.users = temp;
        
        sortUsers();
    }
    
    private static void sortUsers()
    {
        int numPatients = 0;
        int IP = 0;
        
        int numDoctors = 0;
        int ID = 0;
        
        int numAdmins = 0;
        int IA = 0;
        
        int numSecretarys = 0;
        int IS = 0;
        
        for(User user : User.users)
        {
            if(user instanceof Patient)
            {
                numPatients++;
            }
            else if(user instanceof Doctor)
            {
                numDoctors++;
            }
            else if(user instanceof Admin)
            {
                numAdmins++;
            }
            else if(user instanceof Secretary)
            {
                numSecretarys++;
            }
        }
        
        Patient[] patients = new Patient[numPatients];
        Doctor[] doctors = new Doctor[numDoctors];
        Admin[] admins = new Admin[numAdmins];
        Secretary[] secretarys = new Secretary[numSecretarys];
        
        for(User user : User.users)
        {
            if(user instanceof Patient)
            {
                patients[IP] = (Patient) user;
                IP++;
            }
            else if(user instanceof Doctor)
            {
                doctors[ID] = (Doctor) user;
                ID++;
            }
            else if(user instanceof Admin)
            {
                admins[IA] = (Admin) user;
                IA++;
            }
            else if(user instanceof Secretary)
            {
                secretarys[IS] = (Secretary) user;
                IS++;
            }
        }
        
        Patient.patients = patients;
        Doctor.doctors = doctors;
        Admin.admins = admins;
        Secretary.secretarys = secretarys;
        
        //showUsers();
    }
    
    private static void showUsers()
    {
        for(Patient patient : Patient.patients)
        {
            System.out.println(patient.getID());
        }
        
        for(Doctor doctor : Doctor.doctors)
        {
            System.out.println(doctor.getID());
        }
        
        for(Admin admin : Admin.admins)
        {
            System.out.println(admin.getID());
        }
        
        for(Secretary secretary : Secretary.secretarys)
        {
            System.out.println(secretary.getID());
        }
    }
    
    public static void setUsers()
    {
        User[] temp = {
            new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
            new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", null, "F", "13/06/1998"),
            new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
            new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
            new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
            new Admin("A001", "5f4dcc3b5aa765d61d8327deb882cf99", "James", "Powell", "320 Union St,\nPlymouth,\nPL1 3HP", null),
            new Secretary("S001", "5f4dcc3b5aa765d61d8327deb882cf99", "Sophie", "Owens", "33 Stonehouse St,\nPlymouth,\nPL3 3PE", null)
        };
        
        users = temp;
    }
}
