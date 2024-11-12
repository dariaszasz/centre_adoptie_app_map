import controller.AnimalController;
import presentation.ClientMenu;
import presentation.EmployeeMenu;
import repository.InMemoryAnimalRepository;
import service.AnimalService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Numele fișierului în care vor fi salvate animalele
        String fileName = "animals.txt";

        // Inițializăm repository-ul, serviciul și controller-ul
        InMemoryAnimalRepository animalRepository = new InMemoryAnimalRepository();
        AnimalService animalService = new AnimalService(animalRepository);
        AnimalController animalController = new AnimalController(animalService);

        Scanner scanner = new Scanner(System.in);

        // Meniu principal repetitiv
        while (true) {
            System.out.println("Selectează tipul de utilizator:");
            System.out.println("1. Client");
            System.out.println("2. Angajat");
            System.out.println("0. Ieșire");

            int userType = scanner.nextInt();

            if (userType == 1) {
                ClientMenu clientMenu = new ClientMenu(animalController);
                clientMenu.showMenu();
            } else if (userType == 2) {
                EmployeeMenu employeeMenu = new EmployeeMenu(animalController);
                employeeMenu.showMenu();
            } else if (userType == 0) {
                System.out.println("Ieșire din aplicație.");
                break;  // Oprește bucla și închide aplicația
            } else {
                System.out.println("Opțiune invalidă. Te rog să alegi din nou.");
            }
        }
    }
}