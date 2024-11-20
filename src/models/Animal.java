package models;

import java.io.Serializable;

/**
 * Represents an animal entity in the system.
 * This class contains information about the animal's name, type, age, health record,
 * care plan, status, and assigned volunteer.
 * It extends {@link BaseEntity} and implements {@link Serializable} for persistence.
 */
public class Animal extends BaseEntity implements Serializable {
    private String name;
    private AnimalType animalType;
    private int age;
    private HealthRecord healthRecord;
    private CarePlan carePlan;
    private String status;
    private Volunteer assignedVolunteer;

    /**
     * Constructor to initialize an Animal object with the provided details.
     *
     * @param id         The unique identifier for the animal.
     * @param name       The name of the animal.
     * @param animalType The type of the animal (e.g., dog, cat).
     * @param age        The age of the animal.
     * @param status     The current status of the animal (e.g., available, adopted).
     */
    public Animal(int id, String name, AnimalType animalType, int age, String status) {
        super(id);  // Using ID from BaseEntity
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.assignedVolunteer = null;
        this.status = status;
    }

    /**
     * Retrieves the name of the animal.
     *
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name The name of the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the type of the animal.
     *
     * @return The animal's type.
     */
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Sets the type of the animal.
     *
     * @param animalType The type of the animal (e.g., dog, cat).
     */
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Retrieves the age of the animal.
     *
     * @return The age of the animal.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the animal.
     *
     * @param age The age of the animal.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the health record associated with the animal.
     *
     * @return The health record of the animal.
     */
    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    /**
     * Sets the health record for the animal.
     *
     * @param healthRecord The health record of the animal.
     */
    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    /**
     * Retrieves the care plan for the animal.
     *
     * @return The care plan associated with the animal.
     */
    public CarePlan getCarePlan() {
        return carePlan;
    }

    /**
     * Sets the care plan for the animal.
     *
     * @param carePlan The care plan for the animal.
     */
    public void setCarePlan(CarePlan carePlan) {
        this.carePlan = carePlan;
    }

    /**
     * Retrieves the current status of the animal (e.g., available, adopted).
     *
     * @return The status of the animal.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the animal.
     *
     * @param status The status of the animal (e.g., available, adopted).
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Provides a string representation of the animal, including its ID, name, type, age, and status.
     *
     * @return A string representation of the animal.
     */
    @Override
    public String toString() {
        return "Animal ID: " + getId() + ", Name: " + name + ", Type: " + animalType.getTypeName() + ", Age: " + age + ", Status: " + status;
    }

    /**
     * Retrieves the volunteer currently assigned to this animal.
     *
     * @return The assigned volunteer, or null if no volunteer is assigned.
     */
    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }

    /**
     * Sets the volunteer to be assigned to this animal.
     *
     * @param assignedVolunteer The volunteer to assign to the animal.
     */
    public void setAssignedVolunteer(Volunteer assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
    }
}
