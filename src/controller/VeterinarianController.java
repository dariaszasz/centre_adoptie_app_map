package controller;

import models.Veterinarian;
import service.VeterinarianService;
import exceptions.EntityNotFoundException;

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
        try {
            veterinarianService.addVeterinarian(veterinarian);
            return "Veterinarian added successfully!";
        } catch (Exception e) {
            return "Error adding veterinarian: " + e.getMessage();
        }
    }

    /**
     * Retrieves all veterinarians from the system.
     *
     * @return a list of all veterinarians
     */
    public List<Veterinarian> getAllVeterinarians() {
        try {
            List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
            if (veterinarians.isEmpty()) {
                System.out.println("No veterinarians found.");
            }
            return veterinarians;
        } catch (Exception e) {
            System.out.println("Error retrieving veterinarians: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a veterinarian by their ID.
     *
     * @param id the ID of the veterinarian to be retrieved
     * @return the veterinarian with the specified ID, or null if not found
     */
    public Veterinarian getVeterinarianById(int id) {
        try {
            Veterinarian veterinarian = veterinarianService.getVeterinarianById(id);
            if (veterinarian == null) {
                throw new EntityNotFoundException("Veterinarian with ID " + id + " not found.");
            }
            return veterinarian;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error retrieving veterinarian: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates the details of an existing veterinarian.
     *
     * @param veterinarian the updated veterinarian object
     * @return a success message indicating the veterinarian was updated
     */
    public String updateVeterinarian(Veterinarian veterinarian) {
        try {
            veterinarianService.updateVeterinarian(veterinarian);
            return "Veterinarian updated successfully!";
        } catch (EntityNotFoundException e) {
            return "Error updating veterinarian: " + e.getMessage();
        } catch (Exception e) {
            return "Error updating veterinarian: " + e.getMessage();
        }
    }

    /**
     * Deletes a veterinarian by their ID.
     *
     * @param id the ID of the veterinarian to be deleted
     * @return a success message indicating the veterinarian was deleted
     */
    public String deleteVeterinarian(int id) {
        try {
            boolean deleted = veterinarianService.deleteVeterinarian(id);
            if (deleted) {
                return "Veterinarian deleted successfully!";
            } else {
                throw new EntityNotFoundException("Veterinarian with ID " + id + " not found.");
            }
        } catch (EntityNotFoundException e) {
            return "Error deleting veterinarian: " + e.getMessage();
        } catch (Exception e) {
            return "Error deleting veterinarian: " + e.getMessage();
        }
    }

    /**
     * Sorts all veterinarians by their specialization.
     *
     * @return a list of veterinarians sorted by specialization
     */
    public List<Veterinarian> sortVeterinariansBySpecialization() {
        try {
            return veterinarianService.sortVeterinariansBySpecialization();
        } catch (Exception e) {
            System.out.println("Error sorting veterinarians by specialization: " + e.getMessage());
            return null;
        }
    }

    /**
     * Filters veterinarians by their specialization.
     *
     * @param specialization the specialization to filter veterinarians by
     * @return a list of veterinarians with the specified specialization
     */
    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        try {
            return veterinarianService.filterVeterinariansBySpecialization(specialization);
        } catch (Exception e) {
            System.out.println("Error filtering veterinarians by specialization: " + e.getMessage());
            return null;
        }
    }
}
