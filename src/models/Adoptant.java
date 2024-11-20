package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an adopter (Adoptant). It extends the Person class and includes
 * the functionality to manage adoption requests.
 */
public class Adoptant extends Person {
    private static int currentId = 1;
    private List<AdoptionRequest> adoptionRequests;

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
}
