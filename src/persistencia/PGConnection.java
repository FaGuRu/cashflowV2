package persistencia;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class PGConnection {

    private static PGConnection instancia =null;
    private static Connection connection =null;

    public static PGConnection getInstance() {
        if(instancia==null) {
            instancia = new PGConnection();
            instancia.Connection();
        }
        return instancia;
    }

    public void Connection() {
        String connectionString ="jdbc:postgresql://localhost:5432/cashflow";
        String user = "postgres";
        String password = "Sword0Shield155";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionString,user,password);
            if(connection!=null) {
                System.out.println("Conexion Postgres exitosa");
            }else {
                System.out.println("Conexion Postgres fallida");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Connection getConnection() {
        return connection;
    }

}