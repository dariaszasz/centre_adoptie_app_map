/**
 * Controller class for managing animals.
 * Provides operations to add, retrieve, update, delete, sort, and filter animals.
 */
package controller;

import models.Animal;
import service.AnimalService;

import java.util.List;

public class AnimalController {
    private AnimalService animalService;

    /**
     * Constructs an AnimalController with the specified service.
     *
     * @param animalService the service for managing animals
     */
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Adds a new animal to the system.
     *
     * @param animal the animal to be added
     * @return a success message indicating the animal was added
     */
    public String addAnimal(Animal animal) {
        animalService.addAnimal(animal);  // Add the animal to the service
        return "Animal added successfully!";
    }

    /**
     * Retrieves all animals from the system.
     *
     * @return a list of all animals
     */
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    /**
     * Retrieves an animal by its ID.
     *
     * @param id the ID of the animal to be retrieved
     * @return the animal with the specified ID, or null if not found
     */
    public Animal getAnimalById(int id) {
        return animalService.getAnimalById(id);
    }

    /**
     * Updates the details of an existing animal.
     *
     * @param animal the updated animal object
     * @return a success message indicating the animal was updated
     */
    public String updateAnimal(Animal animal) {
        animalService.updateAnimal(animal);
        return "Animal updated successfully!";
    }

    /**
     * Deletes an animal by its ID.
     *
     * @param id the ID of the animal to be deleted
     * @return a success message indicating the animal was deleted
     */
    public String deleteAnimal(int id) {
        animalService.deleteAnimal(id);
        return "Animal deleted successfully!";
    }

    /**
     * Sorts all animals by their age in ascending order.
     *
     * @return a list of animals sorted by age
     */
    public List<Animal> sortAnimalsByAge() {
        return animalService.sortAnimalsByAge();
    }

    /**
     * Filters animals by their status.
     *
     * @param status the status to filter animals by (e.g., "Available", "Adopted")
     * @return a list of animals matching the specified status
     */
    public List<Animal> filterAnimalsByStatus(String status) {
        return animalService.filterAnimalsByStatus(status);
    }
}
