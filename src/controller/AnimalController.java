package src.controller;

import service.AnimalService;
import models.Tier;
import models.Tierart;

import java.util.List;
import java.util.Scanner;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Funcția pentru vizualizarea animalelor disponibile
    public List<Tier> viewAllAnimals() {
        return animalService.getAllAnimals();
    }

    // Funcția pentru a adăuga un animal
    public void addAnimal(Tier animal) {
        animalService.addAnimal(animal);
    }

    // Funcția pentru a șterge un animal
    public void removeAnimal(int id) {
        animalService.removeAnimal(id);
    }

    // Funcția pentru a schimba statusul unui animal
    public void updateAnimalStatus(int id, String status) {
        animalService.updateAnimalStatus(id, status);
    }

    // Funcția pentru a adopta un animal
    public void adoptAnimal(int animalId) {
        Tier animal = animalService.getAnimalById(animalId);
        if (animal != null) {
            // Logica de adoptie - schimbarea statusului animalului in "adoptat"
            animal.setStatus("adoptat");
            updateAnimalStatus(animalId, "adoptat");
            System.out.println("Animalul a fost adoptat!");
        } else {
            System.out.println("Animalul nu a fost găsit.");
        }
    }
}