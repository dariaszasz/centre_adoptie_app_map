package controller;

import models.Volunteer;
import service.VolunteerService;

import java.util.List;

public class VolunteerController {
    private VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    // Endpoint to add a new volunteer
    public String addVolunteer(Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return "Volunteer added successfully!";
    }

    // Endpoint to get all volunteers
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    // Endpoint to get a volunteer by ID
    public Volunteer getVolunteerById(int id) {
        return volunteerService.getVolunteerById(id);
    }

    // Endpoint to update a volunteer
    public String updateVolunteer(Volunteer volunteer) {
        volunteerService.updateVolunteer(volunteer);
        return "Volunteer updated successfully!";
    }

    // Endpoint to delete a volunteer
    public String deleteVolunteer(int id) {
        volunteerService.deleteVolunteer(id);
        return "Volunteer deleted successfully!";
    }

    // Endpoint to sort volunteers by experience
    public List<Volunteer> sortVolunteersByExperience() {
        return volunteerService.sortVolunteersByExperience();
    }

    // Endpoint to filter volunteers by shelters count
    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        return volunteerService.filterVolunteersBySheltersCount(minShelters);
    }

    // Metodă pentru a atribui un animal unui voluntar
    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        return volunteerService.assignAnimalToVolunteer(volunteerId, animalId);
    }

    // Metodă pentru a genera un ID unic pentru un voluntar
    public int generateUniqueId() {
        return volunteerService.generateUniqueId();
    }
}