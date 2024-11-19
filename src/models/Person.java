package models;

import java.io.Serializable;

public abstract class Person extends BaseEntity implements Serializable {
    private int id;
    private String name;
    private String contactDetails;

    public Person(int id, String name, String contactDetails) {
        super(id); // Setează ID-ul din clasa de bază
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Contact: " + contactDetails;
    }
}