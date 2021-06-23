package by.itacademy.shop.dao.productsdao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.dao.GenericDaoImpl;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import by.itacademy.shop.util.ProductQueryBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
* statement , preparestatement , callable statement
* */
@Repository
@Profile("jdbc")
public class ProductDaoImplJdbc extends GenericDaoImpl<Product> implements ProductDao {
    private static Logger logger = Logger.getLogger(ProductDaoImplJdbc.class);
    private DataSource dataSource;

    public ProductDaoImplJdbc(DataSource dataSource) {
        super(Product.class);
        this.dataSource=dataSource;
    }

    @Override
    public SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria) {
        try(Connection connection=this.dataSource.getConnection()){
            Statement statement=connection.createStatement();
            if(statement.execute(ProductQueryBuilder.makeStringProductSearchNativeQuery(productSearchCriteria))){
                return new SimplePage<>(castListObjectsToListProducts(statement.getResultSet()));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product create(Product entity) {
        ObjectMapper mapper=new ObjectMapper();
        PGobject shortDescr=new PGobject();
        PGobject attributes=new PGobject();
        shortDescr.setType("jsonb");
        attributes.setType("jsonb");

        try(Connection connection=this.dataSource.getConnection()){

            shortDescr.setValue(mapper.writeValueAsString(entity.getShortDescription()));
            attributes.setValue(mapper.writeValueAsString(entity.getAttributes()));
            PreparedStatement statement=connection.prepareStatement("INSERT INTO product (short_description,barcode,"+
                    "quantity_in_storage,price ,attributes ,category_id ,photo_id ,provider_id) " +
                    "VALUES(?,?,?,?,?,?,?,?)");
            statement.setObject(1,shortDescr);
            statement.setString(2,entity.getBarcode());
            statement.setInt(3,entity.getQuantityInStorage());
            statement.setDouble(4,entity.getPrice());
            statement.setObject(5,attributes);
            if(entity.getCategory()!=null){
                statement.setLong(6,entity.getCategory().getId());
            }else{
                statement.setNull(6, Types.INTEGER);
            }
            if(entity.getPhoto()!=null) {
                statement.setLong(7, entity.getPhoto().getId());
            }else{
                statement.setNull(7, Types.INTEGER);
            }
            if(entity.getProvider()!=null) {
                statement.setLong(8,entity.getProvider().getId());
            }else{
                statement.setNull(8, Types.INTEGER);
            }
            statement.executeUpdate();
            return entity;
        }catch (SQLException | JsonProcessingException e){
            logger.error(e.getMessage());
        }
        return null;
    }




    private List<Product> castListObjectsToListProducts(ResultSet source) throws SQLException {
        List<Product> result = new LinkedList<>();
        while (source.next()) {
            try {
                result.add(this.buildProduct(source));
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        return result;
    }

    private Product buildProduct(ResultSet set) throws JsonProcessingException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = null;
        Provider provider = null;
        Photo photo = null;
            category = Category.builder()
                    .id(set.getLong("pcatid"))
                    .title((objectMapper.readValue(set.getString("ctitle"),
                            new TypeReference<HashMap<String, String>>() {
                            })))
                    .build();
            photo = Photo.builder()
                    .id(set.getLong("pphotoid"))
                    .url(set.getString("phurl"))
                    .build();
            provider = Provider.builder()
                    .id(set.getLong("pprovid"))
                    .name(set.getString("prname"))
                    .build();
        return Product.builder()
                .id(set.getLong("pid"))
                .shortDescription((objectMapper.readValue(set.getString("pshordescr"),
                        new TypeReference<HashMap<String, String>>() {
                        }))
                )
                .barcode(set.getString("pbar"))
                .quantityInStorage(set.getInt("pquant"))
                .price(set.getDouble("pprice"))
                .attributes((objectMapper.readValue(set.getString("pattr"),
                        new TypeReference<HashMap<String, String>>() {
                        })))
                .category(category)
                .photo(photo)
                .provider(provider)
                .build();
    }
}
