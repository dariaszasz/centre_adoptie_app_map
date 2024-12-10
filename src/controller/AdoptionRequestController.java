package controller;

import models.Adoptant;
import models.AdoptionRequest;
import service.AdoptionRequestService;
import exceptions.EntityNotFoundException;

import java.util.List;

public class AdoptionRequestController {
    private AdoptionRequestService adoptionRequestService;

    /**
     * Constructs an AdoptionRequestController with the specified service.
     *
     * @param adoptionRequestService the service for managing adoption requests
     */
    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    /**
     * Adds a new adoption request to the system.
     *
     * @param request the adoption request to be added
     */
    public void addAdoptionRequest(AdoptionRequest request) {
        try {
            adoptionRequestService.addAdoptionRequest(request);
            System.out.println("Adoption request added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding adoption request: " + e.getMessage());
        }
    }

    /**
     * Retrieves all adoption requests from the system.
     *
     * @return a list of all adoption requests
     */
    public List<AdoptionRequest> getAllAdoptionRequests() {
        try {
            List<AdoptionRequest> requests = adoptionRequestService.getAllAdoptionRequests();
            if (requests.isEmpty()) {
                System.out.println("No adoption requests found.");
            }
            return requests;
        } catch (Exception e) {
            System.out.println("Error retrieving adoption requests: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approves an adoption request by its ID.
     *
     * @param requestId the ID of the adoption request to be approved
     */
    public void approveAdoptionRequest(int requestId) {
        try {
            boolean approved = adoptionRequestService.approveAdoptionRequest(requestId);
            if (approved) {
                System.out.println("Adoption request approved.");
            } else {
                throw new EntityNotFoundException("Adoption request with ID " + requestId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error approving adoption request: " + e.getMessage());
        }
    }

    /**
     * Rejects an adoption request by its ID.
     *
     * @param requestId the ID of the adoption request to be rejected
     */
    public void rejectAdoptionRequest(int requestId) {
        try {
            boolean rejected = adoptionRequestService.rejectAdoptionRequest(requestId);
            if (rejected) {
                System.out.println("Adoption request rejected.");
            } else {
                throw new EntityNotFoundException("Adoption request with ID " + requestId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error rejecting adoption request: " + e.getMessage());
        }
    }

    /**
     * Retrieves adoptants sorted by the total number of adoption requests they have made.
     *
     * @return a list of adoptants sorted by the total number of requests
     */
    public List<Adoptant> getAdoptantsByTotalRequests() {
        try {
            List<Adoptant> adoptants = adoptionRequestService.getAdoptantsByTotalRequests();
            if (adoptants.isEmpty()) {
                System.out.println("No adoptants found.");
            }
            return adoptants;
        } catch (Exception e) {
            System.out.println("Error retrieving adoptants: " + e.getMessage());
            return null;
        }
    }
}
