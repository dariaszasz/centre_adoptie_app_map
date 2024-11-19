package service;

import models.Adoptant;
import models.AdoptionRequest;
import models.Animal;
import repository.IRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptantService {
    private IRepository<Adoptant> adoptantRepository;
    private IRepository<AdoptionRequest> adoptionRequestRepository;  // Repository pentru cererile de adopție

    // Constructor care primește repository-ul pentru Adoptant și AdoptionRequest
    public AdoptantService(IRepository<Adoptant> adoptantRepository, IRepository<AdoptionRequest> adoptionRequestRepository) {
        this.adoptantRepository = adoptantRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    // Metodă pentru a adăuga un adoptant
    public void addAdoptant(Adoptant adoptant) {
        adoptantRepository.add(adoptant);
    }

    // Metodă pentru a obține toți adoptanții
    public List<Adoptant> getAllAdoptants() {
        return adoptantRepository.getAll();
    }

    // Metodă pentru a obține un adoptant după ID
    public Adoptant getAdoptantById(int id) {
        return adoptantRepository.getById(id);
    }

    // Metodă pentru a actualiza un adoptant
    public void updateAdoptant(Adoptant adoptant) {
        adoptantRepository.update(adoptant);
    }

    // Metodă pentru a șterge un adoptant
    public void deleteAdoptant(int id) {
        adoptantRepository.delete(id);
    }

    // Metodă pentru a obține cererile de adopție ale unui adoptant
    public List<AdoptionRequest> getAdoptionRequestsByAdoptant(Adoptant adoptant) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().equals(adoptant))
                .collect(Collectors.toList());
    }

    // Metodă pentru a face o cerere de adopție
    public void makeAdoptionRequest(Adoptant adoptant, Animal animal) {
        if (adoptant == null || animal == null) {
            System.out.println("Invalid adoptant or animal.");
            return;
        }

        // Crearea cererii de adopție
        AdoptionRequest request = new AdoptionRequest(0, adoptant, animal, new Date(), "Pending");

        // Adăugarea cererii în repository-ul de cereri de adopție
        adoptionRequestRepository.add(request);

        System.out.println("Adoption request submitted successfully!");
    }

    // Metodă pentru a filtra adoptanții în funcție de numărul cererilor de adopție
    public List<Adoptant> filterAdoptantsByAdoptionRequests(int minRequests) {
        return adoptantRepository.getAll().stream()
                .filter(adoptant -> getAdoptionRequestsByAdoptant(adoptant).size() >= minRequests)
                .collect(Collectors.toList());
    }

    // Metodă pentru a sorta adoptanții în funcție de numărul cererilor de adopție
    public List<Adoptant> sortAdoptantsByAdoptionRequests() {
        return adoptantRepository.getAll().stream()
                .sorted((adoptant1, adoptant2) -> Integer.compare(
                        getAdoptionRequestsByAdoptant(adoptant2).size(),
                        getAdoptionRequestsByAdoptant(adoptant1).size()))
                .collect(Collectors.toList());
    }

    // Metodă pentru a obține cererile de adopție pentru un adoptant
    public List<AdoptionRequest> getAdoptionRequestsForAdoptant(int adoptantId) {
        return adoptionRequestRepository.getAll().stream()
                .filter(request -> request.getAdoptant().getId() == adoptantId)
                .collect(Collectors.toList());
    }

    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptionRequestRepository.add(adoptionRequest);
    }

}
