package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
}
