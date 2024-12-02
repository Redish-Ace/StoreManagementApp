package org.example;

import java.sql.*;

public class StoreDatabase {
    private String url = "jdbc:sqlserver://localhost:1433;instanceName=DESKTOP-F68MM4J;user=Lilian;password=liliankey;encrypt=false;trustServerCertificate=true;database=Store";
    private Connection connection = null;

    public Connection connect(){
        try {
            connection = DriverManager.getConnection(url);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect(){
        try {
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
