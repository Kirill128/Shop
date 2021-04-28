package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProductDaoImplTest {

    @Test
    @Transactional
    public void create(){
        ProductDao productDao=new ProductDaoImpl();
        CategoryDao categoryDao=new CategoryDaoImpl();
        PhotoDao photoDao =new PhotoDaoImpl();
        ProviderDao providerDao=new ProviderDaoImpl();

        Map<String,String> map=new HashMap<>();
        Map<String,String> attr=new HashMap<>();
        attr.put("some","test");
        map.put("RU","TEST PRODUCT CREATE");
        Product product= Product.builder()
                .id(100L)
                .category(categoryDao.find(2L))
                .photo(photoDao.find(1L))
                .price(2.5)
                .provider(providerDao.find(1L))
                .quantityInStorage(2)
                .shortDescription(map)
                .attributes(attr)
                .build();
        productDao.create(product);
    }
    @Test
    public void find(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(1L);
        System.out.println(product);
    }
    @Test
    public void update(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(101L);
        product.setPrice(1000.0);
        productDao.update(product);
    }
    @Test
    public void delete(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(200L);
        productDao.delete(product);
    }
    @Test
    public void findAll(){
        ProductDao productDao=new ProductDaoImpl();
        productDao.findAll().stream().forEach(System.out::println);
    }

    @Test
    public void getLimitedProductsWithOffset() {
        ProductDao productDao=new ProductDaoImpl();
        List<Product> productList=productDao.getLimitedProductsWithOffset(1,10);
        productList.stream().forEach(System.out::println);

    }


}