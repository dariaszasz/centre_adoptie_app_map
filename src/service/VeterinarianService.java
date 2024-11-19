package service;

import models.Veterinarian;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VeterinarianService {
    private IRepository<Veterinarian> veterinarianRepository;

    public VeterinarianService(IRepository<Veterinarian> veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    // Method to add a veterinarian
    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.add(veterinarian);
    }

    // Method to get all veterinarians
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.getAll();
    }

    // Method to get a veterinarian by ID
    public Veterinarian getVeterinarianById(int id) {
        return veterinarianRepository.getById(id);
    }

    // Method to update a veterinarian
    public void updateVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.update(veterinarian);
    }

    // Method to delete a veterinarian
    public void deleteVeterinarian(int id) {
        veterinarianRepository.delete(id);
    }

    // Sorting veterinarians by specialization
    public List<Veterinarian> sortVeterinariansBySpecialization() {
        return veterinarianRepository.getAll().stream()
                .sorted((v1, v2) -> v1.getSpecialization().compareTo(v2.getSpecialization()))  // Assuming specialization is a String
                .collect(Collectors.toList());
    }

    // Filtering veterinarians by their specialization
    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        return veterinarianRepository.getAll().stream()
                .filter(veterinarian -> veterinarian.getSpecialization().equalsIgnoreCase(specialization))
                .collect(Collectors.toList());
    }
}
