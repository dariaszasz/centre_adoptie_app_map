package repository;

import models.Tier;
import java.util.List;
import java.util.Optional;

public interface IAnimalRepository {
    List<Tier> getAllAnimals();
    Tier getAnimalById(int id);
    void addAnimal(Tier animal);
    void removeAnimal(int id);
    void updateAnimalStatus(int id, String status);

    Optional<Tier> findById(int id);
}
