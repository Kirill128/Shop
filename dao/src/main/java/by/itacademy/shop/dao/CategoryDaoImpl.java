package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.utilenum.SortDirection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getParentCategories() {

        Query query=entityManager.createQuery("From Category c_21 where c_21.parentCategory=null ");
        List<Category> categoryList=query.getResultList();

        return categoryList;
    }

    @Override
    public List<Category> getSubcategories() {
        Query query=entityManager.createQuery("From Category c_21 where c_21.parentCategory!=null ");
        List<Category> categoryList=query.getResultList();

        return categoryList;
    }
}
