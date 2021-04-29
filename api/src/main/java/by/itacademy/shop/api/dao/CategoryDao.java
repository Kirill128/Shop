package by.itacademy.shop.api.dao;

import by.itacademy.shop.entities.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category>{
    List<Category> getParentCategories();
}
