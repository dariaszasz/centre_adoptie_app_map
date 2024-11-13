package repository;

import models.Tier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAnimalRepository implements IAnimalRepository {
    // Lista care stocheaza toate obiectele de tip Tier in memorie
    private List<Tier> animals = new ArrayList<>();

    //Metoda care returneaza toate animalele din lista
    @Override
    public List<Tier> getAllAnimals() {
        return animals;
    }

    // Metoda care cauta un animal dupa id si il returneaza daca este gasit, sau null altfel
    @Override
    public Tier getAnimalById(int id) {
        // stream-ul cauta primul animal din lista care are id-ul corespunzator
        return animals.stream().filter(animal -> animal.getId() == id).findFirst().orElse(null);
    }

    // Metoda pentru a adauga un nou animal in lista
    @Override
    public void addAnimal(Tier animal) {
        animals.add(animal);
    }

    // Metoda pt a sterge un animal din lista dupa ID
    @Override
    public void removeAnimal(int id) {
        animals.removeIf(animal -> animal.getId() == id);
    }

    // Metoda care actualizeaza statusul unui animal dupa Id
    @Override
    public void updateAnimalStatus(int id, String status) {
        // gaseste animelul folosind ID-ul
        Tier animal = getAnimalById(id);
        if (animal != null) {
            // daca animanul a fost gasit ii seteaza un nou status
            animal.setStatus(status);
        }
    }

    // Metoda care gaseste un animal dupa ID
    // returneaza un Optional<Tier>
    @Override
    public Optional<Tier> findById(int id) {
        // stream-ul gaseste primul animal cu ID-ul specificat
        return animals.stream().filter(animal -> animal.getId() == id).findFirst();
        // Returneaza un `Optional<Tier>` care poate fi gol daca nu este gasit niciun animal
    }
}


