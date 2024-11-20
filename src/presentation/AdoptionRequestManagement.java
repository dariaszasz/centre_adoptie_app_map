package presentation;

import controller.AdoptionRequestController;
import models.Adoptant;
import models.AdoptionRequest;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the presentation logic for managing adoption requests.
 * It provides a menu-driven interface to view, approve, or reject adoption requests,
 * as well as view adoptants with the most adoption requests.
 */
public class AdoptionRequestManagement {

    private AdoptionRequestController adoptionRequestController;
    private Scanner scanner;

    /**
     * Constructor that initializes the AdoptionRequestController and Scanner.
     *
     * @param adoptionRequestController the controller used to manage adoption requests
     */
    public AdoptionRequestManagement(AdoptionRequestController adoptionRequestController) {
        this.adoptionRequestController = adoptionRequestController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a menu to the user and handles user input for performing actions on adoption requests.
     * The user can choose to view, approve, or reject adoption requests,
     * and view adoptants with the most requests.
     */
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Adoption Request Management ---");
            System.out.println("1. View All Adoption Requests");
            System.out.println("2. Approve Adoption Request");
            System.out.println("3. Reject Adoption Request");
            System.out.println("4. View Adoptants By Total Requests"); // New option for adoptants with the most requests
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllAdoptionRequests();
                    break;
                case 2:
                    approveAdoptionRequest();
                    break;
                case 3:
                    rejectAdoptionRequest();
                    break;
                case 4:
                    viewAdoptantsByTotalRequests();  // New option to see adoptants with the most requests
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    /**
     * Displays all adoption requests by fetching them from the AdoptionRequestController.
     * If no adoption requests are found, a message is displayed to the user.
     */
    private void viewAllAdoptionRequests() {
        List<AdoptionRequest> requests = adoptionRequestController.getAllAdoptionRequests();
        if (requests.isEmpty()) {
            System.out.println("No adoption requests found.");
        } else {
            System.out.println("\n--- All Adoption Requests ---");
            for (AdoptionRequest request : requests) {
                System.out.println(request);
            }
        }
    }

    /**
     * Approves an adoption request based on the request ID provided by the user.
     * The adoption request is processed by the AdoptionRequestController.
     */
    private void approveAdoptionRequest() {
        System.out.print("Enter adoption request ID to approve: ");
        int requestId = scanner.nextInt();
        adoptionRequestController.approveAdoptionRequest(requestId); // No need to check return type, as it is void
        System.out.println("Adoption request approved successfully.");
    }

    /**
     * Rejects an adoption request based on the request ID provided by the user.
     * The adoption request is processed by the AdoptionRequestController.
     */
    private void rejectAdoptionRequest() {
        System.out.print("Enter adoption request ID to reject: ");
        int requestId = scanner.nextInt();
        adoptionRequestController.rejectAdoptionRequest(requestId); // No need to check return type, as it is void
        System.out.println("Adoption request rejected successfully.");
    }

    /**
     * Displays adoptants who have the most adoption requests.
     * The total number of requests for each adoptant is calculated and displayed.
     */
    private void viewAdoptantsByTotalRequests() {
        List<Adoptant> adoptants = adoptionRequestController.getAdoptantsByTotalRequests();
        if (adoptants.isEmpty()) {
            System.out.println("No adoptants found.");
        } else {
            System.out.println("\n--- Adoptants by Total Adoption Requests ---");
            for (Adoptant adoptant : adoptants) {
                // Calculate the number of adoption requests for each adoptant
                long requestCount = adoptionRequestController.getAllAdoptionRequests().stream()
                        .filter(request -> request.getAdoptant().equals(adoptant))
                        .count();
                System.out.println(adoptant.getName() + " has " + requestCount + " adoption requests.");
            }
        }
    }
}
