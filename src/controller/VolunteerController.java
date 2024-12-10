package controller;

import models.Volunteer;
import service.VolunteerService;
import exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Controller class responsible for managing volunteers and their related operations.
 * It handles actions like adding, viewing, updating, deleting, and sorting volunteers.
 * Also includes functionality to assign animals to volunteers and generate unique IDs.
 */
public class VolunteerController {
    private VolunteerService volunteerService;

    /**
     * Constructor that initializes the controller with the volunteer service.
     *
     * @param volunteerService The service responsible for volunteer operations.
     */
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Adds a new volunteer to the system.
     *
     * @param volunteer The volunteer to be added.
     * @return A success message indicating the volunteer has been added.
     */
    public String addVolunteer(Volunteer volunteer) {
        try {
            volunteerService.addVolunteer(volunteer);
            return "Volunteer added successfully!";
        } catch (Exception e) {
            return "Error adding volunteer: " + e.getMessage();
        }
    }

    /**
     * Retrieves a list of all volunteers in the system.
     *
     * @return A list of all volunteers.
     */
    public List<Volunteer> getAllVolunteers() {
        try {
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();
            if (volunteers.isEmpty()) {
                System.out.println("No volunteers found.");
            }
            return volunteers;
        } catch (Exception e) {
            System.out.println("Error retrieving volunteers: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a specific volunteer by their ID.
     *
     * @param id The ID of the volunteer to retrieve.
     * @return The volunteer with the specified ID.
     */
    public Volunteer getVolunteerById(int id) {
        try {
            Volunteer volunteer = volunteerService.getVolunteerById(id);
            if (volunteer == null) {
                throw new EntityNotFoundException("Volunteer with ID " + id + " not found.");
            }
            return volunteer;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error retrieving volunteer: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing volunteer's details.
     *
     * @param volunteer The volunteer with updated details.
     * @return A success message indicating the volunteer has been updated.
     */
    public String updateVolunteer(Volunteer volunteer) {
        try {
            volunteerService.updateVolunteer(volunteer);
            return "Volunteer updated successfully!";
        } catch (EntityNotFoundException e) {
            return "Error updating volunteer: " + e.getMessage();
        } catch (Exception e) {
            return "Error updating volunteer: " + e.getMessage();
        }
    }

    /**
     * Deletes a volunteer from the system.
     *
     * @param id The ID of the volunteer to delete.
     * @return A success message indicating the volunteer has been deleted.
     */
    public String deleteVolunteer(int id) {
        try {
            boolean deleted = volunteerService.deleteVolunteer(id);
            if (deleted) {
                return "Volunteer deleted successfully!";
            } else {
                throw new EntityNotFoundException("Volunteer with ID " + id + " not found.");
            }
        } catch (EntityNotFoundException e) {
            return "Error deleting volunteer: " + e.getMessage();
        } catch (Exception e) {
            return "Error deleting volunteer: " + e.getMessage();
        }
    }

    /**
     * Sorts the volunteers by their experience in ascending order.
     *
     * @return A list of volunteers sorted by experience.
     */
    public List<Volunteer> sortVolunteersByExperience() {
        try {
            return volunteerService.sortVolunteersByExperience();
        } catch (Exception e) {
            System.out.println("Error sorting volunteers by experience: " + e.getMessage());
            return null;
        }
    }

    /**
     * Filters the volunteers based on the minimum number of shelters they have worked with.
     *
     * @param minShelters The minimum number of shelters a volunteer must have worked with.
     * @return A list of volunteers who have worked with at least the specified number of shelters.
     */
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        try {
            return volunteerService.filterVolunteersBySheltersCount(minShelters);
        } catch (Exception e) {
            System.out.println("Error filtering volunteers by shelters count: " + e.getMessage());
            return null;
        }
    }

    /**
     * Assigns an animal to a volunteer.
     *
     * @param volunteerId The ID of the volunteer.
     * @param animalId    The ID of the animal to be assigned.
     * @return A message indicating whether the assignment was successful.
     */
    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        try {
            return volunteerService.assignAnimalToVolunteer(volunteerId, animalId);
        } catch (Exception e) {
            System.out.println("Error assigning animal to volunteer: " + e.getMessage());
            return "Error assigning animal to volunteer.";
        }
    }

    /**
     * Generates a unique ID for a volunteer.
     *
     * @return A unique ID.
     */
    public int generateUniqueId() {
        try {
            return volunteerService.generateUniqueId();
        } catch (Exception e) {
            System.out.println("Error generating unique ID: " + e.getMessage());
            return -1;  // Indicates an error
        }
    }
}
