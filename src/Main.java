import controller.AnimalController;
import controller.AdoptantController;
import controller.AdoptionRequestController;
import service.AnimalService;
import service.AdoptantService;
import service.AdoptionRequestService;
import models.Animal;
import models.Adoptant;
import models.AdoptionRequest;
import presentation.AnimalManagement;
import presentation.AdoptantManagement;
import presentation.AdoptionRequestManagement;
import repository.FileRepository;

import java.util.Scanner;

/**
 * The Main class initializes the application by setting up repositories, services, controllers,
 * and the user interface for managing animals, adoptants, and adoption requests.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize repositories using FileRepository to handle persistent storage
        FileRepository<Animal> animalRepository = new FileRepository<>("animals.dat");
        FileRepository<Adoptant> adoptantRepository = new FileRepository<>("adoptants.dat");
        FileRepository<AdoptionRequest> adoptionRequestRepository = new FileRepository<>("adoption_requests.dat");

        // Initialize services with their respective repositories
        AnimalService animalService = new AnimalService(animalRepository);
        AdoptantService adoptantService = new AdoptantService(adoptantRepository, adoptionRequestRepository);
        AdoptionRequestService adoptionRequestService = new AdoptionRequestService(adoptionRequestRepository, animalRepository, adoptantRepository);

        // Initialize controllers with their services
        AnimalController animalController = new AnimalController(animalService);
        AdoptantController adoptantController = new AdoptantController(adoptantService, animalService);
        AdoptionRequestController adoptionRequestController = new AdoptionRequestController(adoptionRequestService);

        // Initialize presentation layers for Animal, Adoptant, and AdoptionRequest
        AnimalManagement animalManagement = new AnimalManagement(animalController);
        AdoptantManagement adoptantManagement = new AdoptantManagement(adoptantController);
        AdoptionRequestManagement adoptionRequestManagement = new AdoptionRequestManagement(adoptionRequestController);

        // Display the main menu to the user
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Animal Management");
            System.out.println("2. Adoptant Management");
            System.out.println("3. Adoption Request Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Handle user input for navigating the menu
            switch (choice) {
                case 1:
                    // Display Animal Management menu
                    animalManagement.displayMenu();
                    break;
                case 2:
                    // Display Adoptant Management menu
                    adoptantManagement.displayMenu();
                    break;
                case 3:
                    // Display Adoption Request Management menu
                    adoptionRequestManagement.displayMenu();
                    break;
                case 4:
                    // Exit the application
                    System.out.println("Exiting the application...");
                    return;
                default:
                    // Handle invalid menu choice
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
