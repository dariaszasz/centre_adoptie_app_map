package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.BaseEntity;

public class InMemoryRepository<T extends BaseEntity> implements IRepository<T> {
    private List<T> entities = new ArrayList<>();

    // Adăugăm un animal sau altă entitate în repository
    @Override
    public void add(T entity) {
        // Verificăm dacă există deja entitatea cu același ID
        boolean exists = entities.stream()
                .anyMatch(e -> e.getId() == entity.getId());

        if (exists) {
            System.out.println("An entity with this ID already exists.");
        } else {
            entities.add(entity);
            System.out.println("Entity added successfully.");
        }
    }

    // Obținem o entitate după ID
    @Override
    public T getById(int id) {
        Optional<T> entity = entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        return entity.orElse(null);  // Dacă nu există, returnează null
    }

    // Obținem toate entitățile
    @Override
    public List<T> getAll() {
        return entities;
    }

    // Actualizăm o entitate
    @Override
    public void update(T entity) {
        // Căutăm entitatea în listă și o înlocuim cu una nouă
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == entity.getId()) {
                entities.set(i, entity);
                System.out.println("Entity updated successfully.");
                return;
            }
        }
        System.out.println("Entity with the given ID not found.");
    }

    // Ștergem o entitate după ID
    @Override
    public void delete(int id) {
        entities.removeIf(entity -> entity.getId() == id);
        System.out.println("Entity deleted successfully.");
    }

    private int currentId = 1; // Start ID from 1 or any desired number

    public int generateUniqueId() {
        return currentId++;
    }

}
