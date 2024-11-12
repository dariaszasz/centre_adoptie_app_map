package presentation;

import controller.AnimalController;
import models.Tier;
import models.Tierart;
import models.Gesundheitsakte;
import models.Pflegeprogramm;
import models.Tierarzt;
import models.Tierheim;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMenu {
    private AnimalController animalController;
    private Scanner scanner;

    public EmployeeMenu(AnimalController animalController) {
        this.animalController = animalController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Show all animals");
            System.out.println("2. Add a new animal");
            System.out.println("3. Delete an existing animal");
            System.out.println("4. Change the status of an existing animal");
            System.out.println("0. Back to main menu");

            int choice = scanner.nextInt();

            if (choice == 1) {
                List<Tier> animals = animalController.viewAllAnimals();
                System.out.println("All animals: :");
                for (Tier animal : animals) {
                    System.out.println(animal);
                }
            } else if (choice == 2) {
                System.out.println("Enter the details of the animal you want to add: ");

                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Tierart ID: ");
                int tierId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tierart Name: ");
                String tierName = scanner.nextLine();
                System.out.print("Tierart Description: ");
                String tierDescription = scanner.nextLine();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Status: ");
                String status = scanner.nextLine();

                Tierart tierart = new Tierart(tierId, tierName, tierDescription);

                List<String> healthHistory = new ArrayList<>();
                healthHistory.add("Example health records");

                List<String> medications = new ArrayList<>();
                medications.add("Example medications");

                List<Tierheim> shelterList = new ArrayList<>();
                Tierarzt vet = new Tierarzt(1, "Dr. John Doe", "johndoe@yahoo.com", "Surgery", shelterList);

                Gesundheitsakte healthRecord = new Gesundheitsakte(1, healthHistory, medications, vet);

                int idPflegeprogramm = 1;
                String description = "General Pflegeprogramm";
                String carePlan = "Daily care and regular doctor visits";
                Pflegeprogramm careProgram = new Pflegeprogramm(idPflegeprogramm, description, carePlan);

                Tier animal = new Tier(id, name, tierart, age, healthRecord, careProgram, status);
                animalController.addAnimal(animal);
                System.out.println("Animal added successfully!");

                List<Tier> animals = animalController.viewAllAnimals();
                System.out.println("All animals: :");
                for (Tier animalItem : animals) {
                    System.out.println(animalItem);
                }
            } else if (choice == 3) {
                System.out.println("Enter the ID of the animal you want to delete: :");
                int animalId = scanner.nextInt();
                animalController.removeAnimal(animalId);
                System.out.println("Animal removed successfully!.");
            } else if (choice == 4) {
                System.out.println("Enter the Id and the new status of the animal: :");
                int animalId = scanner.nextInt();
                String status = scanner.next();
                animalController.updateAnimalStatus(animalId, status);
                System.out.println("Status updated successfully!.");
            } else if (choice == 0) {
                break;  // Ieși din meniu și revino la meniul principal
            } else {
                System.out.println("Invalid choice!.");
            }
        }
    }
}
