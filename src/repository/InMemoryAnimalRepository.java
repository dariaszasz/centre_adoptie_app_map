package repository;

import models.Tier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAnimalRepository implements IAnimalRepository {
    private List<Tier> animals = new ArrayList<>();

    @Override
    public List<Tier> getAllAnimals() {
        return animals;
    }

    @Override
    public Tier getAnimalById(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addAnimal(Tier animal) {
        animals.add(animal);
    }

    @Override
    public void removeAnimal(int id) {
        animals.removeIf(animal -> animal.getId() == id);
    }

    @Override
    public void updateAnimalStatus(int id, String status) {
        Tier animal = getAnimalById(id);
        if (animal != null) {
            animal.setStatus(status);
        }
    }

    @Override
    public Optional<Tier> findById(int id) {
        return animals.stream()
                .filter(animal -> animal.getId() == id)
                .findFirst();
    }
}


