package controller;

import models.Animal;
import service.AnimalService;
import exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

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
        try {
            animalService.addAnimal(animal);  // Add the animal to the service
            return "Animal added successfully!";
        } catch (Exception e) {
            return "Error adding animal: " + e.getMessage();
        }
    }

    /**
     * Retrieves all animals from the system.
     *
     * @return a list of all animals
     */
    public List<Animal> getAllAnimals() {
        try {
            List<Animal> animals = animalService.getAllAnimals();
            if (animals.isEmpty()) {
                System.out.println("No animals found.");
            }
            return animals;
        } catch (Exception e) {
            System.out.println("Error retrieving animals: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves an animal by its ID.
     *
     * @param id the ID of the animal to be retrieved
     * @return the animal with the specified ID, or null if not found
     */
    public Animal getAnimalById(int id) {
        try {
            Optional<Animal> animal = animalService.getAnimalById(id);
            if (animal.isEmpty()) {
                throw new EntityNotFoundException("Animal with ID " + id + " not found.");
            }
            return animal.orElse(null);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error retrieving animal: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates the details of an existing animal.
     *
     * @param animal the updated animal object
     * @return a success message indicating the animal was updated
     */
    public String updateAnimal(Animal animal) {
        try {
            animalService.updateAnimal(animal);
            return "Animal updated successfully!";
        } catch (EntityNotFoundException e) {
            return "Error updating animal: " + e.getMessage();
        } catch (Exception e) {
            return "Error updating animal: " + e.getMessage();
        }
    }

    /**
     * Deletes an animal by its ID.
     *
     * @param id the ID of the animal to be deleted
     * @return a success message indicating the animal was deleted
     */
    public String deleteAnimal(int id) {
        try {
            boolean deleted = animalService.deleteAnimal(id);
            if (deleted) {
                return "Animal deleted successfully!";
            } else {
                throw new EntityNotFoundException("Animal with ID " + id + " not found.");
            }
        } catch (EntityNotFoundException e) {
            return "Error deleting animal: " + e.getMessage();
        } catch (Exception e) {
            return "Error deleting animal: " + e.getMessage();
        }
    }

    /**
     * Sorts all animals by their age in ascending order.
     *
     * @return a list of animals sorted by age
     */
    public List<Animal> sortAnimalsByAge() {
        try {
            return animalService.sortAnimalsByAge();
        } catch (Exception e) {
            System.out.println("Error sorting animals by age: " + e.getMessage());
            return null;
        }
    }

    /**
     * Filters animals by their status.
     *
     * @param status the status to filter animals by (e.g., "Available", "Adopted")
     * @return a list of animals matching the specified status
     */
    public List<Animal> filterAnimalsByStatus(String status) {
        try {
            return animalService.filterAnimalsByStatus(status);
        } catch (Exception e) {
            System.out.println("Error filtering animals by status: " + e.getMessage());
            return null;
        }
    }
}
