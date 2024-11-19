package controller;

import models.Adoptant;
import models.AdoptionRequest;
import service.AdoptionRequestService;

import java.util.List;

public class AdoptionRequestController {
    private AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    // Method to add a new adoption request
    public void addAdoptionRequest(AdoptionRequest request) {
        adoptionRequestService.addAdoptionRequest(request);
    }

    // Get all adoption requests
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.getAllAdoptionRequests();
    }

    // Approve an adoption request
    public void approveAdoptionRequest(int requestId) {
        adoptionRequestService.approveAdoptionRequest(requestId);
    }

    // Reject an adoption request
    public void rejectAdoptionRequest(int requestId) {
        adoptionRequestService.rejectAdoptionRequest(requestId);
    }

    // Metodă pentru a obține adoptanții cu cele mai multe cereri
    public List<Adoptant> getAdoptantsByTotalRequests() {
        return adoptionRequestService.getAdoptantsByTotalRequests();
    }
}