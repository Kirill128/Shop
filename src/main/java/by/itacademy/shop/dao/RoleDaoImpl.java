package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq=cb.createQuery(Role.class);

        Root<Role> role=cq.from(Role.class);

        Predicate namePredicate=cb.equal(role.get("name"),name);

        cq.where(namePredicate);

        TypedQuery<Role> query=entityManager.createQuery(cq);

        return query.getSingleResult();
    }
}
