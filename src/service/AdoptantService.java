package service;

import exceptions.BusinessLogicException;
import models.Adoptant;
import models.AdoptionRequest;
import models.Animal;
import repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

public class AdoptantService {
    private IRepository<Adoptant> adoptantRepository;
    private IRepository<AdoptionRequest> adoptionRequestRepository;

    public AdoptantService(IRepository<Adoptant> adoptantRepository, IRepository<AdoptionRequest> adoptionRequestRepository) {
        this.adoptantRepository = adoptantRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    public void addAdoptant(Adoptant adoptant) {
        if (adoptant == null) {
            throw new BusinessLogicException("Cannot add a null adoptant.");
        }
        adoptant.setId(adoptantRepository.generateUniqueId());
        adoptantRepository.add(adoptant);
    }

    public List<Adoptant> getAllAdoptants() {
        return adoptantRepository.getAll();
    }

    public Adoptant getAdoptantById(int id) {
        Adoptant adoptant = adoptantRepository.getById(id);
        if (adoptant == null) {
            throw new BusinessLogicException("Adoptant with ID " + id + " not found.");
        }
        return adoptant;
    }

    public void updateAdoptant(Adoptant adoptant) {
        if (adoptant == null) {
            throw new BusinessLogicException("Cannot update a null adoptant.");
        }
        adoptantRepository.update(adoptant);
    }

    public void deleteAdoptant(int id) {
        if (adoptantRepository.getById(id) == null) {
            throw new BusinessLogicException("Adoptant with ID " + id + " not found.");
        }
        adoptantRepository.delete(id);
    }

    public List<AdoptionRequest> getAdoptionRequestsByAdoptant(Adoptant adoptant) {
        if (adoptant == null) {
            throw new BusinessLogicException("Cannot retrieve adoption requests for a null adoptant.");
        }
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().equals(adoptant))
                .collect(Collectors.toList());
    }

    public void makeAdoptionRequest(Adoptant adoptant, Animal animal) {
        if (adoptant == null || animal == null) {
            throw new BusinessLogicException("Invalid adoptant or animal.");
        }

        int requestId = adoptionRequestRepository.generateUniqueId();
        AdoptionRequest request = new AdoptionRequest(requestId, adoptant, animal, new Date(), "Pending");
        adoptionRequestRepository.add(request);
        System.out.println("Adoption request submitted successfully!");
    }

    public List<Adoptant> filterAdoptantsByAdoptionRequests(int minRequests) {
        return adoptantRepository.getAll().stream()
                .filter(adoptant -> getAdoptionRequestsByAdoptant(adoptant).size() >= minRequests)
                .collect(Collectors.toList());
    }

    public List<Adoptant> sortAdoptantsByAdoptionRequests() {
        return adoptantRepository.getAll().stream()
                .sorted((adoptant1, adoptant2) -> Integer.compare(
                        getAdoptionRequestsByAdoptant(adoptant2).size(),
                        getAdoptionRequestsByAdoptant(adoptant1).size()))
                .collect(Collectors.toList());
    }

    public List<AdoptionRequest> getAdoptionRequestsForAdoptant(int adoptantId) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().getId() == adoptantId)
                .collect(Collectors.toList());
    }

    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        if (adoptionRequest == null) {
            throw new BusinessLogicException("Cannot add a null adoption request.");
        }
        adoptionRequestRepository.add(adoptionRequest);
    }

    public int generateUniqueId() {
        return adoptantRepository.generateUniqueId();
    }

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
