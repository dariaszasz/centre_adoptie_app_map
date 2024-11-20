import controller.AnimalController;
import controller.AdoptantController;
import controller.AdoptionRequestController;
import controller.VolunteerController;
import service.AnimalService;
import service.AdoptantService;
import service.AdoptionRequestService;
import service.VolunteerService;
import models.Animal;
import models.Adoptant;
import models.AdoptionRequest;
import models.Volunteer;
import presentation.AnimalManagement;
import presentation.AdoptantManagement;
import presentation.AdoptionRequestManagement;
import presentation.VolunteerManagement;
import repository.FileRepository;

import java.util.Scanner;

/**
 * Main class that initializes the application, manages the repositories, services, controllers,
 * and presentation layers. It also handles the main menu and user input for managing animals, adoptants,
 * adoption requests, and volunteers.
 */
public class Main {
    /**
     * The main method that runs the application. It initializes repositories, services, controllers,
     * and presentation layers for managing animals, adoptants, adoption requests, and volunteers.
     * It provides a main menu to the user to navigate between different management options.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize repositories
        FileRepository<Animal> animalRepository = new FileRepository<>("animals.dat");
        FileRepository<Adoptant> adoptantRepository = new FileRepository<>("adoptants.dat");
        FileRepository<AdoptionRequest> adoptionRequestRepository = new FileRepository<>("adoption_requests.dat");
        FileRepository<Volunteer> volunteerRepository = new FileRepository<>("volunteers.dat");  // New repository for volunteers

        // Initialize services
        AnimalService animalService = new AnimalService(animalRepository);
        AdoptantService adoptantService = new AdoptantService(adoptantRepository, adoptionRequestRepository);
        AdoptionRequestService adoptionRequestService = new AdoptionRequestService(adoptionRequestRepository, animalRepository, adoptantRepository);
        VolunteerService volunteerService = new VolunteerService(volunteerRepository, animalRepository);  // New service for volunteers

        // Initialize controllers
        AnimalController animalController = new AnimalController(animalService);
        AdoptantController adoptantController = new AdoptantController(adoptantService, animalService);
        AdoptionRequestController adoptionRequestController = new AdoptionRequestController(adoptionRequestService);
        VolunteerController volunteerController = new VolunteerController(volunteerService);  // New controller for volunteers

        // Initialize presentation layers for Animal, Adoptant, AdoptionRequest, and Volunteer
        AnimalManagement animalManagement = new AnimalManagement(animalController);
        AdoptantManagement adoptantManagement = new AdoptantManagement(adoptantController);
        AdoptionRequestManagement adoptionRequestManagement = new AdoptionRequestManagement(adoptionRequestController);
        VolunteerManagement volunteerManagement = new VolunteerManagement(volunteerController);  // New presentation layer for volunteers

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Animal Management");
            System.out.println("2. Adoptant Management");
            System.out.println("3. Adoption Request Management");
            System.out.println("4. Volunteer Management");  // New menu option
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    animalManagement.displayMenu();
                    break;
                case 2:
                    adoptantManagement.displayMenu();
                    break;
                case 3:
                    adoptionRequestManagement.displayMenu();
                    break;
                case 4:
                    volunteerManagement.displayMenu();  // Handle Volunteer Management
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
