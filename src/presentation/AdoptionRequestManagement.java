package presentation;

import controller.AdoptionRequestController;
import models.Adoptant;
import models.AdoptionRequest;

import java.util.List;
import java.util.Scanner;

public class AdoptionRequestManagement {

    private AdoptionRequestController adoptionRequestController;
    private Scanner scanner;

    public AdoptionRequestManagement(AdoptionRequestController adoptionRequestController) {
        this.adoptionRequestController = adoptionRequestController;
        this.scanner = new Scanner(System.in);
    }

    // Display menu to the user
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

    // View all adoption requests
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

    // Approve an adoption request
    private void approveAdoptionRequest() {
        System.out.print("Enter adoption request ID to approve: ");
        int requestId = scanner.nextInt();
        adoptionRequestController.approveAdoptionRequest(requestId); // No need to check return type, as it is void
        System.out.println("Adoption request approved successfully.");
    }

    // Reject an adoption request
    private void rejectAdoptionRequest() {
        System.out.print("Enter adoption request ID to reject: ");
        int requestId = scanner.nextInt();
        adoptionRequestController.rejectAdoptionRequest(requestId); // No need to check return type, as it is void
        System.out.println("Adoption request rejected successfully.");
    }

    // View adoptants with the most adoption requests
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
