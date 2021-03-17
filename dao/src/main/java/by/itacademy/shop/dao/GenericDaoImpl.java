package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.GenericDao;
import by.itacademy.shop.entities.Entity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDaoImpl<T extends Entity<Long>> implements GenericDao<T> {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private Class<T> classForFind;

    protected GenericDaoImpl(Class<T> classForFind){
        this.setUp();
        this.classForFind=classForFind;
    }
    @Transactional
    public T create(T entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    public T find(long id) {
        this.entityManager.getTransaction().begin();
        T result=this.entityManager.find(this.getClassForFind(),id);
        this.entityManager.getTransaction().commit();
        return result;
    }

    public void update(T entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
    }

    public List<T> findAll() {
        return null;
    }

    private void setUp(){
        this.entityManagerFactory= Persistence.createEntityManagerFactory("by.itacademy.shop.hibernate.jpa");
        this.entityManager=this.entityManagerFactory.createEntityManager();
    }
    private void tearDown(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public Class<T> getClassForFind() {
        return classForFind;
    }
}
