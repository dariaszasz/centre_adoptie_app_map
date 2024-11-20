package models;

import java.io.Serializable;

/**
 * Represents an animal type (e.g., Dog, Cat) with its associated characteristics.
 * This class implements Serializable for data persistence.
 */
public class AnimalType implements Serializable {
    private int id;
    private String typeName;
    private String specialCharacteristics;

    /**
     * Constructor for creating an instance of AnimalType.
     *
     * @param id the ID of the animal type
     * @param typeName the name of the animal type (e.g., Dog, Cat)
     * @param specialCharacteristics special features or traits associated with this animal type
     */
    public AnimalType(int id, String typeName, String specialCharacteristics) {
        this.id = id;
        this.typeName = typeName;
        this.specialCharacteristics = specialCharacteristics;
    }

    /**
     * Returns the ID of the animal type.
     *
     * @return the ID of the animal type
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the animal type (e.g., Dog, Cat).
     *
     * @return the name of the animal type
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Returns the special characteristics of the animal type.
     *
     * @return the special characteristics of the animal type
     */
    public String getSpecialCharacteristics() {
        return specialCharacteristics;
    }

    /**
     * Returns a string representation of the animal type, including its name and special characteristics.
     *
     * @return a string representation of the animal type
     */
    @Override
    public String toString() {
        return "Type: " + typeName + ", Characteristics: " + specialCharacteristics;
    }
}
