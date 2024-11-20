package controller;

import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;
import service.AdoptantService;
import service.AnimalService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for managing Adoptants and their adoption requests.
 * It handles actions like adding, viewing, updating, and deleting adoptants,
 * as well as managing adoption requests.
 */
public class AdoptantController {
    private AdoptantService adoptantService;
    private AnimalService animalService;
    private Scanner scanner;

    /**
     * Constructor that initializes the controller with necessary services.
     *
     * @param adoptantService The service responsible for adoptant operations.
     * @param animalService   The service responsible for animal operations.
     */
    public AdoptantController(AdoptantService adoptantService, AnimalService animalService) {
        this.adoptantService = adoptantService;
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds a new adoptant by capturing their name and contact details.
     */
    public void addAdoptant() {
        System.out.print("Enter adoptant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter adoptant contact details: ");
        String contactDetails = scanner.nextLine();

        Adoptant newAdoptant = new Adoptant(
                adoptantService.generateUniqueId(), // Generate unique ID
                name,
                contactDetails
        );

        adoptantService.addAdoptant(newAdoptant); // Add adoptant
        System.out.println("Adoptant added successfully!");
    }

    /**
     * Displays a list of all adoptants.
     */
    public void viewAllAdoptants() {
        List<Adoptant> adoptants = adoptantService.getAllAdoptants();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            adoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    /**
     * Views an adoptant based on their ID.
     */
    public void viewAdoptantById() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        if (adoptant != null) {
            System.out.println(adoptant);
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    /**
     * Updates an adoptant's details such as their name and contact details.
     */
    public void updateAdoptant() {
        System.out.print("Enter adoptant ID to update: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

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

    /**
     * Deletes an adoptant by their ID.
     */
    public void deleteAdoptant() {
        System.out.print("Enter adoptant ID to delete: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        adoptantService.deleteAdoptant(adoptantId);
        System.out.println("Adoptant deleted successfully!");
    }

    /**
     * Views all adoption requests for a specific adoptant by their ID.
     */
    public void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

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

    /**
     * Makes an adoption request for a specific animal by an adoptant.
     */
    public void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        System.out.print("Enter animal ID: ");
        int animalId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);

        if (adoptant != null && animal != null) {
            adoptantService.makeAdoptionRequest(adoptant, animal);
        } else {
            System.out.println("Invalid adoptant or animal ID.");
        }
    }

    /**
     * Views adoptants who have made a certain minimum number of adoption requests.
     */
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

    /**
     * Sorts and views adoptants based on the number of adoption requests.
     */
    public void sortAdoptantsByAdoptionRequests() {
        List<Adoptant> sortedAdoptants = adoptantService.sortAdoptantsByAdoptionRequests();
        if (sortedAdoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            sortedAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    /**
     * Views adoptants sorted by the total number of adoptions they have made.
     */
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
            System.out.println("Adoptant not found.");
        }
    }

    /**
     * Makes an adoption request for a specific animal by an adoptant, identified by IDs.
     */
    public void makeAdoptionRequest(int adoptantId, int animalId) {
        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);

        if (adoptant != null && animal != null) {
            AdoptionRequest adoptionRequest = new AdoptionRequest(0, adoptant, animal, new Date(), "Pending");
            adoptantService.addAdoptionRequest(adoptionRequest);
            System.out.println("Adoption request made successfully!");
        } else {
            System.out.println("Invalid adoptant ID or animal ID.");
        }
    }

    /**
     * Views all adoption requests for a specific adoptant by their ID.
     */
    public void viewAdoptionRequests(int adoptantId) {
        List<AdoptionRequest> adoptionRequests = adoptantService.getAdoptionRequestsForAdoptant(adoptantId);
        if (adoptionRequests != null && !adoptionRequests.isEmpty()) {
            adoptionRequests.forEach(request -> System.out.println(request));
        } else {
            System.out.println("No adoption requests found for this adoptant.");
        }
    }

    /**
     * Adds a new adoption request.
     */
    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptantService.addAdoptionRequest(adoptionRequest);
        System.out.println("Adoption request added successfully.");
    }
}
