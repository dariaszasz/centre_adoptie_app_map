/**
 * Controller class for managing veterinarians.
 * Provides operations to add, retrieve, update, delete, sort, and filter veterinarians.
 */
package controller;

import models.Veterinarian;
import service.VeterinarianService;

import java.util.List;

public class VeterinarianController {
    private VeterinarianService veterinarianService;

    /**
     * Constructs a VeterinarianController with the specified service.
     *
     * @param veterinarianService the service for managing veterinarians
     */
    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    /**
     * Adds a new veterinarian to the system.
     *
     * @param veterinarian the veterinarian to be added
     * @return a success message indicating the veterinarian was added
     */
    public String addVeterinarian(Veterinarian veterinarian) {
        veterinarianService.addVeterinarian(veterinarian);
        return "Veterinarian added successfully!";
    }

    /**
     * Retrieves all veterinarians from the system.
     *
     * @return a list of all veterinarians
     */
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianService.getAllVeterinarians();
    }

    /**
     * Retrieves a veterinarian by their ID.
     *
     * @param id the ID of the veterinarian to be retrieved
     * @return the veterinarian with the specified ID, or null if not found
     */
    public Veterinarian getVeterinarianById(int id) {
        return veterinarianService.getVeterinarianById(id);
    }

    /**
     * Updates the details of an existing veterinarian.
     *
     * @param veterinarian the updated veterinarian object
     * @return a success message indicating the veterinarian was updated
     */
    public String updateVeterinarian(Veterinarian veterinarian) {
        veterinarianService.updateVeterinarian(veterinarian);
        return "Veterinarian updated successfully!";
    }

    /**
     * Deletes a veterinarian by their ID.
     *
     * @param id the ID of the veterinarian to be deleted
     * @return a success message indicating the veterinarian was deleted
     */
    public String deleteVeterinarian(int id) {
        veterinarianService.deleteVeterinarian(id);
        return "Veterinarian deleted successfully!";
    }

    /**
     * Sorts all veterinarians by their specialization.
     *
     * @return a list of veterinarians sorted by specialization
     */
    public List<Veterinarian> sortVeterinariansBySpecialization() {
        return veterinarianService.sortVeterinariansBySpecialization();
    }

    /**
     * Filters veterinarians by their specialization.
     *
     * @param specialization the specialization to filter veterinarians by
     * @return a list of veterinarians with the specified specialization
     */
    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        return veterinarianService.filterVeterinariansBySpecialization(specialization);
    }
}
