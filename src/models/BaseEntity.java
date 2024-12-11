package models;

import java.io.Serializable;
import java.util.Map;

/**
 * This class serves as a base class for entities that require an ID.
 * It implements Serializable to allow instances to be saved or transmitted.
 * Any class that extends this class will inherit the ID field and its methods.
 */
public abstract class BaseEntity implements Serializable {
    private int id;
    private String status;

    /**
     * Constructor for creating an instance of BaseEntity with a specified ID.
     *
     * @param id the ID of the entity
     */
    public BaseEntity(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the entity.
     *
     * @return the ID of the entity
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the entity.
     *
     * @param id the ID to set for the entity
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the table name for the entity.
     * Subclasses must override this method to specify the table name.
     */
    public abstract String getTableName();

    /**
     * Returns a map of column names and their values for this entity.
     * Subclasses must override this method to specify the columns and their values.
     *
     * @return a map of column names and their values
     */
    public abstract Map<String, Object> getColumnValues();
}
