package by.itacademy.shop.dao.productsdao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.dao.GenericDaoImpl;
import by.itacademy.shop.dao.productsdao.jdbctemplate.ProductDaoImplJdbcTemplate;
import by.itacademy.shop.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Profile("jpql")
public class ProductDaoImplJpql extends GenericDaoImpl<Product> implements ProductDao {
    private static Logger logger = Logger.getLogger(ProductDaoImplJdbcTemplate.class);

    protected ProductDaoImplJpql() {
        super(Product.class);
    }
    @Override
    public SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria) {
        return null;//TODO: make if have time
    }


    @Override
    public Product create(Product entity) throws JsonProcessingException {
        return super.create(entity);

    }

    @Override
    public Product find(long id) {
        TypedQuery<Product> query=entityManager.createQuery("From Product pr WHERE pr.id=:id",Product.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void update(Product entity) {
        ObjectMapper mapper=new ObjectMapper();
        Query query=entityManager.createQuery("update Product pr SET pr.shortDescription=:shortDescr," +
                "pr.barcode=:barcode, pr.quantityInStorage=:quantity,pr.attributes=:attributes," +
                "pr.category=:category,pr.photo=:photo,pr.provider=:provider where pr.id=:id ");
        query.setParameter("id",entity.getId());
        query.setParameter("barcode",entity.getBarcode());
        query.setParameter("quantity",entity.getQuantityInStorage());
        query.setParameter("category",entity.getCategory());
        query.setParameter("photo",entity.getPhoto());
        query.setParameter("provider",entity.getProvider());
        try{
            query.setParameter("shortDescr",mapper.writeValueAsString(entity.getShortDescription()));
            query.setParameter("attributes",mapper.writeValueAsString(entity.getAttributes()));
        }catch (JsonProcessingException e){
            logger.error(e.getLocalizedMessage());
        }
        query.executeUpdate();
    }

    @Override
    public void delete(Product entity) {
        Query query=entityManager.createQuery("delete from Product pr where pr.id=:id ");
        query.setParameter("id",entity.getId());
        query.executeUpdate();
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product pr",Product.class).getResultList();
    }


}
