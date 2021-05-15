package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.dao.nativequeryhelper.NativeQueryStringBuilder;
import by.itacademy.shop.entities.Product;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

class ProductDaoImplTest {

    @Test
    public void create(){
//        ProductDao productDao=new ProductDaoImpl();
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
//        productDao.create(product);
    }
    @Test
    public void find(){
        ProductDao productDao=new ProductDaoImpl(new CategoryDaoImpl(),
                                                new ProviderDaoImpl(),
                                                new PhotoDaoImpl());
        Product product=productDao.find(1L);
        System.out.println(product);
    }
    @Test
    public void update(){
        ProductDao productDao=new ProductDaoImpl(new CategoryDaoImpl(),
                new ProviderDaoImpl(),
                new PhotoDaoImpl());
        Product product=productDao.find(101L);
        product.setPrice(1000.0);
        productDao.update(product);
    }
    @Test
    public void delete(){
        ProductDao productDao=new ProductDaoImpl(new CategoryDaoImpl(),
            new ProviderDaoImpl(),
            new PhotoDaoImpl());
        Product product=productDao.find(200L);
        productDao.delete(product);
    }
    @Test
    public void findAll(){
        ProductDao productDao=new ProductDaoImpl(new CategoryDaoImpl(),
                new ProviderDaoImpl(),
                new PhotoDaoImpl());
        productDao.findAll().stream().forEach(System.out::println);
    }

    @Test
    public void nativeQueryBuilderTest(){
        NativeQueryStringBuilder nativeQueryBuilder=new NativeQueryStringBuilder()
                .select("*")
                .from("product")
                .join("left","category","product.category_id=category.id")
                .where("product.category_id > '3'")
                .limitOffset(20,0);
        System.out.println(nativeQueryBuilder.build());

    }


}