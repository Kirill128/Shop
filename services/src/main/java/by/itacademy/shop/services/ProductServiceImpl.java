package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.utilenum.Lang;
import by.itacademy.shop.utils.ExelFilesWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public SimplePage<GuestProductDto> getProductsPageByCriteria(ProductSearchCriteria searchCriteria) {
        if(searchCriteria.getPartOfName()!=null){
            searchCriteria.setPartsOfName(Arrays.asList(searchCriteria.getPartOfName().split("\\s+")));
        }
        SimplePage<Product> oldPage=this.productDao.getProductsPageByCriteria(searchCriteria);
        SimplePage<GuestProductDto> newPage=new SimplePage<>();
        newPage.setCountInDb(oldPage.getCountInDb());
        newPage.setResults(ProductMapper.mapProductsToGuestProductDtos(oldPage.getResults(),searchCriteria.getLang()));
        return newPage;
    }

    //----------------------------------Admin ---------------------------------------------------

    @Override
    public List<AdminProductDto> getAllProducts() throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(this.productDao.findAll());
    }

    @Override
    public SimplePage<AdminProductDto> getProductsPageByCriteriaAdmin(ProductSearchCriteria searchCriteria) throws JsonProcessingException {
        SimplePage<Product> daoPage=this.productDao.getProductsPageByCriteria(searchCriteria);
        SimplePage<AdminProductDto> newPage=new SimplePage<>();
        newPage.setResults(ProductMapper.mapProductsToProductDtos(daoPage.getResults()));
        newPage.setCountInDb(daoPage.getCountInDb());

        return newPage;
    }
    @Override
    public void update(AdminProductDto product) throws JsonProcessingException {
        this.productDao.update(ProductMapper.mapProductDtoToProduct(product));
    }

    @Override
    public void delete(long id) {
        Product product=this.productDao.find(id);
        this.productDao.delete(product);
    }

    @Override
    public AdminProductDto createProduct(AdminProductDto product) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.create(ProductMapper.mapProductDtoToProduct(product)));
    }

    @Override
    public List<AdminProductDto> createProducts(List<AdminProductDto> productDtos) throws JsonProcessingException {
        List<Product> savedProducts=new ArrayList<>(productDtos.size());
        for(AdminProductDto productDto : productDtos){
            savedProducts.add(this.productDao.create(ProductMapper.mapProductDtoToProduct(productDto)));
        }
        return ProductMapper.mapProductsToProductDtos(savedProducts);
    }

    @Override
    public AdminProductDto findFullInfo(long id) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.find(id));
    }

    @Override
    public List<AdminProductDto> parseXLSOrXlSXFile(MultipartFile file, Lang lang) throws IOException {
        return ExelFilesWorker.parseXLSOrXlSXFile(file,lang);
    }

}
