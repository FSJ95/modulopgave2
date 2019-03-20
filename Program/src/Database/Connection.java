package Database;

import java.sql.*;
import java.sql.DriverManager;

public class Connection {

    // Connection settings.
    private java.sql.Connection conn = null;
    private String sqlHost;
    private String sqlUsername;
    private String sqlPassword;

    // Konstruktor.
    public Connection() {
        this.sqlHost = "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowMultiQueries=true";
        this.sqlUsername = "root";
        this.sqlPassword = "password";
    }

    // Konstruktor med connection input.
    public Connection(String sqlHost, String sqlUsername, String sqlPassword) {
        this.sqlHost = sqlHost;
        this.sqlUsername = sqlUsername;
        this.sqlPassword = sqlPassword;
    }


    // Metode der returnerer connection.
    public java.sql.Connection getConnection() {
        try {

            conn = DriverManager.getConnection(sqlHost, sqlUsername, sqlPassword);
        } catch (SQLException e) {

            System.out.println("Exception: " + e);

        }

        return conn;

    }

}

