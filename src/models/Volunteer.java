package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.interfaces.IStatusEntity;

/**
 * Represents a volunteer in the system.
 * A volunteer is a person who assists with the care of animals and works with shelters.
 * This class extends {@link Person} and contains additional information about the volunteer's experience,
 * the animals they care for, and the shelters they are associated with.
 */
public class Volunteer extends Person implements IStatusEntity{
    private String experience;
    private List<Animal> animals;
    private List<Shelter> shelters;
    private List<Animal> assignedAnimals;
    private String status;

    /**
     * Constructor to initialize a Volunteer object with the provided details.
     *
     * @param id            The unique identifier for the volunteer.
     * @param name          The name of the volunteer.
     * @param contactDetails The contact details of the volunteer.
     * @param experience    The experience level of the volunteer (e.g., "Beginner", "Expert").
     */
    public Volunteer(int id, String name, String contactDetails, String experience) {
        super(id, name, contactDetails);
        this.animals = new ArrayList<>();
        this.experience = experience;
        this.shelters = new ArrayList<>();
        this.assignedAnimals = new ArrayList<>();
        this.status = status;
    }

    /**
     * Retrieves the experience level of the volunteer.
     *
     * @return The experience level of the volunteer.
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Sets the experience level of the volunteer.
     *
     * @param experience The experience level of the volunteer (e.g., "Beginner", "Expert").
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Retrieves the list of shelters the volunteer is associated with.
     *
     * @return A list of shelters associated with the volunteer.
     */
    public List<Shelter> getShelters() {
        return shelters;
    }

    /**
     * Adds a shelter to the volunteer's list of associated shelters.
     *
     * @param shelter The shelter to add to the list of shelters.
     */
    public void addShelter(Shelter shelter) {
        this.shelters.add(shelter);
    }

    /**
     * Provides a string representation of the volunteer, including their ID, name, experience,
     * and the number of shelters they are associated with.
     *
     * @return A string representation of the volunteer.
     */
    @Override
    public String toString() {
        return super.toString() + ", Experience: " + experience + ", Shelters: " + shelters.size();
    }

    /**
     * Adds an animal to the list of animals the volunteer cares for.
     *
     * @param animal The animal to add to the list of animals the volunteer is responsible for.
     */
    public void addAnimal(Animal animal) {
        this.assignedAnimals.add(animal);
    }

    public List<Animal> getAssignedAnimals() {
        return assignedAnimals;
    }

    /**
     * Implements the getStatus method from the IStatusEntity interface.
     *
     * @return the status of the volunteer
     */
    @Override
    public String getStatus() {
        return status;  // Returnează statusul voluntarului
    }

    /**
     * Sets the status of the volunteer.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Map<String, Object> getColumnValues() {
        // Returnează valorile coloanelor pentru acest voluntar
        Map<String, Object> columnValues = new HashMap<>();
        columnValues.put("id", getId()); // ID-ul voluntarului (din BaseEntity)
        columnValues.put("name", getName());  // Numele voluntarului
        columnValues.put("experience", experience);  // Rolul voluntarului
        columnValues.put("contactDetails", getContactDetails());  // Detaliile de contact
        return columnValues;
    }

    @Override
    public String getTableName() {
        return "volunteer"; // Numele tabelului din baza de date
    }
}
