package controller;

import models.Volunteer;
import service.VolunteerService;

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
        volunteerService.addVolunteer(volunteer);
        return "Volunteer added successfully!";
    }

    /**
     * Retrieves a list of all volunteers in the system.
     *
     * @return A list of all volunteers.
     */
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    /**
     * Retrieves a specific volunteer by their ID.
     *
     * @param id The ID of the volunteer to retrieve.
     * @return The volunteer with the specified ID.
     */
    public Volunteer getVolunteerById(int id) {
        return volunteerService.getVolunteerById(id);
    }

    /**
     * Updates an existing volunteer's details.
     *
     * @param volunteer The volunteer with updated details.
     * @return A success message indicating the volunteer has been updated.
     */
    public String updateVolunteer(Volunteer volunteer) {
        volunteerService.updateVolunteer(volunteer);
        return "Volunteer updated successfully!";
    }

    /**
     * Deletes a volunteer from the system.
     *
     * @param id The ID of the volunteer to delete.
     * @return A success message indicating the volunteer has been deleted.
     */
    public String deleteVolunteer(int id) {
        volunteerService.deleteVolunteer(id);
        return "Volunteer deleted successfully!";
    }

    /**
     * Sorts the volunteers by their experience in ascending order.
     *
     * @return A list of volunteers sorted by experience.
     */
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerService.sortVolunteersByExperience();
    }

    /**
     * Filters the volunteers based on the minimum number of shelters they have worked with.
     *
     * @param minShelters The minimum number of shelters a volunteer must have worked with.
     * @return A list of volunteers who have worked with at least the specified number of shelters.
     */
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerService.filterVolunteersBySheltersCount(minShelters);
    }

    /**
     * Assigns an animal to a volunteer.
     *
     * @param volunteerId The ID of the volunteer.
     * @param animalId    The ID of the animal to be assigned.
     * @return A message indicating whether the assignment was successful.
     */
    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        return volunteerService.assignAnimalToVolunteer(volunteerId, animalId);
    }

    /**
     * Generates a unique ID for a volunteer.
     *
     * @return A unique ID.
     */
    public int generateUniqueId() {
        return volunteerService.generateUniqueId();
    }
}
