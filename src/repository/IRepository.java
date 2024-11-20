package repository;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for a generic repository that can store serializable entities.
 * This defines basic operations for adding, updating, deleting, retrieving, and generating a unique ID for entities.
 *
 * @param <T> The type of the entity that extends {@link Serializable}.
 */
public interface IRepository<T extends Serializable> {

    /**
     * Adds an entity to the repository.
     *
     * @param entity The entity to be added. This cannot be null.
     */
    void add(T entity);

    /**
     * Updates an existing entity in the repository.
     * If an entity with the given ID does not exist, no update is performed.
     *
     * @param entity The updated entity that will replace the existing entity.
     *               This cannot be null.
     */
    void update(T entity);

    /**
     * Deletes an entity from the repository based on its ID.
     *
     * @param id The ID of the entity to be deleted.
     *           If no entity with this ID exists, no deletion occurs.
     */
    void delete(int id);

    /**
     * Retrieves an entity from the repository based on its ID.
     *
     * @param id The ID of the desired entity.
     * @return The found entity, or null if no entity with this ID exists.
     */
    T getById(int id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return A list of all entities in the repository. If no entities exist, the list will be empty.
     */
    List<T> getAll();

    /**
     * Generates a unique ID for a new entity.
     * This should return an ID that has not been used by any other entities.
     *
     * @return A unique ID for a new entity.
     */
    int generateUniqueId();
}
