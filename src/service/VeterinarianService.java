package service;

import models.Veterinarian;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing veterinarian-related operations.
 * It interacts with the repository to add, update, delete, and retrieve veterinarians.
 */
public class VeterinarianService {
    private IRepository<Veterinarian> veterinarianRepository;

    /**
     * Constructor for the VeterinarianService.
     *
     * @param veterinarianRepository The repository for veterinarians.
     */
    public VeterinarianService(IRepository<Veterinarian> veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    /**
     * Adds a new veterinarian to the repository.
     *
     * @param veterinarian The veterinarian to be added.
     */
    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.add(veterinarian);
    }

    /**
     * Retrieves all veterinarians from the repository.
     *
     * @return A list of all veterinarians.
     */
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.getAll();
    }

    /**
     * Retrieves a veterinarian by their ID.
     *
     * @param id The ID of the veterinarian to retrieve.
     * @return The veterinarian with the given ID, or null if not found.
     */
    public Veterinarian getVeterinarianById(int id) {
        return veterinarianRepository.getById(id);
    }

    /**
     * Updates the information of an existing veterinarian in the repository.
     *
     * @param veterinarian The veterinarian with updated information.
     */
    public void updateVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.update(veterinarian);
    }

    /**
     * Deletes a veterinarian from the repository by their ID.
     *
     * @param id The ID of the veterinarian to be deleted.
     */
    public void deleteVeterinarian(int id) {
        veterinarianRepository.delete(id);
    }

    /**
     * Sorts veterinarians by their specialization in alphabetical order.
     *
     * @return A list of veterinarians sorted by specialization.
     */
    public List<Veterinarian> sortVeterinariansBySpecialization() {
        return veterinarianRepository.getAll().stream()
                .sorted((v1, v2) -> v1.getSpecialization().compareTo(v2.getSpecialization()))
                .collect(Collectors.toList());
    }

    /**
     * Filters veterinarians based on their specialization.
     *
     * @param specialization The specialization to filter veterinarians by.
     * @return A list of veterinarians that match the given specialization.
     */
    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        return veterinarianRepository.getAll().stream()
                .filter(veterinarian -> veterinarian.getSpecialization().equalsIgnoreCase(specialization))
                .collect(Collectors.toList());
    }
}
