package test.by.itacademy.shop.services; 

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.dao.ProductDaoImpl;
import by.itacademy.shop.locale.Lang;
import by.itacademy.shop.services.ProductServiceImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.FileInputStream;

/** 
* ProductServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 27, 2021</pre> 
* @version 1.0 
*/ 
public class ProductServiceImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createProduct(ProductDto product) 
* 
*/ 
@Test
public void testCreateProduct() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: find(long id) 
* 
*/ 
@Test
public void testFind() throws Exception { 
    ProductDao productDao=new ProductDaoImpl();
    ProductService productService=new ProductServiceImpl(productDao);
//    System.out.println(productService.find(1L));

} 

/** 
* 
* Method: update(ProductDto product) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delete(long id) 
* 
*/ 
@Test
public void testDelete() throws Exception {
}

/** 
* 
* Method: getAllProducts() 
* 
*/ 
@Test
public void testGetAllProducts() throws Exception {
    System.out.println("En"+Lang.EN.value);
    System.out.println("RU"+ Lang.RU.value);
}

/** 
* 
* Method: getLimitedProductsWithOffset(int pageNum, int pageSize) 
* 
*/ 
@Test
public void testGetLimitedProductsWithOffset() throws Exception {
//    ProductDao productDao=new ProductDaoImpl();
//    ProductService productService=new ProductServiceImpl(productDao);
//    productService.getLimitedProductsWithOffset(1,10);
}

/** 
* 
* Method: parseXLSOrXlSXFile(MultipartFile file) 
* 
*/ 
@Test
public void testParseXLSOrXlSXFile() throws Exception {
    ProductDao productDao=new ProductDaoImpl();
    ProductService productService=new ProductServiceImpl(productDao);
//    productService.parseXLSOrXlSXFile(null,new FileInputStream("/home/kirill/Programming/Shop/services/src/main/resources/ShopProducts.xlsx")).stream().forEach(System.out::println);
}


} 
