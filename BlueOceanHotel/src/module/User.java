package module;

public class User {
    private String fullName;
    private String IDNumber;
    private String Dob;
    private String userName;
    private String password;
    private int Admin;

    public User(String fullName, String IDNumber, String dob, String userName, String password, int admin) {
        this.fullName = fullName;
        this.IDNumber = IDNumber;
        Dob = dob;
        this.userName = userName;
        this.password = password;
        Admin = admin;
    }

    public User(String IDNumber, String dob, String userName, String password, int admin) {
        this.fullName ="";
        this.IDNumber = IDNumber;
        Dob = dob;
        this.userName = userName;
        this.password = password;
        Admin = admin;
    }

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int admin) {
        if(admin==0 || admin==1){
            Admin = admin;
        }else{
            admin=0;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", IDNumber='" + IDNumber + '\'' +
                ", Dob='" + Dob + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", Admin=" + Admin +
                '}';
    }
}
