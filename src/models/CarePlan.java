package models;

import java.io.Serializable;

public class CarePlan implements Serializable {
    private int id;
    private String feedingPlan;
    private String medicalCare;

    public CarePlan(int id, String feedingPlan, String medicalCare) {
        this.id = id;
        this.feedingPlan = feedingPlan;
        this.medicalCare = medicalCare;
    }

    @Override
    public String toString() {
        return "Feeding Plan: " + feedingPlan + ", Medical Care: " + medicalCare;
    }
}