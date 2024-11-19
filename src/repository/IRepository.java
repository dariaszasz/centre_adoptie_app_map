package repository;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T extends Serializable> {
    void add(T entity);
    void update(T entity);
    void delete(int id);
    T getById(int id);
    List<T> getAll();

    int generateUniqueId();
}

