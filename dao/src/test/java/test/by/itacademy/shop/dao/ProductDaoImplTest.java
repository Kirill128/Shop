package test.by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import test.by.itacademy.shop.config.ProductDaoConfigForTest;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ProductDaoConfigForTest.class,
        loader = AnnotationConfigContextLoader.class)
public class ProductDaoImplTest {
    @Autowired
    private ProductDao productDao;

    private static Product product;

    @BeforeAll
    public static void setupProduct(){
        Map<String,String> descr=new HashMap<>();
        descr.put("RU","ОПИСАНИЕ");
        product=Product.builder()
                .id(2L)
                .attributes(null)
                .quantityInStorage(4)
                .shortDescription(descr)
                .price(3.0)
                .barcode("22221")
                .category()
                .photo()
                .provider()
                .build();
    }
    @Test
    public void should
}
