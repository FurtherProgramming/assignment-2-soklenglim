package main.model.user;

import main.SQLConnection;

import java.sql.*;

public class UserModel {
    Connection connection;

    public UserModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


}