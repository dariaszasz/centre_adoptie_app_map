package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Volunteer, who is a person with some experience in animal care.
 * A volunteer can be associated with multiple shelters.
 * This class extends the Person class.
 */
public class Volunteer extends Person {
    private String experience;
    private List<Shelter> shelters;

    /**
     * Constructor for creating a Volunteer instance with specified ID, name, contact details, and experience.
     * Initializes an empty list of shelters.
     *
     * @param id the ID of the volunteer
     * @param name the name of the volunteer
     * @param contactDetails the contact details of the volunteer
     * @param experience the experience level or description of the volunteer
     */
    public Volunteer(int id, String name, String contactDetails, String experience) {
        super(id, name, contactDetails);
        this.experience = experience;
        this.shelters = new ArrayList<>();
    }

    /**
     * Returns the experience level or description of the volunteer.
     *
     * @return the experience of the volunteer
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Sets the experience level or description of the volunteer.
     *
     * @param experience the experience to set
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Returns the list of shelters that the volunteer is associated with.
     *
     * @return a list of shelters
     */
    public List<Shelter> getShelters() {
        return shelters;
    }

    /**
     * Adds a shelter to the list of shelters that the volunteer is associated with.
     *
     * @param shelter the shelter to add to the list
     */
    public void addShelter(Shelter shelter) {
        this.shelters.add(shelter);
    }

    /**
     * Returns a string representation of the volunteer, including the inherited information
     * from the Person class (ID, name, contact details) along with the volunteer's experience
     * and the number of shelters they are associated with.
     *
     * @return a string representation of the volunteer
     */
    @Override
    public String toString() {
        return super.toString() + ", Experience: " + experience + ", Shelters: " + shelters.size();
    }
}
