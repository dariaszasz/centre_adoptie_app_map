package models;

import java.io.Serializable;

/**
 * This class represents a care plan for an animal.
 * It contains information related to the feeding plan and medical care required by the animal.
 * This class implements Serializable to allow instances to be saved or transmitted.
 */
public class CarePlan implements Serializable {
    private int id;
    private String feedingPlan;
    private String medicalCare;

    /**
     * Constructor for creating a CarePlan instance with a specified ID, feeding plan, and medical care.
     *
     * @param id the ID of the care plan
     * @param feedingPlan the feeding plan for the animal
     * @param medicalCare the medical care information for the animal
     */
    public CarePlan(int id, String feedingPlan, String medicalCare) {
        this.id = id;
        this.feedingPlan = feedingPlan;
        this.medicalCare = medicalCare;
    }

    /**
     * Returns a string representation of the care plan, including feeding plan and medical care.
     *
     * @return a string representation of the care plan
     */
    @Override
    public String toString() {
        return "Feeding Plan: " + feedingPlan + ", Medical Care: " + medicalCare;
    }
}
