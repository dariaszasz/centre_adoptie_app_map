package service;

import models.AdoptionRequest;
import models.Animal;
import models.Adoptant;
import repository.IRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling operations related to adoption requests.
 * It interacts with repositories to add, approve, reject, and retrieve adoption requests.
 */
public class AdoptionRequestService {
    private IRepository<AdoptionRequest> adoptionRequestRepository;
    private IRepository<Animal> animalRepository;
    private IRepository<Adoptant> adoptantRepository;

    /**
     * Constructor for the AdoptionRequestService.
     *
     * @param adoptionRequestRepository The repository for adoption requests.
     * @param animalRepository The repository for animals.
     * @param adoptantRepository The repository for adoptants.
     */
    public AdoptionRequestService(IRepository<AdoptionRequest> adoptionRequestRepository, IRepository<Animal> animalRepository, IRepository<Adoptant> adoptantRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.animalRepository = animalRepository;
        this.adoptantRepository = adoptantRepository;
    }

    /**
     * Adds a new adoption request to the repository.
     *
     * @param request The adoption request to be added.
     */
    public void addAdoptionRequest(AdoptionRequest request) {
        adoptionRequestRepository.add(request);
    }

    /**
     * Retrieves all adoption requests.
     *
     * @return A list of all adoption requests in the repository.
     */
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepository.getAll();
    }

    /**
     * Approves an adoption request.
     * Marks the adoption request as "Approved" and updates the animal's status to "Adopted".
     *
     * @param requestId The ID of the adoption request to approve.
     */
    public void approveAdoptionRequest(int requestId) {
        AdoptionRequest request = adoptionRequestRepository.getById(requestId);
        if (request != null && request.getStatus().equals("Pending")) {
            request = new AdoptionRequest(request.getId(), request.getAdoptant(), request.getAnimal(), request.getRequestDate(), "Approved");
            adoptionRequestRepository.update(request);

            // Mark the animal as adopted
            Animal animal = request.getAnimal();
            animal.setStatus("Adopted");
            animalRepository.update(animal);

            System.out.println("Adoption request approved for animal: " + animal.getName());
        } else {
            System.out.println("Request not found or already processed.");
        }
    }

    /**
     * Rejects an adoption request.
     * Marks the adoption request as "Rejected".
     *
     * @param requestId The ID of the adoption request to reject.
     */
    public void rejectAdoptionRequest(int requestId) {
        AdoptionRequest request = adoptionRequestRepository.getById(requestId);
        if (request != null && request.getStatus().equals("Pending")) {
            request = new AdoptionRequest(request.getId(), request.getAdoptant(), request.getAnimal(), request.getRequestDate(), "Rejected");
            adoptionRequestRepository.update(request);
            System.out.println("Adoption request rejected for animal: " + request.getAnimal().getName());
        } else {
            System.out.println("Request not found or already processed.");
        }
    }

    /**
     * Retrieves a list of adoptants sorted by the total number of adoption requests they have made.
     * The adoptants are sorted in descending order based on the number of requests.
     *
     * @return A list of adoptants sorted by the total number of adoption requests.
     */
    public List<Adoptant> getAdoptantsByTotalRequests() {
        // Get all adoption requests
        List<AdoptionRequest> allRequests = adoptionRequestRepository.getAll();

        // Create a map linking each adoptant to the number of adoption requests
        Map<Adoptant, Long> adoptantRequestCount = allRequests.stream()
                .collect(Collectors.groupingBy(AdoptionRequest::getAdoptant, Collectors.counting()));

        // Sort the adoptants by the number of requests in descending order
        return adoptantRequestCount.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue())) // Sort in descending order
                .map(Map.Entry::getKey)  // Extract only the adoptants from the map
                .collect(Collectors.toList());
    }
}