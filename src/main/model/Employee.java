package main.model;

public class Employee {
    String userName;
    String firstName, lastName, role, secretQ, secretA, password;

    public Employee (String userName, String firstName, String lastName, String role, String secretQ, String secretA, String password) {
        this.userName = userName;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secretA = secretA;
        this.secretQ = secretQ;
        this.password = password;
    }

    public Employee() {
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
}
