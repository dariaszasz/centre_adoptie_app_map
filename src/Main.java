/*
import controller.AdoptantController;
import controller.AdoptionRequestController;
import controller.AnimalController;
import service.AdoptantService;
import service.AdoptionRequestService;
import models.Adoptant;
import models.Animal;
import models.AdoptionRequest;
import repository.InMemoryRepository;
import presentation.AdoptionRequestManagement;
import presentation.AnimalManagement;
import presentation.AdoptantManagement;
import service.AnimalService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        InMemoryRepository<Animal> animalRepository = new InMemoryRepository<>();
        InMemoryRepository<Adoptant> adoptantRepository = new InMemoryRepository<>();
        InMemoryRepository<AdoptionRequest> adoptionRequestRepository = new InMemoryRepository<>();

        // Initialize services
        AnimalService animalService = new AnimalService(animalRepository);
        AdoptantService adoptantService = new AdoptantService(adoptantRepository, adoptionRequestRepository);
        AdoptionRequestService adoptionRequestService = new AdoptionRequestService(adoptionRequestRepository, animalRepository, adoptantRepository); // Serviciu pentru AdoptionRequest

        // Initialize controllers
        AnimalController animalController = new AnimalController(animalService);
        AdoptantController adoptantController = new AdoptantController(adoptantService, animalService);
        AdoptionRequestController adoptionRequestController = new AdoptionRequestController(adoptionRequestService);  // Controller pentru AdoptionRequest

        // Initialize presentation layers for Animal, Adoptant, and AdoptionRequest
        AnimalManagement animalManagement = new AnimalManagement(animalController);
        AdoptantManagement adoptantManagement = new AdoptantManagement(adoptantController);
        AdoptionRequestManagement adoptionRequestManagement = new AdoptionRequestManagement(adoptionRequestController);  // Pasăm AdoptionRequestController

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
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}

 */
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

public class Main {
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

