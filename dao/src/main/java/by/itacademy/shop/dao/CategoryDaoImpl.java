package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.entities.Category;

public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
    protected CategoryDaoImpl() {
        super(Category.class);
    }
}
