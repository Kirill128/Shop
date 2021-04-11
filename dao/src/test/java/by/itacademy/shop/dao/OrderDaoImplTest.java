package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.OrderDao;
import by.itacademy.shop.api.dao.ProductOrderDao;
import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.Status;
import liquibase.pro.packaged.U;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {
    @Test
    public void create(){
        OrderDao orderDao=new OrderDaoImpl();
        UserDao userDao=new UserDaoImpl();
        Order order=Order.builder()
                .id(100L)
                .price(10.0)
                .submitTime(LocalDateTime.parse("2017-11-15T08:22:12"))
                .status(Status.AVAILABLE_IN_STORAGE)
                .user(userDao.find(1L))
                .build();
        orderDao.create(order);
    }
    @Test
    public void find(){
        OrderDao orderDao=new OrderDaoImpl();
        Order order=orderDao.find(100L);
        System.out.println(order);
    }
    @Test
    public void update() {
        OrderDao orderDao=new OrderDaoImpl();
        Order order=orderDao.find(100L);
        order.setStatus(Status.AVAILABLE_IN_STORAGE);
        orderDao.update(order);
    }
    @Test
    public void delete(){
        OrderDao orderDao=new OrderDaoImpl();
        Order order=orderDao.find(100L);
        orderDao.delete(order);
    }

    @Test
    public void findAll(){
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.findAll().stream().forEach(System.out::println);
    }

}