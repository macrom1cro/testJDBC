package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;




import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Util() {

    }
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public Connection getConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection error");
        }
        return connection;
    }
}
