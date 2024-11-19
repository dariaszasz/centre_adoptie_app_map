package service;

import models.Animal;
import repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalService {
    private IRepository<Animal> animalRepository;

    public AnimalService(IRepository<Animal> animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Method to add an animal
    public void addAnimal(Animal animal) {
        animalRepository.add(animal);
    }

    // Method to get all animals
    public List<Animal> getAllAnimals() {
        return animalRepository.getAll();
    }

    // Method to get animal by ID
    public Animal getAnimalById(int id) {
        return animalRepository.getById(id);
    }

    // Method to update an animal
    public void updateAnimal(Animal animal) {
        animalRepository.update(animal);
    }

    // Method to delete an animal
    public void deleteAnimal(int id) {
        animalRepository.delete(id);
    }

    // Sorting animals by their age in ascending order
    public List<Animal> sortAnimalsByAge() {
        return animalRepository.getAll().stream()
                .sorted((a1, a2) -> Integer.compare(a1.getAge(), a2.getAge()))
                .collect(Collectors.toList());
    }

    // Filtering animals by their availability status
    public List<Animal> filterAnimalsByStatus(String status) {
        return animalRepository.getAll().stream()
                .filter(animal -> animal.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}

