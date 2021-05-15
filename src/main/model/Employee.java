package main.model;

public class Employee {
    String userName;
    String role;

    public Employee (String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public Employee() {
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

    public void setRole(String role) {
        this.role = role;
    }
}
