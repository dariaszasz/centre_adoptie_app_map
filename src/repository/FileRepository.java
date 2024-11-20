package repository;

import models.BaseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic repository class for managing entities of type T that extend BaseEntity.
 * It allows performing CRUD operations (Create, Read, Update, Delete) on entities
 * and stores them in a file.
 *
 * @param <T> the type of entity that extends BaseEntity
 */
public class FileRepository<T extends BaseEntity> implements IRepository<T> {
    private String fileName;

    /**
     * Constructor for the FileRepository. Initializes the repository with a file name.
     *
     * @param fileName the name of the file where entities are stored
     */
    public FileRepository(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Adds a new entity to the repository and saves it to the file.
     *
     * @param entity the entity to add
     */
    @Override
    public void add(T entity) {
        List<T> entities = getAll();
        entities.add(entity);
        saveToFile(entities);
    }

    /**
     * Updates an existing entity in the repository.
     * Removes the old entity and adds the updated one.
     *
     * @param entity the entity with updated data
     */
    @Override
    public void update(T entity) {
        List<T> entities = getAll();
        entities.removeIf(e -> ((BaseEntity) e).getId() == ((BaseEntity) entity).getId()); // Remove the old entity by ID
        entities.add(entity); // Add the updated entity
        saveToFile(entities);
    }

    /**
     * Deletes an entity from the repository based on its ID.
     *
     * @param id the ID of the entity to delete
     */
    @Override
    public void delete(int id) {
        List<T> entities = getAll();
        entities.removeIf(entity -> ((BaseEntity) entity).getId() == id); // Remove the entity by ID
        saveToFile(entities);
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the entity with the given ID, or null if not found
     */
    @Override
    public T getById(int id) {
        return getAll().stream()
                .filter(entity -> ((BaseEntity) entity).getId() == id) // Filter by matching ID
                .findFirst() // Get the first matching entity
                .orElse(null); // Return null if no entity is found
    }

    /**
     * Retrieves all entities stored in the file.
     *
     * @return a list of all entities
     */
    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            entities = (List<T>) ois.readObject(); // Deserialize the list of entities
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception when file might be empty or not exist (returns empty list)
        }
        return entities;
    }

    /**
     * Saves the list of entities to the file.
     * Serializes the entities and writes them to the file.
     *
     * @param entities the list of entities to save
     */
    private void saveToFile(List<T> entities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(entities); // Serialize the entities and save them to the file
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception if something goes wrong
        }
    }

    /**
     * Generates a unique ID for new entities by finding the maximum ID from existing entities
     * and incrementing it by 1.
     * If no entities exist, it starts from 1.
     *
     * @return the next available unique ID
     */
    @Override
    public int generateUniqueId() {
        List<T> allEntities = getAll(); // Read all entities from the file
        return allEntities.stream()
                .mapToInt(entity -> entity.getId()) // Get the IDs of all entities
                .max() // Get the maximum ID
                .orElse(0) + 1; // If no entities exist, start from 1
    }
}
