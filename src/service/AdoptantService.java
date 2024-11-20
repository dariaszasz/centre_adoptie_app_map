package service;

import models.Adoptant;
import models.AdoptionRequest;
import models.Animal;
import repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling operations related to adoptants (adoption candidates)
 * and adoption requests. It interacts with repositories to add, update, delete, and retrieve data
 * related to adoptants and adoption requests.
 */
public class AdoptantService {
    private IRepository<Adoptant> adoptantRepository;
    private IRepository<AdoptionRequest> adoptionRequestRepository;  // Repository for adoption requests

    /**
     * Constructor for the AdoptantService.
     *
     * @param adoptantRepository The repository for the adoptants.
     * @param adoptionRequestRepository The repository for the adoption requests.
     */
    public AdoptantService(IRepository<Adoptant> adoptantRepository, IRepository<AdoptionRequest> adoptionRequestRepository) {
        this.adoptantRepository = adoptantRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    /**
     * Adds a new adoptant to the repository.
     * Generates a unique ID for the adoptant before adding.
     *
     * @param adoptant The adoptant to be added.
     */
    public void addAdoptant(Adoptant adoptant) {
        adoptant.setId(adoptantRepository.generateUniqueId());
        adoptantRepository.add(adoptant);
    }

    /**
     * Retrieves all adoptants.
     *
     * @return A list of all adoptants in the repository.
     */
    public List<Adoptant> getAllAdoptants() {
        return adoptantRepository.getAll();
    }

    /**
     * Retrieves an adoptant by their ID.
     *
     * @param id The ID of the adoptant to retrieve.
     * @return The adoptant with the specified ID, or null if not found.
     */
    public Adoptant getAdoptantById(int id) {
        return adoptantRepository.getById(id);
    }

    /**
     * Updates an adoptant's information in the repository.
     *
     * @param adoptant The adoptant with updated information.
     */
    public void updateAdoptant(Adoptant adoptant) {
        adoptantRepository.update(adoptant);
    }

    /**
     * Deletes an adoptant by their ID.
     *
     * @param id The ID of the adoptant to delete.
     */
    public void deleteAdoptant(int id) {
        adoptantRepository.delete(id);
    }

    /**
     * Retrieves all adoption requests for a specific adoptant.
     *
     * @param adoptant The adoptant whose requests are to be retrieved.
     * @return A list of adoption requests associated with the given adoptant.
     */
    public List<AdoptionRequest> getAdoptionRequestsByAdoptant(Adoptant adoptant) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().equals(adoptant))
                .collect(Collectors.toList());
    }

    /**
     * Makes an adoption request for a specific animal by an adoptant.
     * The request is added to the adoption request repository with a "Pending" status.
     *
     * @param adoptant The adoptant making the request.
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
     * Filters adoptants based on the minimum number of adoption requests they have.
     *
     * @param minRequests The minimum number of requests a adoptant must have.
     * @return A list of adoptants with at least the specified number of adoption requests.
     */
    public List<Adoptant> filterAdoptantsByAdoptionRequests(int minRequests) {
        return adoptantRepository.getAll().stream()
                .filter(adoptant -> getAdoptionRequestsByAdoptant(adoptant).size() >= minRequests)
                .collect(Collectors.toList());
    }

    /**
     * Sorts adoptants by the number of adoption requests they have, in descending order.
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
     * Retrieves all adoption requests for a specific adoptant based on their ID.
     *
     * @param adoptantId The ID of the adoptant whose requests are to be retrieved.
     * @return A list of adoption requests associated with the given adoptant.
     */
    public List<AdoptionRequest> getAdoptionRequestsForAdoptant(int adoptantId) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().getId() == adoptantId)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new adoption request to the repository.
     *
     * @param adoptionRequest The adoption request to be added.
     */
    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.add(adoptionRequest);
    }

    /**
     * Generates a unique ID for a new adoptant.
     * This is done by generating a unique ID using the adoptant repository.
     *
     * @return A unique ID for a new adoptant.
     */
    public int generateUniqueId() {
        return adoptantRepository.generateUniqueId(); // adoptantRepository is an instance of FileRepository
    }

    /**
     * Retrieves a sorted list of adoptants based on the total number of adoptions they have made.
     *
     * @return A list of adoptants sorted in descending order by the total number of adoptions.
     */
    public List<Adoptant> getAdoptantsByTotalAdoptions() {
        Map<Adoptant, Integer> adoptantAdoptionCount = new HashMap<>();

        for (AdoptionRequest request : adoptionRequestRepository.getAll()) {
            Adoptant adoptant = request.getAdoptant();
            adoptantAdoptionCount.put(adoptant, adoptantAdoptionCount.getOrDefault(adoptant, 0) + 1);
        }

        List<Adoptant> sortedAdoptants = new ArrayList<>(adoptantAdoptionCount.keySet());
        sortedAdoptants.sort((adoptant1, adoptant2) -> Integer.compare(
                adoptantAdoptionCount.get(adoptant2), adoptantAdoptionCount.get(adoptant1)
        ));

        return sortedAdoptants;
    }
}
