package presentation;

import controller.VolunteerController;
import models.Volunteer;
import exceptions.ValidationException;

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
                    // Add a new volunteer
                    addVolunteer(scanner);
                    break;

                case 2:
                    // Assign an animal to a volunteer
                    assignAnimalToVolunteer(scanner);
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
        try {
            System.out.println("\n--- Add New Volunteer ---");

            // Generate unique ID for the volunteer
            int id = volunteerController.generateUniqueId();

            System.out.print("Enter Volunteer Name: ");
            scanner.nextLine(); // Consume leftover newline
            String name = scanner.nextLine();
            if (name.isEmpty()) throw new ValidationException("Name cannot be empty.");

            System.out.print("Enter Volunteer Contact Details: ");
            String contactDetails = scanner.nextLine();
            if (contactDetails.isEmpty()) throw new ValidationException("Contact details cannot be empty.");

            System.out.print("Enter Volunteer Experience: ");
            String experience = scanner.nextLine();
            if (experience.isEmpty()) throw new ValidationException("Experience cannot be empty.");

            // Create a new Volunteer object with the generated ID
            Volunteer volunteer = new Volunteer(id, name, contactDetails, experience);

            // Add the volunteer using the controller
            volunteerController.addVolunteer(volunteer);
            System.out.println("Volunteer added successfully.");

            // Debug print: Verify if the volunteer was added
            System.out.println("Current Volunteers: ");
            volunteerController.getAllVolunteers().forEach(v -> System.out.println(v.getId() + ": " + v.getName()));

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to assign an animal to a volunteer.
     * It ensures that both volunteer and animal IDs are valid before performing the assignment.
     *
     * @param scanner The scanner object used to read user input.
     */
    private void assignAnimalToVolunteer(Scanner scanner) {
        try {
            System.out.print("Enter Volunteer ID: ");
            int volunteerId = scanner.nextInt();
            if (volunteerId <= 0) throw new ValidationException("Invalid Volunteer ID. ID must be greater than 0.");

            System.out.print("Enter Animal ID: ");
            int animalId = scanner.nextInt();
            if (animalId <= 0) throw new ValidationException("Invalid Animal ID. ID must be greater than 0.");

            String response = volunteerController.assignAnimalToVolunteer(volunteerId, animalId);
            System.out.println(response);

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
}
