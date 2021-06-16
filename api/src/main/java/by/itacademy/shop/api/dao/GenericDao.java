package by.itacademy.shop.api.dao;

import by.itacademy.shop.entities.GenericEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

// CRUD implementation
public interface GenericDao<T extends GenericEntity<Long>> {
    T create(T entity) throws JsonProcessingException;

    T find(long id);

    void update(T entity);

    void delete(T entity);


    List<T> findAll();
}
