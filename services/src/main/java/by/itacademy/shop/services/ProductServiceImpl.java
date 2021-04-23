package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductDto createProduct(ProductDto product) {
        return ProductMapper.INSTANCE.mapProductDto(this.productDao.create(ProductMapper.INSTANCE.mapProduct(product)));
    }

    @Override
    public ProductDto find(long id) {
        return ProductMapper.INSTANCE.mapProductDto(this.productDao.find(id));
    }

    @Override
    public void update(ProductDto product) {
        this.productDao.update(ProductMapper.INSTANCE.mapProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return ProductMapper.INSTANCE.mapProductDtos(this.productDao.findAll());
    }

    @Override
    public List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) {
        return ProductMapper.INSTANCE.mapProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize));
    }
}
