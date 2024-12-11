package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clasa pentru gestionarea unei conexiuni unice la baza de date.
 * Folosește Singleton pattern pentru a asigura că există doar o singură instanță a conexiunii.
 */
public class DatabaseConnection {

    private static DatabaseConnection instance; // Instanță unică
    private Connection connection;

    /**
     * Constructor privat pentru a inițializa conexiunea la baza de date.
     * Se folosește autentificarea Windows și un URL configurat pentru SQL Server.
     */
    private DatabaseConnection() {
        try {
            // String-ul de conexiune pentru autentificare Windows
            String url = "jdbc:sqlserver://DARIA\\SQLEXPRESS:1433;databaseName=AnimalShelter;integratedSecurity=true;encrypt=false";

            // Inițializare conexiune
            this.connection = DriverManager.getConnection(url);
            System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database", e);
        }
    }

    /**
     * Obține instanța unică a clasei {@link DatabaseConnection}.
     * Dacă instanța nu există, aceasta va fi creată.
     * Este marcată cu {@code synchronized} pentru a asigura că doar un fir de execuție o poate crea.
     *
     * @return Instanța unică a clasei {@link DatabaseConnection}.
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Obține conexiunea la baza de date.
     *
     * @return Conexiunea activă la baza de date.
     */
    public Connection getConnection() {
        return connection;
    }
}
