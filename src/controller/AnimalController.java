package controller;

import models.Animal;
import service.AnimalService;

import java.util.List;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Endpoint to add a new animal
    public String addAnimal(Animal animal) {
        animalService.addAnimal(animal);  // Adăugăm animalul în serviciu
        return "Animal added successfully!";
    }

    // Endpoint to get all animals
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    // Endpoint to get an animal by ID
    public Animal getAnimalById(int id) {
        return animalService.getAnimalById(id);
    }

    // Endpoint to update an animal
    public String updateAnimal(Animal animal) {
        animalService.updateAnimal(animal);
        return "Animal updated successfully!";
    }

    // Endpoint to delete an animal
    public String deleteAnimal(int id) {
        animalService.deleteAnimal(id);
        return "Animal deleted successfully!";
    }

    // Endpoint to sort animals by age
    public List<Animal> sortAnimalsByAge() {
        return animalService.sortAnimalsByAge();
    }

    // Endpoint to filter animals by status
    public List<Animal> filterAnimalsByStatus(String status) {
        return animalService.filterAnimalsByStatus(status);
    }

}