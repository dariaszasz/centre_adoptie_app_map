package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.BaseEntity;

/**
 * An in-memory implementation of the {@link IRepository} interface for managing entities.
 * This class provides basic CRUD (Create, Read, Update, Delete) operations for objects
 * of type {@code T} that extend the {@link BaseEntity} class.
 *
 * @param <T> The type of entity being managed, which must extend {@link BaseEntity}.
 */
public class InMemoryRepository<T extends BaseEntity> implements IRepository<T> {
    private List<T> entities = new ArrayList<>(); // List to store all entities
    private int currentId = 1; // Counter to generate unique IDs for entities

    /**
     * Adds a new entity to the repository.
     * If an entity with the same ID already exists, it will not be added.
     *
     * @param entity The entity to add.
     */
    @Override
    public void add(T entity) {
        boolean exists = entities.stream()
                .anyMatch(e -> e.getId() == entity.getId());

        if (exists) {
            System.out.println("An entity with this ID already exists.");
        } else {
            entities.add(entity);
            System.out.println("Entity added successfully.");
        }
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the given ID, or {@code null} if not found.
     */
    @Override
    public T getById(int id) {
        Optional<T> entity = entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        return entity.orElse(null);
    }

    /**
     * Retrieves all entities in the repository.
     *
     * @return A list of all entities.
     */
    @Override
    public List<T> getAll() {
        return entities;
    }

    /**
     * Updates an existing entity in the repository.
     * If the entity is not found, a message will be displayed.
     *
     * @param entity The updated entity to replace the existing one.
     */
    @Override
    public void update(T entity) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == entity.getId()) {
                entities.set(i, entity);
                System.out.println("Entity updated successfully.");
                return;
            }
        }
        System.out.println("Entity with the given ID not found.");
    }

    /**
     * Deletes an entity from the repository by its ID.
     * If the entity is not found, a message will be displayed.
     *
     * @param id The ID of the entity to delete.
     */
    @Override
    public void delete(int id) {
        boolean removed = entities.removeIf(entity -> entity.getId() == id);
        if (removed) {
            System.out.println("Entity deleted successfully.");
        } else {
            System.out.println("Entity with the given ID not found.");
        }
    }

    /**
     * Generates a unique ID for new entities.
     * This ID is automatically incremented for each call.
     *
     * @return A unique ID.
     */
    public int generateUniqueId() {
        return currentId++;
    }
}
