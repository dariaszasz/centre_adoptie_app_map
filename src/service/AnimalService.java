package service;

import models.Animal;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing animal-related operations.
 * It interacts with the repository to add, update, delete, and retrieve animals.
 */
public class AnimalService {
    private IRepository<Animal> animalRepository;

    /**
     * Constructor for the AnimalService.
     *
     * @param animalRepository The repository for animals.
     */
    public AnimalService(IRepository<Animal> animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * Adds a new animal to the repository.
     *
     * @param animal The animal to be added.
     */
    public void addAnimal(Animal animal) {
        animalRepository.add(animal);
    }

    /**
     * Retrieves all animals from the repository.
     *
     * @return A list of all animals.
     */
    public List<Animal> getAllAnimals() {
        return animalRepository.getAll();
    }

    /**
     * Retrieves an animal by its ID.
     *
     * @param id The ID of the animal to retrieve.
     * @return The animal with the given ID, or null if not found.
     */
    public Animal getAnimalById(int id) {
        return animalRepository.getById(id);
    }

    /**
     * Updates the information of an existing animal in the repository.
     *
     * @param animal The animal with updated information.
     */
    public void updateAnimal(Animal animal) {
        animalRepository.update(animal);
    }

    /**
     * Deletes an animal from the repository by its ID.
     *
     * @param id The ID of the animal to be deleted.
     */
    public void deleteAnimal(int id) {
        animalRepository.delete(id);
    }

    /**
     * Sorts animals by their age in ascending order.
     *
     * @return A list of animals sorted by age in ascending order.
     */
    public List<Animal> sortAnimalsByAge() {
        return animalRepository.getAll().stream()
                .sorted((a1, a2) -> Integer.compare(a1.getAge(), a2.getAge()))
                .collect(Collectors.toList());
    }

    /**
     * Filters animals based on their availability status.
     *
     * @param status The status to filter animals by (e.g., "Available", "Adopted").
     * @return A list of animals that match the given status.
     */
    public List<Animal> filterAnimalsByStatus(String status) {
        return animalRepository.getAll().stream()
                .filter(animal -> animal.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
