package controller;

import models.Veterinarian;
import service.VeterinarianService;

import java.util.List;

public class VeterinarianController {
    private VeterinarianService veterinarianService;

    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    // Endpoint to add a new veterinarian
    public String addVeterinarian(Veterinarian veterinarian) {
        veterinarianService.addVeterinarian(veterinarian);
        return "Veterinarian added successfully!";
    }

    // Endpoint to get all veterinarians
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianService.getAllVeterinarians();
    }

    // Endpoint to get a veterinarian by ID
    public Veterinarian getVeterinarianById(int id) {
        return veterinarianService.getVeterinarianById(id);
    }

    // Endpoint to update a veterinarian
    public String updateVeterinarian(Veterinarian veterinarian) {
        veterinarianService.updateVeterinarian(veterinarian);
        return "Veterinarian updated successfully!";
    }

    // Endpoint to delete a veterinarian
    public String deleteVeterinarian(int id) {
        veterinarianService.deleteVeterinarian(id);
        return "Veterinarian deleted successfully!";
    }

    // Endpoint to sort veterinarians by specialization
    public List<Veterinarian> sortVeterinariansBySpecialization() {
        return veterinarianService.sortVeterinariansBySpecialization();
    }

    // Endpoint to filter veterinarians by their specialization
    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        return veterinarianService.filterVeterinariansBySpecialization(specialization);
    }
}