package repository;

import models.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementare generică a unui repository pentru gestionarea operațiunilor de bază de date.
 *
 * @param <T> Tipul entității care extinde {@link BaseEntity}.
 */
public class DBRepository<T extends BaseEntity> implements IRepository<T> {

    private final Connection connection;
    private final Class<T> entityType;

    /**
     * Constructorul repository-ului.
     *
     * @param connection Conexiunea la baza de date.
     * @param entityType Tipul entității gestionate de repository.
     */
    public DBRepository(Connection connection, Class<T> entityType) {
        this.connection = connection;
        this.entityType = entityType;
    }

    /**
     * Adaugă o entitate în baza de date.
     *
     * @param entity Entitatea care urmează să fie adăugată.
     */
    @Override
    public void add(T entity) {
        String tableName = entity.getTableName();
        Map<String, Object> columnValues = entity.getColumnValues();

        // Construiește interogarea SQL
        String columns = String.join(", ", columnValues.keySet());
        String placeholders = columnValues.keySet().stream()
                .map(col -> "?")
                .collect(Collectors.joining(", "));
        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object value : columnValues.values()) {
                if (value == null) {
                    statement.setNull(index, java.sql.Types.NULL);
                } else {
                    statement.setObject(index, value);
                }
                index++;
            }

            statement.executeUpdate();
            System.out.println("Entity added to " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add entity to " + tableName, e);
        }
    }

    /**
     * Actualizează o entitate existentă în baza de date.
     *
     * @param entity Entitatea care urmează să fie actualizată.
     */
    @Override
    public void update(T entity) {
        String tableName = entityType.getSimpleName().toLowerCase();
        Field[] fields = entityType.getDeclaredFields();

        StringBuilder setClause = new StringBuilder();
        List<Object> values = new ArrayList<>();
        int id = -1;

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("id")) {
                    id = (int) field.get(entity);
                } else {
                    setClause.append(field.getName()).append(" = ?, ");
                    values.add(field.get(entity));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field value", e);
            }
        }

        if (setClause.length() > 0) {
            setClause.setLength(setClause.length() - 2); // Remove trailing comma
        }

        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, setClause);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            statement.setInt(values.size() + 1, id); // Set 'id' as the last parameter
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update entity", e);
        }
    }

    /**
     * Șterge o entitate din baza de date pe baza ID-ului.
     *
     * @param id ID-ul entității care trebuie ștearsă.
     */
    @Override
    public void delete(int id) {
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete entity", e);
        }
    }

    /**
     * Obține o entitate după ID din baza de date.
     *
     * @param id ID-ul entității care urmează să fie returnată.
     * @return Entitatea corespunzătoare ID-ului sau null dacă nu a fost găsită.
     */
    @Override
    public T getById(int id) {
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                T entity = entityType.getDeclaredConstructor().newInstance();
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    field.set(entity, value);
                }
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve entity", e);
        }

        return null;
    }

    /**
     * Obține toate entitățile din tabela corespunzătoare.
     *
     * @return O listă cu toate entitățile din baza de date.
     */
    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = String.format("SELECT * FROM %s", tableName);

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                T entity = entityType.getDeclaredConstructor().newInstance();
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    field.set(entity, value);
                }
                entities.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve entities", e);
        }

        return entities;
    }

    /**
     * Generează un ID unic pentru o entitate nouă.
     *
     * @return Un ID unic generat pe baza valorii maxime a ID-urilor din tabel.
     */
    @Override
    public int generateUniqueId() {
        String sql = "SELECT ISNULL(MAX(id), 0) + 1 AS nextId FROM adoptant";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("nextId");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to generate unique ID", e);
        }
        throw new RuntimeException("Failed to generate unique ID: No result from database");
    }

    /**
     * Căută entitățile cu un anumit status.
     *
     * @param status Statusul pe care îl caută în baza de date.
     * @return O listă cu entitățile care au statusul dat.
     */
    @Override
    public List<T> findByStatus(String status) {
        List<T> entities = new ArrayList<>();
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = String.format("SELECT * FROM %s WHERE status = ?", tableName);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = entityType.getDeclaredConstructor().newInstance();
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    field.set(entity, value);
                }
                entities.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve entities by status", e);
        }

        return entities;
    }
}
