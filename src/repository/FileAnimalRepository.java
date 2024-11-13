package repository;

import models.Tier;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileAnimalRepository implements IAnimalRepository {
    // numele fisierului in care vor fi stocate datele animalului
    private static final String FILE_NAME = "animale.txt";

    // Lista interna care stocheaza toate animalele
    private List<Tier> animals;

    // Constructorul clasei care initializeaza lista de animale si incarca animalele din file
    public FileAnimalRepository() {
        this.animals = new ArrayList<>();
        // apeleaza metoda care incarca animalele din file
        loadAnimalsFromFile();
    }

    // Metoda care returneaza lista completa de animale
    @Override
    public List<Tier> getAllAnimals() {
        return animals;
    }

    // Metoda pt a adauga un animal
    @Override
    public void addAnimal(Tier animal) {
        animals.add(animal); // adauga animalul in lista
        saveAnimalsToFile(); // salveaza animalele in fisier
    }

    // Metoda pt a sterge un animal din lista pe baza ID-ului
    @Override
    public void removeAnimal(int id) {
        // elimina animalul din lista daca ID-ul corespunde cu cel specificat
        animals.removeIf(animal -> animal.getId() == id);
        saveAnimalsToFile();// salveaza lista actualizata in file
    }

    // Metoda pt a actualiza statusul unui animal pe baza Id-ului
    @Override
    public void updateAnimalStatus(int id, String status) {
        // parcurge lista de animale
        for (Tier animal : animals) {
            // daca Id-ul corespunde se seteaza un nou status pentru acel animal
            if (animal.getId() == id) {
                animal.setStatus(status);
                break; // opreste bucla la gasirea animalului
            }
        }
        saveAnimalsToFile(); // salveaza lista actualizata in fisier
    }

    // Metoda care returneaza un animal pe baza ID-ului, impachetat intr-un Optional
    @Override
    public Optional<Tier> findById(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findFirst();
    }

    // Metoda care returneaza un animal pe baza ID-ului sau null daca nu este gasit
    @Override
    public Tier getAnimalById(int id) {
        for (Tier animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    // Incarca animalele din fisier in lista
    private void loadAnimalsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            // citeste obiectul din fisier si il converteste in lista de animale
            animals = (List<Tier>) ois.readObject();
        } catch (FileNotFoundException e) {
            // daca fisierul nu exista afiseaza un mesaj
            System.out.println("The file does not exist yet. A new one will be created.");
        } catch (IOException | ClassNotFoundException e) {
            // in caz de exceptie afiseaza o urma a exceptiei
            e.printStackTrace();
        }
    }

    // Metoda care salveaza lista de annimale in fisier
    private void saveAnimalsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            // scrie lista de animale in fisier
            oos.writeObject(animals);
        } catch (IOException e) {
            e.printStackTrace(); // in caz de eroare afiseaza o urma a exceptiei
        }
    }
}
