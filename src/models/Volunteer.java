package models;

import java.util.ArrayList;
import java.util.List;

public class Volunteer extends Person {
    private String experience;
    private List<Shelter> shelters;

    public Volunteer(int id, String name, String contactDetails, String experience) {
        super(id, name, contactDetails);
        this.experience = experience;
        this.shelters = new ArrayList<>();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<Shelter> getShelters() {
        return shelters;
    }

    public void addShelter(Shelter shelter) {
        this.shelters.add(shelter);
    }

    @Override
    public String toString() {
        return super.toString() + ", Experience: " + experience + ", Shelters: " + shelters.size();
    }
}