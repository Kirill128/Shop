package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.GenericDao;
import by.itacademy.shop.entities.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDaoImpl<T extends GenericEntity<Long>> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> classForFind;

    protected GenericDaoImpl(Class<T> classForFind){
        this.classForFind=classForFind;
    }
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T find(long id) {
        return entityManager.find(this.getClassForFind(),id);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }


    public List<T> findAll() {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq=cb.createQuery(getClassForFind());
        cq.from(getClassForFind());
        return entityManager.createQuery(cq).getResultList();
    }

    public Class<T> getClassForFind() {
        return classForFind;
    }
}
