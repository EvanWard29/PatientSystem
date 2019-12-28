package users;

public class User {

    private String ID;

    private String Forename;
    private String Surname;

    private String Address;

    public User(String ID, String Forename, String Surname, String Address) {
        this.ID = ID;
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

}
