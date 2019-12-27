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

    public int getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setID(int ID) {
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setName(String Name) {
    }

    public String getAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAddress(String Address) {
    }

    public void login(int id, String password) {
    }
}
