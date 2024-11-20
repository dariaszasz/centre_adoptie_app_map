package presentation;

import controller.AdoptantController;
import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the presentation logic for managing adoptants.
 * It provides a menu-driven interface to allow users to add, view, update,
 * delete adoptants, and manage adoption requests.
 */
public class AdoptantManagement {
    private AdoptantController adoptantController;
    private Scanner scanner;

    /**
     * Constructor that initializes the AdoptantController and Scanner.
     *
     * @param adoptantController the controller used to manage adoptants
     */
    public AdoptantManagement(AdoptantController adoptantController) {
        this.adoptantController = adoptantController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a menu to the user and handles user input for performing actions on adoptants.
     * The user can choose to add, view, update, delete adoptants, or manage adoption requests.
     */
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Adoptant Management ---");
            System.out.println("1. Add Adoptant");
            System.out.println("2. View All Adoptants");
            System.out.println("3. View Adoptant by ID");
            System.out.println("4. Update Adoptant");
            System.out.println("5. Delete Adoptant");
            System.out.println("6. View Adoption Requests for Adoptant");
            System.out.println("7. Make Adoption Request");
            System.out.println("8. View Adoptants with a minimum of Adoption Requests:");
            System.out.println("9. View Adoptants Sorted by Total Adoptions");
            System.out.println("10. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addAdoptant();
                    break;
                case 2:
                    viewAllAdoptants();
                    break;
                case 3:
                    viewAdoptantById();
                    break;
                case 4:
                    updateAdoptant();
                    break;
                case 5:
                    deleteAdoptant();
                    break;
                case 6:
                    viewAdoptionRequests();
                    break;
                case 7:
                    makeAdoptionRequest();
                    break;
                case 8:
                    viewAdoptantsWithAdoptionRequests();
                    break;
                case 9:
                    viewAdoptantsSortedByTotalAdoptions();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    /**
     * Adds a new adoptant using the AdoptantController.
     */
    private void addAdoptant() {
        adoptantController.addAdoptant();
    }

    /**
     * Displays all adoptants by fetching them from the AdoptantController.
     * If no adoptants are found, a message is shown to the user.
     */
    private void viewAllAdoptants() {
        List<Adoptant> adoptants = adoptantController.getAllAdoptants();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            adoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }

    /**
     * Displays the details of an adoptant by their ID.
     * If the adoptant is not found, a message is shown to the user.
     */
    private void viewAdoptantById() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Adoptant adoptant = adoptantController.getAdoptantById(adoptantId);
        if (adoptant != null) {
            System.out.println(adoptant);
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    /**
     * Allows the user to update an adoptant's details.
     * The user can update the name and contact details of the adoptant.
     */
    private void updateAdoptant() {
        System.out.print("Enter adoptant ID to update: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Adoptant adoptant = adoptantController.getAdoptantById(adoptantId);
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

            adoptantController.updateAdoptant(adoptant);
            System.out.println("Adoptant updated successfully!");
        } else {
            System.out.println("Adoptant not found.");
        }
    }

    /**
     * Deletes an adoptant by their ID using the AdoptantController.
     */
    private void deleteAdoptant() {
        System.out.print("Enter adoptant ID to delete: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.deleteAdoptant(adoptantId);
        System.out.println("Adoptant deleted successfully!");
    }

    /**
     * Displays all adoption requests for a specific adoptant by their ID.
     */
    private void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID to view adoption requests: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.viewAdoptionRequests(adoptantId);
    }

    /**
     * Allows the user to make an adoption request for an animal.
     * The user must provide both the adoptant ID and the animal ID.
     */
    private void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();

        System.out.print("Enter animal ID to adopt: ");
        int animalId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.makeAdoptionRequest(adoptantId, animalId);
    }

    /**
     * Displays adoptants who have made a minimum number of adoption requests.
     */
    private void viewAdoptantsWithAdoptionRequests() {
        System.out.print("Enter minimum number of adoption requests: ");
        int minRequests = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.viewAdoptantsWithAdoptionRequests(minRequests);
    }

    /**
     * Displays adoptants sorted by the total number of adoptions they have made.
     */
    private void viewAdoptantsSortedByTotalAdoptions() {
        adoptantController.viewAdoptantsSortedByTotalAdoptions();
    }
}
