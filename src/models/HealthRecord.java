package models;

import java.io.Serializable;
import java.util.List;

public class HealthRecord implements Serializable {
    private int id;
    private List<String> diagnoses;
    private List<String> treatments;
    private Veterinarian veterinarian;

    public HealthRecord(int id, List<String> diagnoses, List<String> treatments, Veterinarian veterinarian) {
        this.id = id;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.veterinarian = veterinarian;
    }

    @Override
    public String toString() {
        return "Diagnoses: " + diagnoses + ", Treatments: " + treatments + ", Veterinarian: " + veterinarian.getName();
    }
}