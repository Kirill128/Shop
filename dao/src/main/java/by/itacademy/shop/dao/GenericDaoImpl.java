package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.GenericDao;
import by.itacademy.shop.entities.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDaoImpl<T extends GenericEntity<Long>> implements GenericDao<T> {
    protected EntityManagerFactory entityManagerFactory;
//    protected EntityManager entityManager;

    private Class<T> classForFind;

    protected GenericDaoImpl(Class<T> classForFind){
        this.emfCr();
        this.classForFind=classForFind;
    }
    public T create(T entity) {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
        return entity;
    }

    public T find(long id) {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        T result=entityManager.find(this.getClassForFind(),id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return result;
    }

    public void update(T entity) {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public void delete(T entity) {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(entity);
//        this.entityManager.flush();
        entityManager.getTransaction().commit();

        entityManager.close();
    }
    public void delete(long id) {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        T result=entityManager.find(this.getClassForFind(),id);
        entityManager.remove(result);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<T> findAll() {
        this.setUp();
        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq=cb.createQuery(getClassForFind());
        Root<T> root=cq.from(getClassForFind());

        entityManager.close();
        return entityManager.createQuery(cq).getResultList();
    }

    private void emfCr(){
        this.entityManagerFactory= Persistence.createEntityManagerFactory("by.itacademy.shop.hibernate.jpa");
    }

    private void setUp(){

//        this.entityManager=this.entityManagerFactory.createEntityManager();
    }
    private void tearDown(){
//        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public Class<T> getClassForFind() {
        return classForFind;
    }
}
