package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.entities.Category;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class CategoryDaoImplTest {


    @Test
    public void create(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        Map<String,String> attr=new HashMap<>();
        attr.put("some","test");
        Category category= Category.builder()
                .parentCategory(categoryDao.find(1L))
                .title(attr)
                .build();
        categoryDao.create(category);
    }

    @Test
    public void find(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        Category category=categoryDao.find(200L);
        for(Category category1: category.getSubCategories()){
            System.out.println("//////////////////////////////////////////////");
            System.out.println(category1);
        }
    }


    @Test
    public void update() {
        CategoryDao categoryDao=new CategoryDaoImpl();
        Category category= Category.builder()
                .id(200L)
                .parentCategory(categoryDao.find(5L))
                .build();
        categoryDao.update(category);
    }

    @Test
    @BeforeAll
    public static void delete(){
        System.out.println("DELETE STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        CategoryDao categoryDao=new CategoryDaoImpl();
        Category category =categoryDao.find(23L);
        categoryDao.delete(category);
        System.out.println("DELETE Ended !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Test
    public  void findAll(){
        System.out.println("findall STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.findAll().stream().forEach(System.out::println);
        System.out.println("findall Ended !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Test
    @AfterEach
    public  void getParentCategories() {
        System.out.println("ParentCategories STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.getParentCategories().stream().forEach(System.out::println);
        System.out.println("ParentCategories Ended !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}