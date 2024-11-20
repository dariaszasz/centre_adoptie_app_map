package models;

import java.io.Serializable;

/**
 * This class represents a person, which is a base class for entities such as Adoptants and Volunteers.
 * It contains common attributes like ID, name, and contact details.
 * This class implements Serializable to allow instances to be saved or transmitted.
 */
public abstract class Person extends BaseEntity implements Serializable {
    private int id;
    private String name;
    private String contactDetails;

    /**
     * Constructor for creating a Person instance with a specified ID, name, and contact details.
     *
     * @param id the ID of the person
     * @param name the name of the person
     * @param contactDetails the contact details of the person
     */
    public Person(int id, String name, String contactDetails) {
        super(id); // Sets the ID from the base class
        this.name = name;
        this.contactDetails = contactDetails;
    }

    /**
     * Returns the ID of the person.
     *
     * @return the ID of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the contact details of the person.
     *
     * @return the contact details of the person
     */
    public String getContactDetails() {
        return contactDetails;
    }

    /**
     * Sets the contact details of the person.
     *
     * @param contactDetails the contact details to set
     */
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     * Returns a string representation of the person, including the ID, name, and contact details.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Contact: " + contactDetails;
    }
}
