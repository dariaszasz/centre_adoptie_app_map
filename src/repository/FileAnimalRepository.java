package repository;

import models.Tier;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileAnimalRepository implements IAnimalRepository {
    private static final String FILE_NAME = "animale.txt";
    private List<Tier> animals;

    public FileAnimalRepository() {
        this.animals = new ArrayList<>();
        loadAnimalsFromFile();

    @Override
    public List<Tier> getAllAnimals() {
        return animals;
    }

    @Override
    public void addAnimal(Tier animal) {
        animals.add(animal);
        saveAnimalsToFile();
    }

    @Override
    public void removeAnimal(int id) {
        animals.removeIf(animal -> animal.getId() == id);
        saveAnimalsToFile();
    }

    @Override
    public void updateAnimalStatus(int id, String status) {
        for (Tier animal : animals) {
            if (animal.getId() == id) {
                animal.setStatus(status);
                break;
            }
        }
        saveAnimalsToFile();
    }

    @Override
    public Optional<Tier> findById(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findFirst();
    }

    @Override
    public Tier getAnimalById(int id) {
        for (Tier animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;  // Returnează null dacă nu găsești animalul
    }

    private void loadAnimalsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            animals = (List<Tier>) ois.readObject();  // Citește lista de animale din fișier
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist yet. A new one will be created.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveAnimalsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(animals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

