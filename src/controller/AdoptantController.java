package controller; /**
 * Controller class for managing adoptants and adoption requests.
 * Provides methods to add, update, delete, view, sort, and filter adoptants and their associated adoption requests.
 */
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

    /**
     * Constructs an AdoptantController with the specified services.
     *
     * @param adoptantService the service for managing adoptants
     * @param animalService   the service for managing animals
     */
    public AdoptantController(AdoptantService adoptantService, AnimalService animalService) {
        this.adoptantService = adoptantService;
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds a new adoptant to the system.
     */
    public void addAdoptant() {
        System.out.print("Enter adoptant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter adoptant contact details: ");
        String contactDetails = scanner.nextLine();

        Adoptant newAdoptant = new Adoptant(
                adoptantService.generateUniqueId(), // Generate a unique ID
                name,
                contactDetails
        );

        adoptantService.addAdoptant(newAdoptant);
        System.out.println("Adoptant added successfully!");
    }

    /**
     * Displays all adoptants in the system.
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
     * Displays an adoptant by their ID.
     */
    public void viewAdoptantById() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine(); // Consume remaining newline

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        if (adoptant != null) {
            System.out.println(adoptant);
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    /**
     * Updates the details of an adoptant.
     */
    public void updateAdoptant() {
        System.out.print("Enter adoptant ID to update: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine(); // Consume remaining newline

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
        scanner.nextLine(); // Consume remaining newline

        adoptantService.deleteAdoptant(adoptantId);
        System.out.println("Adoptant deleted successfully!");
    }

    /**
     * Displays the adoption requests associated with a specific adoptant.
     */
    public void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine(); // Consume remaining newline

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
     * Creates a new adoption request for an adoptant and an animal.
     */
    public void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        System.out.print("Enter animal ID: ");
        int animalId = scanner.nextInt();
        scanner.nextLine(); // Consume remaining newline

        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);

        if (adoptant != null && animal != null) {
            AdoptionRequest adoptionRequest = new AdoptionRequest(
                    0,
                    adoptant,
                    animal,
                    new Date(),
                    "Pending"
            );
            adoptantService.addAdoptionRequest(adoptionRequest);
            System.out.println("Adoption request made successfully!");
        } else {
            System.out.println("Invalid adoptant ID or animal ID.");
        }
    }

    /**
     * Displays adoptants who have a specified minimum number of adoption requests.
     */
    public void viewAdoptantsWithAdoptionRequests() {
        System.out.print("Enter minimum number of adoption requests: ");
        int minRequests = scanner.nextInt();
        scanner.nextLine(); // Consume remaining newline

        List<Adoptant> filteredAdoptants = adoptantService.filterAdoptantsByAdoptionRequests(minRequests);
        if (filteredAdoptants.isEmpty()) {
            System.out.println("No adoptants found with the specified number of requests.");
        } else {
            filteredAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    /**
     * Sorts adoptants by their number of adoption requests.
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
     * Displays adoptants sorted by their total adoptions.
     */
    public void viewAdoptantsSortedByTotalAdoptions() {
        List<Adoptant> sortedAdoptants = adoptantService.getAdoptantsByTotalAdoptions();
        if (sortedAdoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            sortedAdoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    /**
     * Retrieves a list of all adoptants in the system.
     *
     * @return a list of all adoptants
     */
    public List<Adoptant> getAllAdoptants() {
        return adoptantService.getAllAdoptants();
    }

    /**
     * Retrieves an adoptant by their unique ID.
     *
     * @param id the ID of the adoptant to retrieve
     * @return the adoptant with the specified ID, or null if not found
     */
    public Adoptant getAdoptantById(int id) {
        return adoptantService.getAdoptantById(id);
    }

    /**
     * Updates the details of an existing adoptant.
     *
     * @param adoptant the adoptant object containing updated information
     */
    public void updateAdoptant(Adoptant adoptant) {
        adoptantService.updateAdoptant(adoptant);
    }

    /**
     * Deletes an adoptant from the system by their unique ID.
     *
     * @param id the ID of the adoptant to delete
     */
    public void deleteAdoptant(int id) {
        adoptantService.deleteAdoptant(id);
    }

    /**
     * Displays the adoption requests associated with a specific adoptant.
     *
     * @param adoptantId the ID of the adoptant whose adoption requests will be displayed
     */
    public void viewAdoptantsWithAdoptionRequests(int adoptantId) {
        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId); // Uses the service to retrieve the adoptant
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
     * Creates a new adoption request for a specific adoptant and animal.
     *
     * @param adoptantId the ID of the adoptant making the adoption request
     * @param animalId   the ID of the animal being requested for adoption
     */
    public void makeAdoptionRequest(int adoptantId, int animalId) {
        Adoptant adoptant = adoptantService.getAdoptantById(adoptantId);
        Animal animal = animalService.getAnimalById(animalId);

        if (adoptant != null && animal != null) {
            AdoptionRequest adoptionRequest = new AdoptionRequest(0, adoptant, animal, new Date(), "Pending");
            adoptantService.addAdoptionRequest(adoptionRequest);  // Assuming this method exists in AdoptantService
            System.out.println("Adoption request made successfully!");
        } else {
            System.out.println("Invalid adoptant ID or animal ID.");
        }
    }

    /**
     * Displays all adoption requests associated with a specific adoptant.
     *
     * @param adoptantId the ID of the adoptant whose adoption requests will be displayed
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
     * Adds a new adoption request to the system.
     *
     * @param adoptionRequest the adoption request to add
     */
    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {
        adoptantService.addAdoptionRequest(adoptionRequest);
        System.out.println("Adoption request added successfully.");
    }

}
