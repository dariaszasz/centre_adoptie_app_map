package service;

import models.Adoptant;
import models.AdoptionRequest;
import models.Animal;
import repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class that handles the business logic related to adoptants and adoption requests.
 * Provides methods for adding, updating, deleting adoptants, managing adoption requests,
 * and filtering and sorting adoptants based on adoption requests.
 */
public class AdoptantService {
    private IRepository<Adoptant> adoptantRepository;
    private IRepository<AdoptionRequest> adoptionRequestRepository;  // Repository for adoption requests

    /**
     * Constructor that initializes the AdoptantService with the provided repositories
     * for adoptants and adoption requests.
     *
     * @param adoptantRepository The repository to handle adoptants data.
     * @param adoptionRequestRepository The repository to handle adoption requests data.
     */
    public AdoptantService(IRepository<Adoptant> adoptantRepository, IRepository<AdoptionRequest> adoptionRequestRepository) {
        this.adoptantRepository = adoptantRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    /**
     * Adds a new adoptant to the system.
     * Assigns a unique ID to the adoptant before adding it to the repository.
     *
     * @param adoptant The adoptant object to be added.
     */
    public void addAdoptant(Adoptant adoptant) {
        adoptant.setId(adoptantRepository.generateUniqueId());
        adoptantRepository.add(adoptant);
    }

    /**
     * Retrieves all adoptants in the system.
     *
     * @return A list of all adoptants.
     */
    public List<Adoptant> getAllAdoptants() {
        return adoptantRepository.getAll();
    }

    /**
     * Retrieves an adoptant by its ID.
     *
     * @param id The ID of the adoptant.
     * @return The adoptant object if found, otherwise null.
     */
    public Adoptant getAdoptantById(int id) {
        return adoptantRepository.getById(id);
    }

    /**
     * Updates the information of an adoptant in the system.
     *
     * @param adoptant The adoptant object with updated information.
     */
    public void updateAdoptant(Adoptant adoptant) {
        adoptantRepository.update(adoptant);
    }

    /**
     * Deletes an adoptant by their ID.
     *
     * @param id The ID of the adoptant to be deleted.
     */
    public void deleteAdoptant(int id) {
        adoptantRepository.delete(id);
    }

    /**
     * Retrieves a list of adoption requests associated with a specific adoptant.
     *
     * @param adoptant The adoptant whose adoption requests are to be retrieved.
     * @return A list of adoption requests made by the specified adoptant.
     */
    public List<AdoptionRequest> getAdoptionRequestsByAdoptant(Adoptant adoptant) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().equals(adoptant))
                .collect(Collectors.toList());
    }

    /**
     * Creates an adoption request for a specific adoptant and animal.
     * The request is added to the adoption request repository.
     *
     * @param adoptant The adoptant making the adoption request.
     * @param animal The animal being requested for adoption.
     */
    public void makeAdoptionRequest(Adoptant adoptant, Animal animal) {
        if (adoptant == null || animal == null) {
            System.out.println("Invalid adoptant or animal.");
            return;
        }

        AdoptionRequest request = new AdoptionRequest(0, adoptant, animal, new Date(), "Pending");
        adoptionRequestRepository.add(request);
        System.out.println("Adoption request submitted successfully!");
    }

    /**
     * Filters adoptants based on the minimum number of adoption requests they have made.
     *
     * @param minRequests The minimum number of adoption requests.
     * @return A list of adoptants who have made at least the specified number of requests.
     */
    public List<Adoptant> filterAdoptantsByAdoptionRequests(int minRequests) {
        return adoptantRepository.getAll().stream()
                .filter(adoptant -> getAdoptionRequestsByAdoptant(adoptant).size() >= minRequests)
                .collect(Collectors.toList());
    }

    /**
     * Sorts adoptants by the number of adoption requests they have made in descending order.
     *
     * @return A list of adoptants sorted by the number of adoption requests.
     */
    public List<Adoptant> sortAdoptantsByAdoptionRequests() {
        return adoptantRepository.getAll().stream()
                .sorted((adoptant1, adoptant2) -> Integer.compare(
                        getAdoptionRequestsByAdoptant(adoptant2).size(),
                        getAdoptionRequestsByAdoptant(adoptant1).size()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of adoption requests for a specific adoptant by their ID.
     *
     * @param adoptantId The ID of the adoptant.
     * @return A list of adoption requests for the specified adoptant.
     */
    public List<AdoptionRequest> getAdoptionRequestsForAdoptant(int adoptantId) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().getId() == adoptantId)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new adoption request to the system.
     *
     * @param adoptionRequest The adoption request to be added.
     */
    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.add(adoptionRequest);
    }

    /**
     * Generates a unique ID for a new adoptant.
     *
     * @return The generated unique ID.
     */
    public int generateUniqueId() {
        return adoptantRepository.generateUniqueId();
    }

    /**
     * Retrieves a list of adoptants sorted by the total number of adoptions they have made.
     * The adoptants are sorted in descending order based on their adoption count.
     *
     * @return A list of adoptants sorted by their total number of adoptions.
     */
    public List<Adoptant> getAdoptantsByTotalAdoptions() {
        Map<Adoptant, Integer> adoptantAdoptionCount = new HashMap<>();

        for (AdoptionRequest request : adoptionRequestRepository.getAll()) {
            Adoptant adoptant = request.getAdoptant();
            adoptantAdoptionCount.put(adoptant, adoptantAdoptionCount.getOrDefault(adoptant, 0) + 1);
        }

        List<Adoptant> sortedAdoptants = new ArrayList<>(adoptantAdoptionCount.keySet());
        sortedAdoptants.sort((adoptant1, adoptant2) -> Integer.compare(
                adoptantAdoptionCount.get(adoptant2),
                adoptantAdoptionCount.get(adoptant1)
        ));

        return sortedAdoptants;
    }
}
