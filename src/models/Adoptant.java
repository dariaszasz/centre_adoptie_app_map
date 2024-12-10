package models;

import models.interfaces.IStatusEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an adopter (Adoptant). It extends the Person class and includes
 * the functionality to manage adoption requests.
 */
public class Adoptant extends Person implements IStatusEntity {
    private static int currentId = 1;
    private List<AdoptionRequest> adoptionRequests;
    private String status;

    /**
     * Constructor for creating an adoptant (adopter).
     *
     * @param id the ID of the adoptant (auto-generated)
     * @param name the name of the adoptant
     * @param contactDetails the contact details of the adoptant
     */
    public Adoptant(int id, String name, String contactDetails) {
        super(currentId++, name, contactDetails);
        this.adoptionRequests = new ArrayList<>();
        this.status = status;
    }

    /**
     * Returns the list of adoption requests made by this adoptant.
     *
     * @return the list of adoption requests
     */
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequests;
    }

    /**
     * Adds a new adoption request to the adoptant's list of requests.
     *
     * @param request the adoption request to add
     */
    public void addAdoptionRequest(AdoptionRequest request) {
        adoptionRequests.add(request);
    }

    /**
     * Overrides the toString method to provide a string representation of the adoptant,
     * including their name, contact details, and the number of adoption requests.
     *
     * @return a string representing the adoptant
     */
    @Override
    public String toString() {
        return super.toString() + ", Adoption Requests: " + adoptionRequests.size();
    }

    /**
     * Implements the getStatus method from the IStatusEntity interface.
     *
     * @return the status of the adoptant
     */
    @Override
    public String getStatus() {
        return status;  // Returnează statusul adoptantului
    }

    /**
     * Sets the status of the adoptant.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
