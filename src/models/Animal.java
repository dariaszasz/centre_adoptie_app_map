package models;

import java.io.Serializable;

public class Animal extends BaseEntity implements Serializable {
    private String name;
    private AnimalType animalType;
    private int age;
    private HealthRecord healthRecord;
    private CarePlan carePlan;
    private Volunteer assignedVolunteer;
    private String status;

    public Animal(int id, String name, AnimalType animalType, int age, String status) {
        super(id);  // Folosim id-ul din BaseEntity
        this.name = name;
        this.animalType = animalType;
        this.age = age;
        this.assignedVolunteer = null;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    public CarePlan getCarePlan() {
        return carePlan;
    }

    public void setCarePlan(CarePlan carePlan) {
        this.carePlan = carePlan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Animal ID: " + getId() + ", Name: " + name + ", Type: " + animalType.getTypeName() + ", Age: " + age + ", Status: " + status;
    }

    public Volunteer getAssignedVolunteer() {
        return assignedVolunteer;
    }

    public void setAssignedVolunteer(Volunteer assignedVolunteer) {
        this.assignedVolunteer = assignedVolunteer;
    }
}