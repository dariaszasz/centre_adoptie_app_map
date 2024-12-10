package service;

import exceptions.BusinessLogicException;
import models.AdoptionRequest;
import models.Animal;
import models.Adoptant;
import repository.IRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdoptionRequestService {
    private IRepository<AdoptionRequest> adoptionRequestRepository;
    private IRepository<Animal> animalRepository;
    private IRepository<Adoptant> adoptantRepository;

    public AdoptionRequestService(IRepository<AdoptionRequest> adoptionRequestRepository, IRepository<Animal> animalRepository, IRepository<Adoptant> adoptantRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.animalRepository = animalRepository;
        this.adoptantRepository = adoptantRepository;
    }

    public void addAdoptionRequest(AdoptionRequest request) {
        if (request == null) {
            throw new BusinessLogicException("Adoption request cannot be null.");
        }
        adoptionRequestRepository.add(request);
    }

    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepository.getAll();
    }

    public boolean approveAdoptionRequest(int requestId) {
        AdoptionRequest request = adoptionRequestRepository.getById(requestId);
        if (request == null) {
            throw new BusinessLogicException("Adoption request with ID " + requestId + " not found.");
        }
        if (request.getStatus().equals("Approved") || request.getStatus().equals("Rejected")) {
            throw new BusinessLogicException("Adoption request has already been processed.");
        }

        // Mark the adoption request as approved
        request = new AdoptionRequest(request.getId(), request.getAdoptant(), request.getAnimal(), request.getRequestDate(), "Approved");
        adoptionRequestRepository.update(request);

        // Mark the animal as adopted
        Animal animal = request.getAnimal();
        animal.setStatus("Adopted");
        animalRepository.update(animal);

        System.out.println("Adoption request approved for animal: " + animal.getName());
        return true;
    }

    public boolean rejectAdoptionRequest(int requestId) {
        AdoptionRequest request = adoptionRequestRepository.getById(requestId);
        if (request == null) {
            throw new BusinessLogicException("Adoption request with ID " + requestId + " not found.");
        }
        if (request.getStatus().equals("Approved") || request.getStatus().equals("Rejected")) {
            throw new BusinessLogicException("Adoption request has already been processed.");
        }

        // Mark the adoption request as rejected
        request = new AdoptionRequest(request.getId(), request.getAdoptant(), request.getAnimal(), request.getRequestDate(), "Rejected");
        adoptionRequestRepository.update(request);

        System.out.println("Adoption request rejected for animal: " + request.getAnimal().getName());
        return true;
    }

    public List<Adoptant> getAdoptantsByTotalRequests() {
        List<AdoptionRequest> allRequests = adoptionRequestRepository.getAll();

        Map<Adoptant, Long> adoptantRequestCount = allRequests.stream()
                .collect(Collectors.groupingBy(AdoptionRequest::getAdoptant, Collectors.counting()));

        return adoptantRequestCount.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
