package repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic repository implementation for managing database operations.
 *
 * @param <T> The type of the entity that extends {@link Serializable}.
 */
public class DBRepository<T extends Serializable> implements IRepository<T> {

    private final Connection connection;
    private final Class<T> entityType;

    public DBRepository(Connection connection, Class<T> entityType) {
        this.connection = connection;
        this.entityType = entityType;
    }

    @Override
    public void add(T entity) {
        String tableName = entityType.getSimpleName().toLowerCase();
        Field[] fields = entityType.getDeclaredFields();

        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        List<Object> values = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) { // Exclude 'id' field for insert
                field.setAccessible(true);
                columns.append(field.getName()).append(", ");
                placeholders.append("?, ");
                try {
                    values.add(field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field value", e);
                }
            }
        }

        // Remove trailing commas
        if (columns.length() > 0) {
            columns.setLength(columns.length() - 2);
            placeholders.setLength(placeholders.length() - 2);
        }

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                Object value = values.get(i);
                if (value instanceof Integer) {
                    statement.setInt(i + 1, (Integer) value);
                } else if (value instanceof String) {
                    statement.setString(i + 1, (String) value);
                } else if (value instanceof Double) {
                    statement.setDouble(i + 1, (Double) value);
                } else if (value instanceof Date) {
                    statement.setDate(i + 1, (Date) value);
                } else {
                    statement.setObject(i + 1, value); // For other types
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add entity", e);
        }
    }



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

    @Override
    public int generateUniqueId() {
        String tableName = entityType.getSimpleName().toLowerCase();
        String sql = String.format("SELECT MAX(id) FROM %s", tableName);

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to generate unique ID", e);
        }

        return 1; // Return 1 if no entities exist in the table
    }

    @Override
    public List<T> findByStatus(String status) {
        List<T> entities = new ArrayList<>();
        String tableName = entityType.getSimpleName().toLowerCase();  // Obține numele tabelului
        String sql = String.format("SELECT * FROM %s WHERE status = ?", tableName);  // Interogare SQL pentru status

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);  // Setează valoarea parametrului
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = entityType.getDeclaredConstructor().newInstance();  // Crează entitatea
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());  // Obține valoarea din ResultSet
                    field.set(entity, value);  // Setează valoarea în entitate
                }
                entities.add(entity);  // Adaugă entitatea în listă
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve entities by status", e);
        }

        return entities;  // Returnează lista de entități
    }

}
