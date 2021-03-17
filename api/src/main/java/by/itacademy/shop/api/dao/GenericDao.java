package by.itacademy.shop.api.dao;

import by.itacademy.shop.entities.Entity;

import java.util.List;

// CRUD implementation
public interface GenericDao<T extends Entity<Long>> {
    T create(T entity);

    T find(long id);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();
}
