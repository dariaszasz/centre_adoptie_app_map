package models;

import java.util.ArrayList;
import java.util.List;

public class Veterinarian extends Person {
    private String specialization;
    private List<Shelter> shelters;

    public Veterinarian(int id, String name, String contactDetails, String specialization) {
        super(id, name, contactDetails);
        this.specialization = specialization;
        this.shelters = new ArrayList<>();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Shelter> getShelters() {
        return shelters;
    }

    public void addShelter(Shelter shelter) {
        this.shelters.add(shelter);
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization + ", Shelters: " + shelters.size();
    }
}