package service;

import models.Tier;
import repository.IAnimalRepository;

import java.util.List;
import java.util.Optional;

public class AnimalService {
    private IAnimalRepository animalRepository;

    public AnimalService(IAnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Tier> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

    public void addAnimal(Tier animal) {
        animalRepository.addAnimal(animal);
    }

    public void removeAnimal(int id) {
        animalRepository.removeAnimal(id);
    }

    public void updateAnimalStatus(int id, String status) {
        animalRepository.updateAnimalStatus(id, status);
    }

    public Tier getAnimalById(int id) {
        Optional<Tier> animal = animalRepository.findById(id);
        return animal.orElse(null); // Dacă animalul nu există, returnează null
    }
}
