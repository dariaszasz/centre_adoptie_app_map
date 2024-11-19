package repository;

import models.BaseEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T extends BaseEntity> implements IRepository<T> {
    private String fileName;

    public FileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(T entity) {
        List<T> entities = getAll();
        entities.add(entity);
        saveToFile(entities);
    }

    @Override
    public void update(T entity) {
        List<T> entities = getAll();
        entities.removeIf(e -> ((BaseEntity) e).getId() == ((BaseEntity) entity).getId());
        entities.add(entity);
        saveToFile(entities);
    }

    @Override
    public void delete(int id) {
        List<T> entities = getAll();
        entities.removeIf(entity -> ((BaseEntity) entity).getId() == id);
        saveToFile(entities);
    }

    @Override
    public T getById(int id) {
        return getAll().stream()
                .filter(entity -> ((BaseEntity) entity).getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            entities = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File might be empty or not exist, we handle the error by returning an empty list
        }
        return entities;
    }

    private void saveToFile(List<T> entities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int generateUniqueId() {
        List<T> allEntities = getAll(); // Citește toate entitățile din fișier
        return allEntities.stream()
                .mapToInt(entity -> entity.getId()) // Obține ID-urile
                .max()
                .orElse(0) + 1; // Dacă nu există entități, începe de la 1
    }

}


