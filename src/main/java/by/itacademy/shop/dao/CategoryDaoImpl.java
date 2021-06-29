package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.entities.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getParentCategories() {
//        Query query=entityManager.createQuery("select c_21 from Category as c_21 where c_21.parentCategory is null ");
//        return query.getResultList();
                CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq=cb.createQuery(Category.class);
        Root<Category> root=cq.from(Category.class);
        Predicate categoryPredicate=cb.isNull(root.get("parentCategory"));
        cq.where(categoryPredicate);

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Category> getSubcategories() {
//        Query query=entityManager.createQuery("select c_21 from Category as c_21 where c_21.parentCategory is not null ");
//        return query.getResultList();

        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq=cb.createQuery(Category.class);
        Root<Category> root=cq.from(Category.class);
        Predicate categoryPredicate=cb.isNotNull(root.get("parentCategory"));
        cq.where(categoryPredicate);

        return entityManager.createQuery(cq).getResultList();
    }
}
