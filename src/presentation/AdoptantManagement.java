package presentation;

import controller.AdoptantController;
import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;

import java.util.List;
import java.util.Scanner;

public class AdoptantManagement {
    private AdoptantController adoptantController;
    private Scanner scanner;

    public AdoptantManagement(AdoptantController adoptantController) {
        this.adoptantController = adoptantController;
        this.scanner = new Scanner(System.in);
    }

    // Display menu to the user
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
            System.out.println("8. View Adoptants with Adoption Requests");
            System.out.println("9. Sort Adoptants by Adoption Requests");
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
                    sortAdoptantsByAdoptionRequests();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    // Add a new adoptant
    private void addAdoptant() {
        System.out.print("Enter adoptant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter adoptant contact details: ");
        String contactDetails = scanner.nextLine();

        // Create a new Adoptant object
        Adoptant adoptant = new Adoptant(0, name, contactDetails);
        adoptantController.addAdoptant(adoptant);
        System.out.println("Adoptant added successfully!");
    }


    // View all adoptants
    private void viewAllAdoptants() {
        List<Adoptant> adoptants = adoptantController.getAllAdoptants();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            adoptants.forEach(adoptant -> System.out.println(adoptant));
        }
    }


    // View adoptant by ID
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

    // Update adoptant details
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

    // Delete an adoptant
    private void deleteAdoptant() {
        System.out.print("Enter adoptant ID to delete: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.deleteAdoptant(adoptantId);
        System.out.println("Adoptant deleted successfully!");
    }

    // View all adoption requests for an adoptant
    private void viewAdoptionRequests() {
        System.out.print("Enter adoptant ID to view adoption requests: ");
        int adoptantId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.viewAdoptionRequests(adoptantId);
    }

    // Make an adoption request for an animal
    private void makeAdoptionRequest() {
        System.out.print("Enter adoptant ID: ");
        int adoptantId = scanner.nextInt();

        System.out.print("Enter animal ID to adopt: ");
        int animalId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.makeAdoptionRequest(adoptantId, animalId);
    }

    // View adoptants with a minimum number of adoption requests
    private void viewAdoptantsWithAdoptionRequests() {
        System.out.print("Enter minimum number of adoption requests: ");
        int minRequests = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        adoptantController.viewAdoptantsWithAdoptionRequests(minRequests);
    }

    // Sort adoptants by the number of adoption requests
    private void sortAdoptantsByAdoptionRequests() {
        adoptantController.sortAdoptantsByAdoptionRequests();
    }
}
