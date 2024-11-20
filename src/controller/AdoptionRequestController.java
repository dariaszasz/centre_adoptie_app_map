/**
 * Controller class for managing adoption requests.
 * Provides operations for adding, retrieving, approving, rejecting, 
 * and analyzing adoption requests.
 */
package controller;

import models.Adoptant;
import models.AdoptionRequest;
import service.AdoptionRequestService;

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
        adoptionRequestService.addAdoptionRequest(request);
    }

    /**
     * Retrieves all adoption requests from the system.
     *
     * @return a list of all adoption requests
     */
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.getAllAdoptionRequests();
    }

    /**
     * Approves an adoption request by its ID.
     *
     * @param requestId the ID of the adoption request to be approved
     */
    public void approveAdoptionRequest(int requestId) {
        adoptionRequestService.approveAdoptionRequest(requestId);
    }

    /**
     * Rejects an adoption request by its ID.
     *
     * @param requestId the ID of the adoption request to be rejected
     */
    public void rejectAdoptionRequest(int requestId) {
        adoptionRequestService.rejectAdoptionRequest(requestId);
    }

    /**
     * Retrieves adoptants sorted by the total number of adoption requests they have made.
     *
     * @return a list of adoptants sorted by the total number of requests
     */
    public List<Adoptant> getAdoptantsByTotalRequests() {
        return adoptionRequestService.getAdoptantsByTotalRequests();
    }
}
