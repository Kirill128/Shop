//package by.itacademy.shop.dao;
//
//import by.itacademy.shop.api.dao.OrderDao;
//import by.itacademy.shop.api.dao.ProductDao;
//import by.itacademy.shop.api.dao.ProductOrderDao;
//import by.itacademy.shop.entities.Product;
//import by.itacademy.shop.entities.ProductOrder;
//import org.junit.jupiter.api.Test;
//
//
//class ProductOrderDaoImplTest {
//    @Test
//    public void create(){
//        ProductOrderDao productOrderDao=new ProductOrderDaoImpl();
//        OrderDao orderDao=new OrderDaoImpl();
//        ProductDao productDao=new ProductDaoImpl();
//
//        ProductOrder productOrder= ProductOrder.builder()
//                .id(100L)
//                .order(orderDao.find(1L))
//                .product(productDao.find(1L))
//                .price(100.0)
//                .quantity(12)
//                .build();
//        productOrderDao.create(productOrder);
//    }
//    @Test
//    public void find(){
//        ProductOrderDao productOrderDao=new ProductOrderDaoImpl();
//        ProductOrder productOrder=productOrderDao.find(100L);
//        System.out.println(productOrder);
//    }
//    @Test
//    public void update() {
//        ProductOrderDao productOrderDao=new ProductOrderDaoImpl();
//        ProductOrder productOrder=productOrderDao.find(100L);
//        productOrder.setQuantity(2000);
//        productOrderDao.update(productOrder);
//    }
//    @Test
//    public void delete(){
//        ProductOrderDao productOrderDao=new ProductOrderDaoImpl();
//        ProductOrder productOrder=productOrderDao.find(100L);
//        productOrderDao.delete(productOrder);
//    }
//
//    @Test
//    public void findAll(){
//        ProductOrderDao productOrderDao=new ProductOrderDaoImpl();
//        productOrderDao.findAll().stream().forEach(System.out::println);
//    }
//}