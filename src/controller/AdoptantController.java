package controller;

import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;
import service.AdoptantService;
import service.AnimalService;
import exceptions.EntityNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdoptantController {
    private AdoptantService adoptantService;
    private AnimalService animalService;
    private Scanner scanner;

    public AdoptantController(AdoptantService adoptantService, AnimalService animalService) {
        this.adoptantService = adoptantService;
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }

    public void addAdoptant(String name, String contactDetails) {
        Adoptant newAdoptant = new Adoptant(
                adoptantService.generateUniqueId(),
                name,
                contactDetails
        );

        adoptantService.addAdoptant(newAdoptant);
        System.out.println("Adoptant added successfully!");
    }

    public void viewAllAdoptants() {
        List<Adoptant> adoptants = adoptantService.getAllAdoptants();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            adoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    public void viewAdoptantById() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            if (adoptant != null) {
                System.out.println(adoptant);
            } else {
                throw new EntityNotFoundException("Adoptant with ID " + adoptantId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAdoptant() {
        System.out.print("Enter adoptant ID to update: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            if (adoptant != null) {
                System.out.print("Enter new name (leave empty to keep current): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    adoptant.setName(name);
                }

                System.out.print("Enter new contact details (leave empty to keep current): ");
                String contactDetails = scanner.nextLine();
                if (!contactDetails.isEmpty()) {
                    adoptant.setContactDetails(contactDetails);
                }

                adoptantService.updateAdoptant(adoptant);
                System.out.println("Adoptant updated successfully!");
            } else {
                throw new EntityNotFoundException("Adoptant with ID " + adoptantId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAdoptant() {
        System.out.print("Enter adoptant ID to delete: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            adoptantService.deleteAdoptant(adoptantId);
            System.out.println("Adoptant deleted successfully!");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            if (adoptant != null) {
                List<AdoptionRequest> requests = adoptantService.getAdoptionRequestsByAdoptant(adoptant);
                if (requests.isEmpty()) {
                    System.out.println("No adoption requests found for this adoptant.");
                } else {
                    requests.forEach(request -> System.out.println(request));
                }
            } else {
                throw new EntityNotFoundException("Adoptant with ID " + adoptantId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        System.out.print("Enter animal ID: ");
        int animalId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            Optional<Animal> animal = animalService.getAnimalById(animalId);

            if (adoptant != null && animal.isPresent()) {
                adoptantService.makeAdoptionRequest(adoptant, animal.orElse(null));
                System.out.println("Adoption request made successfully!");
            } else {
                throw new EntityNotFoundException("Invalid adoptant or animal ID.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAdoptantsWithAdoptionRequests() {
        System.out.print("Enter minimum number of adoption requests: ");
        int minRequests = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        List<Adoptant> filteredAdoptants = adoptantService.filterAdoptantsByAdoptionRequests(minRequests);
        if (filteredAdoptants.isEmpty()) {
            System.out.println("No adoptants found with the specified number of requests.");
        } else {
            filteredAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    public void sortAdoptantsByAdoptionRequests() {
        List<Adoptant> sortedAdoptants = adoptantService.sortAdoptantsByAdoptionRequests();
        if (sortedAdoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            sortedAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    public void viewAdoptantsSortedByTotalAdoptions() {
        List<Adoptant> sortedAdoptants = adoptantService.getAdoptantsByTotalAdoptions();
        if (sortedAdoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            sortedAdoptants.forEach(adoptant -> System.out.println(adoptant + " - Total Adoptions: " +
                    adoptantService.getAdoptionRequestsByAdoptant(adoptant).size()));
        }
    }

    public List<Adoptant> getAllAdoptants() {
        return adoptantService.getAllAdoptants();
    }

    public Adoptant getAdoptantById(int id) {
        return adoptantService.getAdoptantById(id);
    }

    public void updateAdoptant(Adoptant adoptant) {
        adoptantService.updateAdoptant(adoptant);
    }

    public void deleteAdoptant(int id) {
        adoptantService.deleteAdoptant(id);
    }

    public void viewAdoptantsWithAdoptionRequests(int adoptantId) {
        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            if (adoptant != null) {
                System.out.println("Adoptant: " + adoptant.getName());
                List<AdoptionRequest> adoptionRequests = adoptantService.getAdoptionRequestsForAdoptant(adoptantId);
                if (adoptionRequests.isEmpty()) {
                    System.out.println("No adoption requests for this adoptant.");
                } else {
                    adoptionRequests.forEach(request -> System.out.println(request));
                }
            } else {
                throw new EntityNotFoundException("Adoptant with ID " + adoptantId + " not found.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void makeAdoptionRequest(int adoptantId, int animalId) {
        try {
            Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
            Optional<Animal> animal = animalService.getAnimalById(animalId);

            if (adoptant != null && animal.isPresent()) {
                AdoptionRequest adoptionRequest = new AdoptionRequest(0, adoptant, animal.orElse(null), new Date(), "Pending");
                adoptantService.addAdoptionRequest(adoptionRequest);
                System.out.println("Adoption request made successfully!");
            } else {
                throw new EntityNotFoundException("Invalid adoptant ID or animal ID.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAdoptionRequests(int adoptantId) {
        try {
            List<AdoptionRequest> adoptionRequests = adoptantService.getAdoptionRequestsForAdoptant(adoptantId);
            if (adoptionRequests != null && !adoptionRequests.isEmpty()) {
                adoptionRequests.forEach(request -> System.out.println(request));
            } else {
                System.out.println("No adoption requests found for this adoptant.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptantService.addAdoptionRequest(adoptionRequest);
        System.out.println("Adoption request added successfully.");
    }
}
