package controller;

import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;
import service.AdoptantService;
import service.AnimalService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdoptantController {
    private AdoptantService adoptantService;
    private AnimalService animalService;
    private Scanner scanner;

    // Constructor care primește serviciile necesare
    public AdoptantController(AdoptantService adoptantService, AnimalService animalService) {
        this.adoptantService = adoptantService;
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }

    // Metodă pentru a adăuga un adoptant
    public void addAdoptant() {
        System.out.print("Enter adoptant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter adoptant contact details: ");
        String contactDetails = scanner.nextLine();

        Adoptant newAdoptant = new Adoptant(
                adoptantService.generateUniqueId(), // Generează ID-ul
                name,
                contactDetails
        );

        adoptantService.addAdoptant(newAdoptant); // Adaugă adoptantul
        System.out.println("Adoptant added successfully!");
    }

    // Metodă pentru a vizualiza toți adoptanții
    public void viewAllAdoptants() {
        List<Adoptant> adoptants = adoptantService.getAllAdoptants();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            adoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    // Metodă pentru a vizualiza un adoptant după ID
    public void viewAdoptantById() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        if (adoptant != null) {
            System.out.println(adoptant);
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    // Metodă pentru a actualiza datele unui adoptant
    public void updateAdoptant() {
        System.out.print("Enter adoptant ID to update: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

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
            System.out.println("Adoptant not found.");
        }
    }

    // Metodă pentru a șterge un adoptant
    public void deleteAdoptant() {
        System.out.print("Enter adoptant ID to delete: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

        adoptantService.deleteAdoptant(adoptantId);
        System.out.println("Adoptant deleted successfully!");
    }

    // Metodă pentru a vizualiza cererile de adopție ale unui adoptant
    public void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        if (adoptant != null) {
            List<AdoptionRequest> requests = adoptantService.getAdoptionRequestsByAdoptant(adoptant);
            if (requests.isEmpty()) {
                System.out.println("No adoption requests found for this adoptant.");
            } else {
                requests.forEach(request -> System.out.println(request));
            }
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    // Metodă pentru a face o cerere de adopție
    public void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        System.out.print("Enter animal ID: ");
        int animalId = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);  // presupunem că animalService există și oferă această metodă

        if (adoptant != null && animal != null) {
            adoptantService.makeAdoptionRequest(adoptant, animal);
        } else {
            System.out.println("Invalid adoptant or animal ID.");
        }
    }

    // Metodă pentru a vizualiza adoptanții cu cereri de adopție
    public void viewAdoptantsWithAdoptionRequests() {
        System.out.print("Enter minimum number of adoption requests: ");
        int minRequests = scanner.nextInt();
        scanner.nextLine();  // Consumă newline-ul rămas

        List<Adoptant> filteredAdoptants = adoptantService.filterAdoptantsByAdoptionRequests(minRequests);
        if (filteredAdoptants.isEmpty()) {
            System.out.println("No adoptants found with the specified number of requests.");
        } else {
            filteredAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    // Metodă pentru a sorta adoptanții după cererile de adopție
    public void sortAdoptantsByAdoptionRequests() {
        List<Adoptant> sortedAdoptants = adoptantService.sortAdoptantsByAdoptionRequests();
        if (sortedAdoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            sortedAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    // Metodă pentru a vizualiza adoptanții sortați după numărul total de cereri de adopție
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

    // Metoda pentru a obține un adoptant după ID
    public Adoptant getAdoptantById(int id) {
        return adoptantService.getAdoptantById(id);
    }

    // Metoda pentru a actualiza un adoptant
    public void updateAdoptant(Adoptant adoptant) {
        adoptantService.updateAdoptant(adoptant);
    }

    // Metoda pentru a șterge un adoptant după ID
    public void deleteAdoptant(int id) {
        adoptantService.deleteAdoptant(id);
    }

    public void viewAdoptantsWithAdoptionRequests(int adoptantId) {
        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId); // Folosește serviciul pentru a obține adoptantul
        if (adoptant != null) {
            System.out.println("Adoptant: " + adoptant.getName());
            // Aici presupunem că metoda de filtrare a cererilor de adopție pentru un adoptant există în AdoptantService
            List<AdoptionRequest> adoptionRequests = adoptantService.getAdoptionRequestsForAdoptant(adoptantId);
            if (adoptionRequests.isEmpty()) {
                System.out.println("No adoption requests for this adoptant.");
            } else {
                adoptionRequests.forEach(request -> System.out.println(request));
            }
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    // Metoda pentru a crea o cerere de adopție
    public void makeAdoptionRequest(int adoptantId, int animalId) {
        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);

        if (adoptant != null && animal != null) {
            AdoptionRequest adoptionRequest = new AdoptionRequest(0, adoptant, animal, new Date(), "Pending");
            adoptantService.addAdoptionRequest(adoptionRequest);  // Presupunând că există această metodă în AdoptantService
            System.out.println("Adoption request made successfully!");
        } else {
            System.out.println("Invalid adoptant ID or animal ID.");
        }
    }

    public void viewAdoptionRequests(int adoptantId) {
        List<AdoptionRequest> adoptionRequests = adoptantService.getAdoptionRequestsForAdoptant(adoptantId);
        if (adoptionRequests != null && !adoptionRequests.isEmpty()) {
            adoptionRequests.forEach(request -> System.out.println(request));
        } else {
            System.out.println("No adoption requests found for this adoptant.");
        }
    }

    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptantService.addAdoptionRequest(adoptionRequest);
        System.out.println("Adoption request added successfully.");
    }
}
