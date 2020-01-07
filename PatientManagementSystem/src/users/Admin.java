package users;

import system.Notification;

public class Admin extends User {
    public static Admin[] admins;

    public Admin(String ID, String Password, String Forename, String Surname, String Address, Notification Message) {
        super(ID, Password, Forename, Surname, Address, Message);
    }
    
    public void addAdmin(Admin newAdmin)
    {
        Admin[] temp = new Admin[admins.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = admins[i];
        }
        
        temp[i] = newAdmin;
        admins = temp;
        
        addUser(newAdmin);
    }
}
