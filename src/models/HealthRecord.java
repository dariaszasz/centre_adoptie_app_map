package models;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents the health record of an animal.
 * It contains diagnoses, treatments, and the veterinarian responsible for the care.
 * This class implements Serializable to allow instances to be saved or transmitted.
 */
public class HealthRecord implements Serializable {
    private int id;
    private List<String> diagnoses;
    private List<String> treatments;
    private Veterinarian veterinarian;

    /**
     * Constructor for creating a HealthRecord instance with a specified ID, diagnoses, treatments, and veterinarian.
     *
     * @param id the ID of the health record
     * @param diagnoses a list of diagnoses for the animal
     * @param treatments a list of treatments prescribed for the animal
     * @param veterinarian the veterinarian responsible for the animal's care
     */
    public HealthRecord(int id, List<String> diagnoses, List<String> treatments, Veterinarian veterinarian) {
        this.id = id;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.veterinarian = veterinarian;
    }

    /**
     * Returns a string representation of the health record, including diagnoses, treatments, and the veterinarian.
     *
     * @return a string representation of the health record
     */
    @Override
    public String toString() {
        return "Diagnoses: " + diagnoses + ", Treatments: " + treatments + ", Veterinarian: " + veterinarian.getName();
    }
}
