package controller;

import models.Tier;
import models.Tierart;
import service.AnimalService;

import java.util.List;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // function to see the available animals
    public List<Tier> viewAllAnimals() {
        return animalService.getAllAnimals();
    }

    //function to add an animal 
    public void addAnimal(Tier animal) {
        animalService.addAnimal(animal);
    }

    // function to delete an animal
    public void removeAnimal(int id) {
        animalService.removeAnimal(id);
    }

    //function to change the status
    public void updateAnimalStatus(int id, String status) {
        animalService.updateAnimalStatus(id, status);
    }

    // function to adopt
    public void adoptAnimal(int animalId) {
        Tier animal = animalService.getAnimalById(animalId);
        if (animal != null) {
            // logic of adoption - changing the status of an animal to "adopted"
            animal.setStatus("adoptat");
            updateAnimalStatus(animalId, "adoptat");
            System.out.println("Animalul a fost adoptat!");
        } else {
            System.out.println("Animalul nu a fost găsit.");
        }
    }
}
