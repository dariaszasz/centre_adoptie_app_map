package presentation;

import controller.AnimalController;
import models.Animal;
import models.AnimalType;
import exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the presentation logic for managing animals in the system.
 * It provides a menu-driven interface to add, view, update, delete, and sort animals.
 */
public class AnimalManagement {

    private AnimalController animalController;
    private Scanner scanner;

    /**
     * Constructor that initializes the AnimalController and Scanner.
     *
     * @param animalController the controller used to manage animals
     */
    public AnimalManagement(AnimalController animalController) {
        this.animalController = animalController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a menu to the user and handles user input for performing actions on animals.
     * The user can choose to add, view, update, delete, sort, or filter animals.
     */
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Animal Management ---");
            System.out.println("1. Add Animal");
            System.out.println("2. View All Animals");
            System.out.println("3. View Animal by ID");
            System.out.println("4. Update Animal");
            System.out.println("5. Delete Animal");
            System.out.println("6. Sort Animals by Age");
            System.out.println("7. Filter Animals by Status");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    viewAllAnimals();
                    break;
                case 3:
                    viewAnimalById();
                    break;
                case 4:
                    updateAnimal();
                    break;
                case 5:
                    deleteAnimal();
                    break;
                case 6:
                    sortAnimalsByAge();
                    break;
                case 7:
                    filterAnimalsByStatus();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    /**
     * Adds a new animal by taking input from the user.
     * The animal's details, such as ID, name, age, type, and status, are collected,
     * and a new Animal object is created and added to the repository.
     */
    private void addAnimal() {
        try {
            System.out.print("Enter animal ID: ");
            int id = scanner.nextInt();  // User inputs the animal's ID
            scanner.nextLine();  // Consume newline

            if (id <= 0) throw new ValidationException("Invalid ID. ID must be greater than 0.");

            System.out.print("Enter animal name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) throw new ValidationException("Name cannot be empty.");

            System.out.print("Enter animal age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (age <= 0) throw new ValidationException("Invalid age. Age must be greater than 0.");

            System.out.print("Enter animal type (e.g., Dog, Cat): ");
            String typeName = scanner.nextLine();
            if (typeName.isEmpty()) throw new ValidationException("Animal type cannot be empty.");

            System.out.print("Enter animal special characteristics (e.g., Friendly, Shy): ");
            String specialCharacteristics = scanner.nextLine();

            System.out.print("Enter animal status (e.g., Available, Adopted): ");
            String status = scanner.nextLine();
            if (status.isEmpty()) throw new ValidationException("Status cannot be empty.");

            // Create an Animal object with a customized ID
            AnimalType animalType = new AnimalType(0, typeName, specialCharacteristics);  // Assuming ID 0 for AnimalType
            Animal animal = new Animal(id, name, animalType, age, status);

            // Add the animal to the repository
            String result = animalController.addAnimal(animal);
            System.out.println(result);

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    /**
     * Displays all animals by fetching them from the AnimalController.
     * If no animals are available, a message is displayed to the user.
     */
    private void viewAllAnimals() {
        try {
            List<Animal> animals = animalController.getAllAnimals();
            if (animals.isEmpty()) {
                System.out.println("No animals available.");
            } else {
                animals.forEach(animal -> System.out.println(animal));
            }
        } catch (Exception e) {
            System.out.println("Error retrieving animals: " + e.getMessage());
        }
    }

    /**
     * Displays a specific animal by its ID.
     * If the animal is found, its details are printed, otherwise, a "not found" message is displayed.
     */
    private void viewAnimalById() {
        try {
            System.out.print("Enter animal ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (id <= 0) throw new ValidationException("Invalid ID. ID must be greater than 0.");

            Animal animal = animalController.getAnimalById(id);
            if (animal != null) {
                System.out.println(animal);
            } else {
                System.out.println("Animal not found.");
            }
        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error retrieving animal: " + e.getMessage());
        }
    }

    /**
     * Updates the details of an animal based on the ID provided by the user.
     * The user can update the name, age, type, and status of the animal.
     */
    private void updateAnimal() {
        try {
            System.out.print("Enter animal ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (id <= 0) throw new ValidationException("Invalid ID. ID must be greater than 0.");

            Animal animal = animalController.getAnimalById(id);
            if (animal != null) {
                System.out.print("Enter new name (leave empty to keep current): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    if (name.isEmpty()) throw new ValidationException("Name cannot be empty.");
                    animal.setName(name);
                }

                System.out.print("Enter new age (leave empty to keep current): ");
                String ageStr = scanner.nextLine();
                if (!ageStr.isEmpty()) {
                    int age = Integer.parseInt(ageStr);
                    if (age <= 0) throw new ValidationException("Age must be greater than 0.");
                    animal.setAge(age);
                }

                System.out.print("Enter new animal type (leave empty to keep current): ");
                String typeName = scanner.nextLine();
                if (!typeName.isEmpty()) {
                    System.out.print("Enter new animal special characteristics (leave empty to keep current): ");
                    String specialCharacteristics = scanner.nextLine();
                    AnimalType updatedType = new AnimalType(0, typeName, specialCharacteristics); // Assuming ID is autogenerated
                    animal.setAnimalType(updatedType);
                }

                System.out.print("Enter new status (leave empty to keep current): ");
                String status = scanner.nextLine();
                if (!status.isEmpty()) {
                    if (status.isEmpty()) throw new ValidationException("Status cannot be empty.");
                    animal.setStatus(status);
                }

                animalController.updateAnimal(animal);
                System.out.println("Animal updated successfully!");
            } else {
                System.out.println("Animal not found.");
            }
        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    /**
     * Deletes an animal based on the ID provided by the user.
     * The animal is removed from the repository, and a success message is displayed.
     */
    private void deleteAnimal() {
        try {
            System.out.print("Enter animal ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (id <= 0) throw new ValidationException("Invalid ID. ID must be greater than 0.");

            // Call the deleteAnimal method in AnimalController, which returns a String
            String resultMessage = animalController.deleteAnimal(id);

            // Print the result message returned by the controller
            System.out.println(resultMessage);

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    /**
     * Sorts all animals by their age in ascending order and displays the sorted list.
     * If no animals are available, a message is displayed.
     */
    private void sortAnimalsByAge() {
        try {
            List<Animal> sortedAnimals = animalController.sortAnimalsByAge();
            if (sortedAnimals.isEmpty()) {
                System.out.println("No animals available to sort.");
            } else {
                sortedAnimals.forEach(animal -> System.out.println(animal));
            }
        } catch (Exception e) {
            System.out.println("Error sorting animals: " + e.getMessage());
        }
    }

    /**
     * Filters animals by their status (e.g., Available, Adopted) and displays the filtered list.
     * If no animals match the status, a message is displayed.
     */
    private void filterAnimalsByStatus() {
        try {
            System.out.print("Enter status to filter by (e.g., Available, Adopted): ");
            String status = scanner.nextLine();

            if (status.isEmpty()) throw new ValidationException("Status cannot be empty.");

            List<Animal> filteredAnimals = animalController.filterAnimalsByStatus(status);
            if (filteredAnimals.isEmpty()) {
                System.out.println("No animals found with the status: " + status);
            } else {
                filteredAnimals.forEach(animal -> System.out.println(animal));
            }
        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error filtering animals: " + e.getMessage());
        }
    }
}
