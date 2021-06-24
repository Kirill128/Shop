package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.config.TestProductDaoImpl;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityManager;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestProductDaoImpl.class,
        loader = AnnotationConfigContextLoader.class)
public class PhotoDaoImplTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductDao productDao;

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

    @BeforeAll
    public void setUp(){
    }

    public static Product createFullProductEntity(){
        return Product.builder()
                .id(id)
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

    @org.junit.jupiter.api.Test
    public void create() throws JsonProcessingException {
        Product product=createFullProductEntity();

        productDao.create(product);
        ArgumentCaptor<Product> argumentCaptor=ArgumentCaptor.forClass(Product.class);
        Mockito.verify(entityManager).persist(argumentCaptor.capture());
        assertEqualsProductAndProduct(product,argumentCaptor.getValue());
    }

    @org.junit.jupiter.api.Test
    public void find() throws JsonProcessingException {
        Product product=createFullProductEntity();

        productDao.find(product.getId());
        ArgumentCaptor<Product> argumentCaptor=ArgumentCaptor.forClass(Product.class);
        Mockito.verify(entityManager).persist(argumentCaptor.capture());
        assertEqualsProductAndProduct(product,argumentCaptor.getValue());
    }

    @org.junit.jupiter.api.Test
    public void update() {
        Product product=createFullProductEntity();

    }

    @org.junit.jupiter.api.Test
    public void delete() {
        Product product=createFullProductEntity();

    }

    @org.junit.jupiter.api.Test
    public void findAll() {
        Product product=createFullProductEntity();

    }

    @org.junit.jupiter.api.Test
    public void getProductsPageByCriteria() {
        Product product=createFullProductEntity();

    }

    //--
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
}