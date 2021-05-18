package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.User;
import by.itacademy.shop.utilenum.SortDirection;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public User findByEmail(String email) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq=cb.createQuery(User.class);

        Root<User> user=cq.from(User.class);

        Predicate emailPredicate=cb.equal(user.get("email"),email);

        cq.where(emailPredicate);

        TypedQuery<User> query=entityManager.createQuery(cq);

        return query.getSingleResult();
    }

    public SimplePage<Product> getProductsPageByCriteriaD(ProductSearchCriteria productSearchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        Predicate predicate = getPredicate(productSearchCriteria, root, criteriaBuilder);
        cq.where(predicate);
        setOrder(productSearchCriteria, cq, root, criteriaBuilder);

        TypedQuery<Product> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(productSearchCriteria.getPageNum() * productSearchCriteria.getPageSize());
        typedQuery.setMaxResults(productSearchCriteria.getPageSize());

        long employeesCount = getProductsCount(predicate, criteriaBuilder);

        return new SimplePage<>(typedQuery.getResultList(), employeesCount);
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria,
                                   Root<Product> productRoot,
                                   CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(productSearchCriteria.getPartOfName())) {
            predicates.add(
                    criteriaBuilder.like(productRoot.get("shortDescription"),
                            "{\"RU\":\"%" + productSearchCriteria.getPartOfName() + "%\"}")
            );
        }
        if (Objects.nonNull(productSearchCriteria.getCategoryId())) {
            predicates.add(
                    criteriaBuilder.equal(productRoot.get("category_id"), productSearchCriteria.getCategoryId())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ProductSearchCriteria searchCriteria,
                          CriteriaQuery<Product> criteriaQuery,
                          Root<Product> productRoot,
                          CriteriaBuilder criteriaBuilder) {
        if (Objects.isNull(searchCriteria.getSortDirection()) || Objects.isNull(searchCriteria.getSortBy())) return;

        if (searchCriteria.getSortDirection().equals(SortDirection.INCREASE)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(searchCriteria.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(searchCriteria.getSortBy())));
        }
    }

    private long getProductsCount(Predicate predicate, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
