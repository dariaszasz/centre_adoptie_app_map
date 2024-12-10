import repository.DBRepository;
import repository.IRepository;
import models.Animal;
import models.Adoptant;
import models.AdoptionRequest;
import models.Volunteer;
import service.AnimalService;
import service.AdoptantService;
import service.AdoptionRequestService;
import service.VolunteerService;
import controller.AnimalController;
import controller.AdoptantController;
import controller.AdoptionRequestController;
import controller.VolunteerController;
import presentation.AnimalManagement;
import presentation.AdoptantManagement;
import presentation.AdoptionRequestManagement;
import presentation.VolunteerManagement;
import repository.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize connection
        Connection connection = DatabaseConnection.getInstance().getConnection();

        // Initialize repositories
        IRepository<Animal> animalRepository = new DBRepository<>(connection, Animal.class);
        IRepository<Adoptant> adoptantRepository = new DBRepository<>(connection, Adoptant.class);
        IRepository<AdoptionRequest> adoptionRequestRepository = new DBRepository<>(connection, AdoptionRequest.class);
        IRepository<Volunteer> volunteerRepository = new DBRepository<>(connection, Volunteer.class);

        // Initialize services
        AnimalService animalService = new AnimalService(animalRepository);
        AdoptantService adoptantService = new AdoptantService(adoptantRepository, adoptionRequestRepository);
        AdoptionRequestService adoptionRequestService = new AdoptionRequestService(adoptionRequestRepository, animalRepository, adoptantRepository);
        VolunteerService volunteerService = new VolunteerService(volunteerRepository, animalRepository);

        // Initialize controllers
        AnimalController animalController = new AnimalController(animalService);
        AdoptantController adoptantController = new AdoptantController(adoptantService, animalService);
        AdoptionRequestController adoptionRequestController = new AdoptionRequestController(adoptionRequestService);
        VolunteerController volunteerController = new VolunteerController(volunteerService);

        // Initialize management layers
        AnimalManagement animalManagement = new AnimalManagement(animalController);
        AdoptantManagement adoptantManagement = new AdoptantManagement(adoptantController);
        AdoptionRequestManagement adoptionRequestManagement = new AdoptionRequestManagement(adoptionRequestController);
        VolunteerManagement volunteerManagement = new VolunteerManagement(volunteerController);

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Animal Management");
            System.out.println("2. Adoptant Management");
            System.out.println("3. Adoption Request Management");
            System.out.println("4. Volunteer Management");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    volunteerManagement.displayMenu();
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
