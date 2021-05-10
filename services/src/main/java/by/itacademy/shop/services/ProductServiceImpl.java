package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
import by.itacademy.shop.utils.ExelFilesWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    //---------------------------------Users----------------------------------------------------

    @Override
    public GuestProductDto find(long id, Lang lang) {
        return ProductMapper.mapProductToGuestProductDto(this.productDao.find(id),lang);
    }

    @Override
    public List<GuestProductDto> getAllProducts(Lang lang) {
        return ProductMapper.mapProductsToGuestProductDtos(this.productDao.findAll(),lang);


    }
    @Override
    public List<GuestProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize, Lang lang) {
        return ProductMapper.mapProductsToGuestProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize),lang);
    }

    //----------------------------------Admin ---------------------------------------------------


    @Override
    public List<ProductDto> getAllProducts() throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.findAll());
    }

    @Override
    public List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.getLimitedProductsWithOffset(pageNum,pageSize));
    }
    @Override
    public void update(ProductDto product) throws JsonProcessingException {
        this.productDao.update(ProductMapper.mapProductDtoToProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public ProductDto createProduct(ProductDto product) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.create(ProductMapper.mapProductDtoToProduct(product)));
    }

    @Override
    public List<ProductDto> createProducts(List<ProductDto> productDtos) throws JsonProcessingException {
        List<Product> savedProducts=new ArrayList<>(productDtos.size());
        for(ProductDto productDto : productDtos){
            savedProducts.add(this.productDao.create(ProductMapper.mapProductDtoToProduct(productDto)));
        }
        return ProductMapper.mapProductsToProductDtos(savedProducts);
    }

    @Override
    public ProductDto findFullInfo(long id) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.find(id));
    }

    @Override
    public List<ProductDto> parseXLSOrXlSXFile(MultipartFile file,Lang lang) throws IOException {
        return ExelFilesWorker.parseXLSOrXlSXFile(file,lang);
    }


}
