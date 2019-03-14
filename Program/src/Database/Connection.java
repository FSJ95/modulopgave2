package Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connection {

    // Connection settings.
    private java.sql.Connection conn = null;
    private String sqlHost = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private String sqlUsername = "root";
    private String sqlPassword = "fo76jatosata";

    // Konstruktor.
    public Connection() {

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

