package models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.interfaces.IStatusEntity;

/**
 * Class representing an adoption request made by an adoptant for an animal.
 * It extends the BaseEntity class and implements Serializable for data persistence.
 */
public class AdoptionRequest extends BaseEntity implements Serializable, IStatusEntity {
    private static final long serialVersionUID = 1L;

    private int id;
    private Adoptant adoptant;
    private Animal animal;
    private Date requestDate;
    private String status;

    /**
     * Constructor for creating an adoption request.
     *
     * @param id the ID of the adoption request
     * @param adoptant the adoptant who made the request
     * @param animal the animal that the adoptant wants to adopt
     * @param requestDate the date when the request was made
     * @param status the current status of the request (e.g., Pending, Approved, Rejected)
     */
    public AdoptionRequest(int id, Adoptant adoptant, Animal animal, Date requestDate, String status) {
        super(id);  // Calling the constructor from BaseEntity
        this.adoptant = adoptant;
        this.animal = animal;
        this.requestDate = requestDate;
        this.status = status;
    }

    /**
     * Returns the ID of the adoption request.
     *
     * @return the ID of the adoption request
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the adoptant who made the adoption request.
     *
     * @return the adoptant
     */
    public Adoptant getAdoptant() {
        return adoptant;
    }

    /**
     * Returns the animal requested for adoption.
     *
     * @return the animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Returns the date when the adoption request was made.
     *
     * @return the request date
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * Returns the status of the adoption request.
     *
     * @return the status of the request (e.g., Pending, Approved, Rejected)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns a string representation of the adoption request, including the request ID,
     * adoptant's name, animal's name, request date, and status.
     *
     * @return a string representation of the adoption request
     */
    @Override
    public String toString() {
        return "Request ID: " + id + ", Adoptant: " + adoptant.getName() + ", Animal: " + animal.getName() +
                ", Date: " + requestDate + ", Status: " + status;
    }

    @Override
    public Map<String, Object> getColumnValues() {
        Map<String, Object> columnValues = new HashMap<>();
        columnValues.put("id", getId()); // ID-ul entității
        columnValues.put("adoptantId", adoptant.getId()); // ID-ul adoptantului
        columnValues.put("animalId", animal.getId()); // ID-ul animalului
        columnValues.put("requestDate", requestDate); // Data cererii
        columnValues.put("status", getStatus()); // Statusul entității (din BaseEntity)
        return columnValues;
    }

    @Override
    public String getTableName() {
        return "adoption_request"; // Numele tabelului din baza de date
    }
}
