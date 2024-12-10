package service;

import exceptions.BusinessLogicException;
import models.Animal;
import repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalService {
    private IRepository<Animal> animalRepository;

    public AnimalService(IRepository<Animal> animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void addAnimal(Animal animal) {
        if (animal == null) {
            throw new BusinessLogicException("Animal cannot be null");
        }
        animalRepository.add(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAll();
    }

    /*public Animal getAnimalById(int id) {
        Animal animal = animalRepository.getById(id);
        if (animal == null) {
            throw new BusinessLogicException("Animal with ID " + id + " not found.");
        }
        return animal;
    }*/
    public Optional<Animal> getAnimalById(int id) {
        return Optional.ofNullable(animalRepository.getById(id));
    }


    public void updateAnimal(Animal animal) {
        if (animal == null) {
            throw new BusinessLogicException("Animal cannot be null");
        }

        Animal existingAnimal = animalRepository.getById(animal.getId());
        if (existingAnimal == null) {
            throw new BusinessLogicException("Animal with ID " + animal.getId() + " not found.");
        }

        animalRepository.update(animal);
    }

    public boolean deleteAnimal(int id) {
        Animal animal = animalRepository.getById(id);
        if (animal == null) {
            throw new BusinessLogicException("Animal with ID " + id + " not found.");
        }

        animalRepository.delete(id);
        return true; // Return true to indicate successful deletion
    }

    public List<Animal> sortAnimalsByAge() {
        List<Animal> animals = animalRepository.getAll();
        if (animals.isEmpty()) {
            throw new BusinessLogicException("No animals available to sort.");
        }

        return animals.stream()
                .sorted((a1, a2) -> Integer.compare(a1.getAge(), a2.getAge()))
                .collect(Collectors.toList());
    }

    /*public List<Animal> filterAnimalsByStatus(String status) {
        List<Animal> animals = animalRepository.getAll();
        List<Animal> filteredAnimals = animals.stream()
                .filter(animal -> animal.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());

        if (filteredAnimals.isEmpty()) {
            throw new BusinessLogicException("No animals found with status: " + status);
        }
        return filteredAnimals;
    }*/
    /*public List<Animal> filterAnimalsByStatus(String status) {
        List<Animal> animals = animalRepository.findByStatus(status);
        if (animals.isEmpty()) {
            return new ArrayList<>(); // returnează o listă goală
        }
        return animals;
    }*/
    public List<Animal> filterAnimalsByStatus(String status) {
        List<Animal> animals = animalRepository.getAll(); // Obținem toate animalele din repository
        List<Animal> filteredAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            if (animal.getStatus().equals(status)) {
                filteredAnimals.add(animal);
            }
        }

        if (filteredAnimals.isEmpty()) {
            throw new BusinessLogicException("No animals found with the status: " + status);
        }

        return filteredAnimals;
    }



}
