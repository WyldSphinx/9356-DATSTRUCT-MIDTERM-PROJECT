package GoogleClassroom.ReferenceClass;

public class User {
    private String idNumber;
    private String password;
    private String birthday;

    public User(String idNumber, String password, String birthday) {
        this.idNumber = idNumber;
        this.password = password;
        this.birthday = birthday;
    }

    // Getters and setters
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Authentication method
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}