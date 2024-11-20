package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Veterinarian, which is a person with a specialization
 * in veterinary care. A veterinarian can be associated with multiple shelters.
 * This class extends the Person class.
 */
public class Veterinarian extends Person {
    private String specialization;
    private List<Shelter> shelters;

    /**
     * Constructor for creating a Veterinarian instance with specified ID, name, contact details, and specialization.
     * Initializes an empty list of shelters.
     *
     * @param id the ID of the veterinarian
     * @param name the name of the veterinarian
     * @param contactDetails the contact details of the veterinarian
     * @param specialization the specialization of the veterinarian
     */
    public Veterinarian(int id, String name, String contactDetails, String specialization) {
        super(id, name, contactDetails);
        this.specialization = specialization;
        this.shelters = new ArrayList<>();
    }

    /**
     * Returns the specialization of the veterinarian.
     *
     * @return the specialization of the veterinarian
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the veterinarian.
     *
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns the list of shelters that the veterinarian is associated with.
     *
     * @return a list of shelters
     */
    public List<Shelter> getShelters() {
        return shelters;
    }

    /**
     * Adds a shelter to the list of shelters that the veterinarian is associated with.
     *
     * @param shelter the shelter to add to the list
     */
    public void addShelter(Shelter shelter) {
        this.shelters.add(shelter);
    }

    /**
     * Returns a string representation of the veterinarian, including the inherited information
     * from the Person class (ID, name, contact details) along with the veterinarian's specialization
     * and the number of shelters they are associated with.
     *
     * @return a string representation of the veterinarian
     */
    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization + ", Shelters: " + shelters.size();
    }
}
