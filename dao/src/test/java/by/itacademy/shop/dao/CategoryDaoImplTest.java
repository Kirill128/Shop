package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.entities.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class CategoryDaoImplTest {

    @Order(value = 1)
    @Test
    public void create(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        Map<String,String> attr=new HashMap<>();
        attr.put("some","test");
        Category category= Category.builder()
                .id(200L)
                .parentCategory(categoryDao.find(1L))
                .title(attr)
                .build();
    }
    @Order(value = 2)
    @Test
    public void find(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        Category category=categoryDao.find(200L);
        System.out.println(category);
    }
    @Order(value = 3)
    @Test
    public void update() {
        CategoryDao categoryDao=new CategoryDaoImpl();
        Category category= Category.builder()
                .id(200L)
                .parentCategory(categoryDao.find(5L))
                .build();
        categoryDao.update(category);
    }
//    @Order(value = 5)
//    @Test
//    public  void delete(){
//        CategoryDao categoryDao=new CategoryDaoImpl();
//        Category category =categoryDao.find(200L);
//        categoryDao.delete(category);
//    }
    @Order(value = 4)
    @Test
    public void findAll(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.findAll().stream().forEach(System.out::println);
    }

}