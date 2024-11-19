package models;

import java.io.Serializable;
import java.util.Date;

public class AdoptionRequest extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Adoptant adoptant;
    private Animal animal;
    private Date requestDate;
    private String status;

    public AdoptionRequest(int id, Adoptant adoptant, Animal animal, Date requestDate, String status) {
        super(id);  // Apelul constructorului din BaseEntity
        this.adoptant = adoptant;
        this.animal = animal;
        this.requestDate = requestDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Adoptant getAdoptant() {
        return adoptant;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Request ID: " + id + ", Adoptant: " + adoptant.getName() + ", Animal: " + animal.getName() + ", Date: " + requestDate + ", Status: " + status;
    }
}