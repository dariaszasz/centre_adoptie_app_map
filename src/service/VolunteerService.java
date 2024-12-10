package service;

import exceptions.BusinessLogicException;
import models.Animal;
import models.Volunteer;
import repository.IRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VolunteerService {
    private final IRepository<Animal> animalRepository;
    private final IRepository<Volunteer> volunteerRepository;

    public VolunteerService(IRepository<Volunteer> volunteerRepository, IRepository<Animal> animalRepository) {
        this.volunteerRepository = volunteerRepository;
        this.animalRepository = animalRepository;
    }

    public void addVolunteer(Volunteer volunteer) {
        if (volunteer == null) {
            throw new BusinessLogicException("Volunteer cannot be null.");
        }
        volunteer.setId(generateUniqueId());
        volunteerRepository.add(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            throw new BusinessLogicException("No volunteers available.");
        }
        return volunteers;
    }

    public Volunteer getVolunteerById(int id) {
        Volunteer volunteer = volunteerRepository.getById(id);
        if (volunteer == null) {
            throw new BusinessLogicException("Volunteer with ID " + id + " not found.");
        }
        return volunteer;
    }

    public void updateVolunteer(Volunteer volunteer) {
        if (volunteer == null) {
            throw new BusinessLogicException("Volunteer cannot be null.");
        }

        Volunteer existingVolunteer = volunteerRepository.getById(volunteer.getId());
        if (existingVolunteer == null) {
            throw new BusinessLogicException("Volunteer with ID " + volunteer.getId() + " not found.");
        }

        volunteerRepository.update(volunteer);
    }

    public boolean deleteVolunteer(int id) {
        Volunteer volunteer = volunteerRepository.getById(id);
        if (volunteer == null) {
            throw new BusinessLogicException("Volunteer with ID " + id + " not found.");
        }

        volunteerRepository.delete(id);
        return true; // Return true to indicate successful deletion
    }

    public List<Volunteer> sortVolunteersByExperience() {
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            throw new BusinessLogicException("No volunteers available to sort.");
        }

        return volunteers.stream()
                .sorted((v1, v2) -> v2.getExperience().compareTo(v1.getExperience()))
                .collect(Collectors.toList());
    }

    public List<Volunteer> filterVolunteersBySheltersCount(int minShelters) {
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            throw new BusinessLogicException("No volunteers available to filter.");
        }

        return volunteers.stream()
                .filter(volunteer -> volunteer.getShelters().size() >= minShelters)
                .collect(Collectors.toList());
    }

    public String assignAnimalToVolunteer(int volunteerId, int animalId) {
        Optional<Volunteer> volunteerOpt = volunteerRepository.getAll().stream()
                .filter(v -> v.getId() == volunteerId)
                .findFirst();

        if (volunteerOpt.isEmpty()) {
            throw new BusinessLogicException("Volunteer with ID " + volunteerId + " not found.");
        }

        Volunteer volunteer = volunteerOpt.get();

        Optional<Animal> animalOpt = animalRepository.getAll().stream()
                .filter(a -> a.getId() == animalId)
                .findFirst();

        if (animalOpt.isEmpty()) {
            throw new BusinessLogicException("Animal with ID " + animalId + " not found.");
        }

        Animal animal = animalOpt.get();
        volunteer.addAnimal(animal);
        animal.setAssignedVolunteer(volunteer);

        return "Animal " + animal.getName() + " has been assigned to volunteer " + volunteer.getName();
    }

    public int generateUniqueId() {
        List<Volunteer> volunteers = volunteerRepository.getAll();
        if (volunteers.isEmpty()) {
            return 1;  // If there are no volunteers, return 1 as the ID.
        }

        return volunteers.stream()
                .mapToInt(Volunteer::getId)
                .max()
                .orElse(0) + 1;
    }
}
