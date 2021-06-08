package main.object;

public class Employee {
    private int id;
    private String userName, firstName, lastName, role, secretQ, secretA, password;
    private Boolean admin;

    public Employee(int id, String userName, String firstName, String lastName, String role, String secretQ, String secretA, String password, Boolean admin) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secretA = secretA;
        this.secretQ = secretQ;
        this.password = password;
        this.admin = admin;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecretQ() {
        return secretQ;
    }

    public String getSecretA() {
        return secretA;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return admin;
    }
}
