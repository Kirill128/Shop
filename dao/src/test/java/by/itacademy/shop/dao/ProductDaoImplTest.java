package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.config.TestProductDaoConfig;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestProductDaoConfig.class,
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class ProductDaoImplTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private JpaTransactionManager transactionManager;

    private static final String descrString="ОПИСАНИЕ ДЛЯ ПОИСКА";
    private static final Lang lang = Lang.RU;

    private static final long id=2L;
    private static final Map<String,String> attributes=null;
    private static final int quantityInStorage=4;
    private static final Map<String,String> shortDescription=Map.of(lang.value,descrString);
    private static final double price=4.0;
    private static final String barcode="some barcode";
    private static final Provider provider=null;
    private static final Photo photo=null;

    private static final String categoryRussianTitle="Описание Категории";
    private static final Map<String,String> categoryTitleMap=Map.of(lang.value,categoryRussianTitle);
    private static final Category category= Category.builder()
            .id(5L)
            .title(categoryTitleMap)
            .build();

//    @Before
//    @Test
//    public void findAll(){
//        productDao.findAll().forEach(System.out::println);
//    }
//
//    @org.junit.jupiter.api.Test
//    public void create() throws JsonProcessingException {
//
//        Product product=createFullProductEntity(id);
//        productDao.create(product);
//    }
    @org.junit.jupiter.api.Test
    public void find() throws JsonProcessingException {
        Product product=createFullProductEntity(1);

        productDao.find(product.getId());
    }

    //-------------------
    private static void assertEqualsProductAndProduct(Product product,Product anotherProduct) throws JsonProcessingException {
        Assertions.assertEquals(product.getId(),anotherProduct.getId());
        Assertions.assertEquals(product.getQuantityInStorage(),anotherProduct.getQuantityInStorage());
        Assertions.assertEquals(new ObjectMapper().writeValueAsString(product.getShortDescription()),
                new ObjectMapper().writeValueAsString(anotherProduct.getShortDescription()));
        Assertions.assertEquals(product.getPrice(),anotherProduct.getPrice());
        Assertions.assertEquals(product.getBarcode(),anotherProduct.getBarcode());
        Assertions.assertEquals(product.getCategory().getId(),anotherProduct.getCategory().getId());
        Assertions.assertEquals(product.getPhoto(),anotherProduct.getPhoto());
        Assertions.assertEquals(product.getProvider(),anotherProduct.getProvider());
        Assertions.assertEquals(product.getAttributes(),anotherProduct.getAttributes());
    }
    public static Product createFullProductEntity(long entityId){
        Product product=createProductEntityWithoutId();
        product.setId(entityId);
        return product;

    }
    public static Product createProductEntityWithoutId(){
        return Product.builder()
                .attributes(attributes)
                .quantityInStorage(quantityInStorage)
                .shortDescription(shortDescription)
                .price(price)
                .barcode(barcode)
                .category(category)
                .photo(photo)
                .provider(provider)
                .build();
    }

//

//
//    @org.junit.jupiter.api.Test
//    public void update() {
//        Product product=createFullProductEntity(id);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void delete() {
//        Product product=createFullProductEntity(id);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void findAll() {
//        Product product=createFullProductEntity(id);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void getProductsPageByCriteria() {
//        Product product=createFullProductEntity(id);
//
//    }
}