package by.itacademy.shop.dao.productsdao.jdbctemplate;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.dao.GenericDaoImpl;
import by.itacademy.shop.util.ProductQueryBuilder;
import by.itacademy.shop.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@Profile("jdbc-template")
public class ProductDaoImplJdbcTemplate extends GenericDaoImpl<Product> implements ProductDao {
    private JdbcTemplate jdbcTemplate;
    private static Logger logger = Logger.getLogger(ProductDaoImplJdbcTemplate.class);

    protected ProductDaoImplJdbcTemplate(JdbcTemplate jdbcTemplate) {
        super(Product.class);
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria) {
    return new SimplePage<>(this.jdbcTemplate.query(
            ProductQueryBuilder.makeStringProductSearchNativeQuery(productSearchCriteria),
            new ProductRowMapper()));
    }

    @Override
    public Product create(Product entity) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        PGobject shortDescr=new PGobject();
        PGobject attributes=new PGobject();
        shortDescr.setType("jsonb");
        attributes.setType("jsonb");
        try{
            shortDescr.setValue(mapper.writeValueAsString(entity.getShortDescription()));
            attributes.setValue(mapper.writeValueAsString(entity.getAttributes()));
        }catch (SQLException sqlException){
            logger.error(sqlException.getLocalizedMessage());
        }

        this.jdbcTemplate.update("INSERT INTO product (short_description,barcode,"+
                "quantity_in_storage,price ,attributes ,category_id ,photo_id ,provider_id) " +
                "VALUES(?,?,?,?,?,?,?,?)",shortDescr,
                entity.getBarcode(),entity.getQuantityInStorage(),entity.getPrice(), attributes,
                (entity.getCategory()!=null)?entity.getCategory().getId() : null,
                (entity.getPhoto()!=null)?entity.getPhoto().getId() : null,
                (entity.getProvider()!=null)?entity.getProvider().getId() : null);
        return entity;
    }


}
