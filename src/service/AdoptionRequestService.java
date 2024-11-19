package service;

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

    // Method to add a new adoption request
    public void addAdoptionRequest(AdoptionRequest request) {
        adoptionRequestRepository.add(request);
    }

    // Get all adoption requests
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepository.getAll();
    }

    // Approve an adoption request
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

    // Reject an adoption request
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

    // Metoda pentru a obține adoptanții cu cele mai multe cereri de adopție
    public List<Adoptant> getAdoptantsByTotalRequests() {
        // Obținem toate cererile de adopție
        List<AdoptionRequest> allRequests = adoptionRequestRepository.getAll();

        // Creăm un map care leagă fiecare adoptant de numărul de cereri de adopție
        Map<Adoptant, Long> adoptantRequestCount = allRequests.stream()
                .collect(Collectors.groupingBy(AdoptionRequest::getAdoptant, Collectors.counting()));

        // Sortăm lista de adoptanți în funcție de numărul de cereri (descrescător)
        return adoptantRequestCount.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue())) // Sortare descrescătoare
                .map(Map.Entry::getKey)  // Extragem doar adoptanții din map
                .collect(Collectors.toList());
    }
}
