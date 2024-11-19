package models;

import java.io.Serializable;

public class AnimalType implements Serializable {
    private int id;
    private String typeName;
    private String specialCharacteristics;

    public AnimalType(int id, String typeName, String specialCharacteristics) {
        this.id = id;
        this.typeName = typeName;
        this.specialCharacteristics = specialCharacteristics;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getSpecialCharacteristics() {
        return specialCharacteristics;
    }

    @Override
    public String toString() {
        return "Type: " + typeName + ", Characteristics: " + specialCharacteristics;
    }
}