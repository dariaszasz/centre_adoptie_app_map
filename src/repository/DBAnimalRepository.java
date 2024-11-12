package repository;

import models.Tier;
import models.Tierart;
import models.Gesundheitsakte;
import models.Pflegeprogramm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBAnimalRepository implements IAnimalRepository {
    private Connection connection;

    public DBAnimalRepository(String dbUrl, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, user, password);
    }

    @Override
    public List<Tier> getAllAnimals() {
        List<Tier> animals = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM animals");
            while (rs.next()) {
                Tier animal = new Tier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new Tierart(rs.getInt("tierart_id"), rs.getString("tierart_name"), rs.getString("tierart_description")),
                        rs.getInt("alter"),
                        new Gesundheitsakte(rs.getInt("gesundheitsakte_id"), null, null, null),
                        new Pflegeprogramm(rs.getInt("pflegeprogramm_id"), rs.getString("futterplan"), rs.getString("medizinischeVersorgung")),
                        rs.getString("status")
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    @Override
    public void addAnimal(Tier animal) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO animals (id, name, tierart_id, alter, status) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, animal.getId());
            stmt.setString(2, animal.getName());
            stmt.setInt(3, animal.getTierart().getId());
            stmt.setInt(4, animal.getAlter());
            stmt.setString(5, animal.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAnimal(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM animals WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnimalStatus(int id, String status) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE animals SET status = ? WHERE id = ?")) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tier> findById(int id) {
        String sql = "SELECT * FROM Tiere WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Tier animal = new Tier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Tierart(resultSet.getInt("tierart_id"), resultSet.getString("artName"), resultSet.getString("besondereEigenschaften")),
                        resultSet.getInt("alter"),
                        null, // presupunem că inițial nu avem nevoie de obiectul Gesundheitsakte
                        null, // presupunem că inițial nu avem nevoie de obiectul Pflegeprogramm
                        resultSet.getString("status")
                );
                return Optional.of(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Tier getAnimalById(int id) {
        return findById(id).orElse(null); // Delegare către findById
    }
}