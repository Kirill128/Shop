package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductOrderDao;
import by.itacademy.shop.api.dto.user.ProductOrderDto;
import by.itacademy.shop.api.services.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {
    private ProductOrderDao productOrderDao;

    public ProductOrderServiceImpl(ProductOrderDao productOrderDao) {
        this.productOrderDao = productOrderDao;
    }

    @Override
    public ProductOrderDto createUser(ProductOrderDto user) {
        return null;
    }

    @Override
    public ProductOrderDto find(long id) {
        return null;
    }

    @Override
    public void update(ProductOrderDto user) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<ProductOrderDto> getAllProductOrders() {
        return null;
    }
}
