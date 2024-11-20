package models;

import java.io.Serializable;

/**
 * Class representing an animal in the system.
 * It extends BaseEntity and implements Serializable for data persistence.
 */
public class Animal extends BaseEntity implements Serializable {
    private String name;
    private AnimalType animalType;
    private int age;
    private HealthRecord healthRecord;
    private CarePlan carePlan;
    private String status;

    /**
     * Constructor for creating an animal instance.
     *
     * @param id the ID of the animal
     * @param name the name of the animal
     * @param animalType the type of the animal (e.g., Dog, Cat)
     * @param age the age of the animal
     * @param status the current status of the animal (e.g., Available, Adopted)
     */
    public Animal(int id, String name, AnimalType animalType, int age, String status) {
        super(id);  // Calling the constructor from BaseEntity
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.status = status;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name the name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of the animal (e.g., Dog, Cat).
     *
     * @return the type of the animal
     */
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Sets the type of the animal.
     *
     * @param animalType the type of the animal
     */
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Returns the age of the animal.
     *
     * @return the age of the animal
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the animal.
     *
     * @param age the age of the animal
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the health record of the animal.
     *
     * @return the health record
     */
    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    /**
     * Sets the health record of the animal.
     *
     * @param healthRecord the health record to set
     */
    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    /**
     * Returns the care plan for the animal.
     *
     * @return the care plan
     */
    public CarePlan getCarePlan() {
        return carePlan;
    }

    /**
     * Sets the care plan for the animal.
     *
     * @param carePlan the care plan to set
     */
    public void setCarePlan(CarePlan carePlan) {
        this.carePlan = carePlan;
    }

    /**
     * Returns the current status of the animal (e.g., Available, Adopted).
     *
     * @return the status of the animal
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the animal.
     *
     * @param status the status to set (e.g., Available, Adopted)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the animal, including its ID, name, type, age, and status.
     *
     * @return a string representation of the animal
     */
    @Override
    public String toString() {
        return "Animal ID: " + getId() + ", Name: " + name + ", Type: " + animalType.getTypeName() +
                ", Age: " + age + ", Status: " + status;
    }
}
