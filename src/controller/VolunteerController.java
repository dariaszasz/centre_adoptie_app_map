package controller;

import models.Volunteer;
import service.VolunteerService;

import java.util.List;

/**
 * Controller class for managing volunteers. Provides endpoints for adding,
 * retrieving, updating, deleting, and filtering volunteers.
 */
public class VolunteerController {
    private VolunteerService volunteerService;

    /**
     * Constructor for the VolunteerController.
     *
     * @param volunteerService the service handling volunteer operations
     */
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Adds a new volunteer to the system.
     *
     * @param volunteer the volunteer to be added
     * @return a success message
     */
    public String addVolunteer(Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return "Volunteer added successfully!";
    }

    /**
     * Retrieves a list of all volunteers.
     *
     * @return a list of all volunteers
     */
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    /**
     * Retrieves a specific volunteer by their unique ID.
     *
     * @param id the ID of the volunteer to retrieve
     * @return the volunteer with the specified ID, or null if not found
     */
    public Volunteer getVolunteerById(int id) {
        return volunteerService.getVolunteerById(id);
    }

    /**
     * Updates the details of an existing volunteer.
     *
     * @param volunteer the volunteer object containing updated information
     * @return a success message
     */
    public String updateVolunteer(Volunteer volunteer) {
        volunteerService.updateVolunteer(volunteer);
        return "Volunteer updated successfully!";
    }

    /**
     * Deletes a volunteer from the system by their unique ID.
     *
     * @param id the ID of the volunteer to delete
     * @return a success message
     */
    public String deleteVolunteer(int id) {
        volunteerService.deleteVolunteer(id);
        return "Volunteer deleted successfully!";
    }

    /**
     * Sorts volunteers based on their experience.
     *
     * @return a list of volunteers sorted by experience
     */
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerService.sortVolunteersByExperience();
    }

    /**
     * Filters volunteers based on the minimum number of shelters they work with.
     *
     * @param minShelters the minimum number of shelters a volunteer must work with
     * @return a list of volunteers matching the criteria
     */
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerService.filterVolunteersBySheltersCount(minShelters);
    }
}
