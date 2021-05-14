//package by.itacademy.shop.dao;
//import by.itacademy.shop.api.dao.CategoryDao;
//import by.itacademy.shop.api.dao.PhotoDao;
//import by.itacademy.shop.api.dao.ProductDao;
//import by.itacademy.shop.api.dao.ProviderDao;
//import by.itacademy.shop.entities.Product;
//import by.itacademy.shop.entities.Provider;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ProviderDaoImplTest {
//    @Test
//    public void create(){//can for detach
//        ProviderDao providerDao=new ProviderDaoImpl();
//        Provider provider= Provider.builder()
//                .id(200L)
//                .name("some provider create")
//                .build();
//        providerDao.create(provider);
//    //--------------------
//        ProductDao productDao=new ProductDaoImpl();
//        CategoryDao categoryDao=new CategoryDaoImpl();
//        PhotoDao photoDao =new PhotoDaoImpl();
//
//        Map<String,String> map=new HashMap<>();
//        Map<String,String> attr=new HashMap<>();
//        attr.put("some","test");
//        map.put("RU","TEST PRODUCT CREATE");
//        Product product= Product.builder()
//                .id(200L)
//                .category(categoryDao.find(2L))
//                .photo(photoDao.find(1L))
//                .price(2.5)
//                .provider(providerDao.find(200L))
//                .quantityInStorage(2)
//                .shortDescription(map)
//                .attributes(attr)
//                .build();
//        productDao.create(product);
//    }
//    @Test
//    public void find(){//can for detach
//        ProviderDao providerDao=new ProviderDaoImpl();
//        System.out.println(providerDao.find(200L));
//    }
//    @Test
//    public void update() {//can't  for detach
//        ProviderDao providerDao=new ProviderDaoImpl();
//        Provider provider=providerDao.find(200L);
//        provider.setName("some provider update");
//        providerDao.update(provider);
//    }
//    @Test
//    public void delete(){//can't  for detach
//        ProviderDao providerDao=new ProviderDaoImpl();
//        Provider provider=providerDao.find(200L);
//        providerDao.delete(provider);
//    }
//    @Test
//    public void findAll(){
//        ProviderDao providerDao=new ProviderDaoImpl();
//        providerDao.findAll().stream().forEach(System.out::println);
//    }
//}
