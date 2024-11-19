package models;

import java.util.ArrayList;
import java.util.List;

public class Adoptant extends Person {
    private List<AdoptionRequest> adoptionRequests;

    public Adoptant(int id, String name, String contactDetails) {
        super(id, name, contactDetails);
        this.adoptionRequests = new ArrayList<>();
    }

    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequests;
    }

    public void addAdoptionRequest(AdoptionRequest request) {
        adoptionRequests.add(request);
    }

    @Override
    public String toString() {
        return super.toString() + ", Adoption Requests: " + adoptionRequests.size();
    }
}