package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static Connection connection = null;

    private SQLConnection(){
        String url = "jdbc:sqlserver://localhost:1433;user=sa;password=admin";

        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Successful Connection = " + connection.getClass().getCanonicalName());
        }catch (SQLException e){
            System.out.println("Failed Connection: " + e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(connection == null){
            new SQLConnection();
        }
        return connection;
    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}