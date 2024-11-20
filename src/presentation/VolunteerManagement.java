package presentation;

import controller.VolunteerController;
import models.Volunteer;

import java.util.Scanner;

/**
 * Handles the user interface for managing volunteers in the system.
 * This class allows the user to interact with the volunteer management system,
 * including adding new volunteers, assigning animals to volunteers, and exiting the system.
 */
public class VolunteerManagement {
    private VolunteerController volunteerController;

    /**
     * Constructor to initialize the VolunteerManagement with a given VolunteerController.
     *
     * @param volunteerController The VolunteerController used to manage volunteer-related operations.
     */
    public VolunteerManagement(VolunteerController volunteerController) {
        this.volunteerController = volunteerController;
    }

    /**
     * Displays the menu and handles the user's input to manage volunteers.
     * The menu includes options for adding a volunteer, assigning an animal to a volunteer,
     * and exiting the program.
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Volunteer Management ---");
            System.out.println("1. Add Volunteer");
            System.out.println("2. Assign Animal to Volunteer");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Atribuie un animal unui voluntar
                    addVolunteer(scanner);
                    break;

                case 2:
                    // Atribuie un animal unui voluntar
                    System.out.println("Enter Volunteer ID: ");
                    int volunteerId = scanner.nextInt();
                    System.out.println("Enter Animal ID: ");
                    int animalId = scanner.nextInt();
                    String response = volunteerController.assignAnimalToVolunteer(volunteerId, animalId);
                    System.out.println(response);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    /**
     * Prompts the user for volunteer details and adds a new volunteer to the system.
     *
     * @param scanner The scanner object used to read user input.
     */
    private void addVolunteer(Scanner scanner) {
        System.out.println("\n--- Add New Volunteer ---");
        // Generarea ID-ului unic pentru voluntar
        int id = volunteerController.generateUniqueId();

        System.out.print("Enter Volunteer Name: ");
        scanner.nextLine(); // Consumă linia rămasă
        String name = scanner.nextLine();

        System.out.print("Enter Volunteer Contact Details: ");
        String contactDetails = scanner.nextLine();

        System.out.print("Enter Volunteer Experience: ");
        String experience = scanner.nextLine();

        // Crează un nou obiect Volunteer cu ID-ul generat
        Volunteer volunteer = new Volunteer(id, name, contactDetails, experience);

        // Adaugă voluntarul folosind controller-ul
        volunteerController.addVolunteer(volunteer);
        System.out.println("Volunteer added successfully.");

        // Debug print: Verifică dacă voluntarul a fost adăugat
        System.out.println("Current Volunteers: ");
        volunteerController.getAllVolunteers().forEach(v -> System.out.println(v.getId() + ": " + v.getName()));
    }
}
