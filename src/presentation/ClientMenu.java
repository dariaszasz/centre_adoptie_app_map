package presentation;

import controller.AnimalController;
import models.Tier;

import java.util.List;
import java.util.Scanner;


public class ClientMenu {
    private AnimalController animalController;
    // Obiect pt a citi input-ul de la utilizator
    private Scanner scanner;

    public ClientMenu(AnimalController animalController) {
        this.animalController = animalController;
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        while (true) {
            System.out.println("\nClient Menu:");
            System.out.println("1. Show all animals");
            System.out.println("2. Adopt an animal");
            System.out.println("0. Back to main menu");

            int choice = scanner.nextInt();

            if (choice == 1) {
                List<Tier> animals = animalController.viewAllAnimals();
                System.out.println("Available animals:");
                for (Tier animal : animals) {
                    System.out.println(animal);
                }
            } else if (choice == 2) {
                System.out.println("ID of the animal you want to adopt: ");
                int animalId = scanner.nextInt();
                animalController.adoptAnimal(animalId);
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }


}