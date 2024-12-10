package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance; // Instanță unică
    private Connection connection;

    private DatabaseConnection() {
        try {
            // String-ul de conexiune pentru autentificare Windows
            String url = "jdbc:sqlserver://DARIA\\SQLEXPRESS:1433;databaseName=AnimalShelter;integratedSecurity=true;encrypt=false";

            // Inițializare conexiune
            this.connection = DriverManager.getConnection(url);
            System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database");
        }
    }

    // Metodă pentru a obține instanța unică
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Obține conexiunea
    public Connection getConnection() {
        return connection;
    }
}
