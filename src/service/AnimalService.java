package service;

import models.Tier;
import repository.IAnimalRepository;

import java.util.List;
import java.util.Optional;

public class AnimalService {
    // Declaratia unui obiect de tip IAnimalRepository pt a interactiona cu datele despre animale
    private IAnimalRepository animalRepository;

    // Constructorul clasei AnimalService care primeste un obiect de tip IAnimalRepository
    // si il foloseste pt a efectua operatii asupra animalelor
    public AnimalService(IAnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    // Metoda pt a obtine toate animalele din repository (de exemplu o lista de animale)
    public List<Tier> getAllAnimals() {
        // Apeleaza metoda getAllAnimals din repository pt a returna lista de animale
        return animalRepository.getAllAnimals();
    }

    // Metoda pentru a adauga un animal in repository
    public void addAnimal(Tier animal) {
        // Apeleaza metoda addAnimal din repository pt a adauga animalul la lista din repository
        animalRepository.addAnimal(animal);
    }

    // Metoda pt a sterge un animal din repository pe baza ID-ului
    public void removeAnimal(int id) {
        animalRepository.removeAnimal(id);
    }

    // Metoda pt a actualiza statusul unui animal pe baza ID-ului
    public void updateAnimalStatus(int id, String status) {
        animalRepository.updateAnimalStatus(id, status);
    }

    // Metoda pt a obtine un animal dupa ID
    public Tier getAnimalById(int id) {
        Optional<Tier> animal = animalRepository.findById(id);
        // Daca animalul exista il returneaza
        // Daca nu exista (Optional este gol) returneaza null
        return animal.orElse(null);
    }
}
