package service;

import exceptions.BusinessLogicException;
import models.Veterinarian;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VeterinarianService {
    private IRepository<Veterinarian> veterinarianRepository;

    public VeterinarianService(IRepository<Veterinarian> veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new BusinessLogicException("Veterinarian cannot be null");
        }
        veterinarianRepository.add(veterinarian);
    }

    public List<Veterinarian> getAllVeterinarians() {
        List<Veterinarian> veterinarians = veterinarianRepository.getAll();
        if (veterinarians.isEmpty()) {
            throw new BusinessLogicException("No veterinarians available.");
        }
        return veterinarians;
    }

    public Veterinarian getVeterinarianById(int id) {
        Veterinarian veterinarian = veterinarianRepository.getById(id);
        if (veterinarian == null) {
            throw new BusinessLogicException("Veterinarian with ID " + id + " not found.");
        }
        return veterinarian;
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) {
            throw new BusinessLogicException("Veterinarian cannot be null");
        }

        Veterinarian existingVeterinarian = veterinarianRepository.getById(veterinarian.getId());
        if (existingVeterinarian == null) {
            throw new BusinessLogicException("Veterinarian with ID " + veterinarian.getId() + " not found.");
        }

        veterinarianRepository.update(veterinarian);
    }

    public boolean deleteVeterinarian(int id) {
        Veterinarian veterinarian = veterinarianRepository.getById(id);
        if (veterinarian == null) {
            throw new BusinessLogicException("Veterinarian with ID " + id + " not found.");
        }

        veterinarianRepository.delete(id);
        return true; // Return true to indicate successful deletion
    }

    public List<Veterinarian> sortVeterinariansBySpecialization() {
        List<Veterinarian> veterinarians = veterinarianRepository.getAll();
        if (veterinarians.isEmpty()) {
            throw new BusinessLogicException("No veterinarians available to sort.");
        }

        return veterinarians.stream()
                .sorted((v1, v2) -> v1.getSpecialization().compareTo(v2.getSpecialization()))
                .collect(Collectors.toList());
    }

    public List<Veterinarian> filterVeterinariansBySpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty()) {
            throw new BusinessLogicException("Specialization must be provided.");
        }

        List<Veterinarian> veterinarians = veterinarianRepository.getAll();
        List<Veterinarian> filteredVeterinarians = veterinarians.stream()
                .filter(veterinarian -> veterinarian.getSpecialization().equalsIgnoreCase(specialization))
                .collect(Collectors.toList());

        if (filteredVeterinarians.isEmpty()) {
            throw new BusinessLogicException("No veterinarians found with specialization: " + specialization);
        }
        return filteredVeterinarians;
    }
}
