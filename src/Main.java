import controller.AnimalController;
import presentation.ClientMenu;
import presentation.EmployeeMenu;
import repository.InMemoryAnimalRepository;
import service.AnimalService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //name of the file where the animals will be saved
        String fileName = "animale.txt";

        //initialisation of repo, service and controller
        InMemoryAnimalRepository animalRepository = new InMemoryAnimalRepository();
        AnimalService animalService = new AnimalService(animalRepository);
        AnimalController animalController = new AnimalController(animalService);

        Scanner scanner = new Scanner(System.in);

        //repetitive main menu 
        while (true) {
            System.out.println("Select user:");
            System.out.println("1. Client");
            System.out.println("2. Employee");
            System.out.println("0. Exit");

            int userType = scanner.nextInt();

            if (userType == 1) {
                ClientMenu clientMenu = new ClientMenu(animalController);
                clientMenu.showMenu();
            } else if (userType == 2) {
                EmployeeMenu employeeMenu = new EmployeeMenu(animalController);
                employeeMenu.showMenu();
            } else if (userType == 0) {
                System.out.println("Exit app.");
                break;  // stops the loop and closes the app
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
