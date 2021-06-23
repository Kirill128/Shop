package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.entities.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getParentCategories() {
        Query query=entityManager.createQuery("From Category c_21 where c_21.parentCategory=null ");
        return query.getResultList();
    }

    @Override
    public List<Category> getSubcategories() {
        Query query=entityManager.createQuery("From Category c_21 where c_21.parentCategory!=null ");
        return query.getResultList();
    }
}
