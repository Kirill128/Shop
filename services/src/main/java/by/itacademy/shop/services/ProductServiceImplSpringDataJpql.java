package by.itacademy.shop.services;

import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.dao.productsdao.ProductDaoSpringDataJpqlWithGraph;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.specification.ProductSpecification;
import by.itacademy.shop.utilenum.SortDirection;
import by.itacademy.shop.utils.ExelFilesWorker;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Profile("spring-data-jpql")
public class ProductServiceImplSpringDataJpql implements ProductService {
    private ProductDaoSpringDataJpqlWithGraph productDao;

    public ProductServiceImplSpringDataJpql(ProductDaoSpringDataJpqlWithGraph productDao) {
        this.productDao = productDao;
    }


    @Override
    public List<GuestProductDto> getAllProducts(Lang lang) {
        return ProductMapper.mapProductsToGuestProductDtos(
                StreamSupport.stream(this.productDao.findAllProd().spliterator(),false)
                        .collect(Collectors.toList()),lang);
    }

    @Override
    public SimplePage<GuestProductDto> getProductsPageByCriteria(ProductSearchCriteria searchCriteria) {
        searchCriteria.setPageNum(Math.max(searchCriteria.getPageNum(), 1));
        if(searchCriteria.getPartOfName()!=null){
            searchCriteria.setPartsOfName(Arrays.asList(searchCriteria.getPartOfName().split("\\s+")));
        }
        String sortBy=(searchCriteria.getSortBy()==null) ? "price" : searchCriteria.getSortBy();

        SimplePage<Product> productPage=new SimplePage<>(this.productDao.findAllProd(
                Specification.where(ProductSpecification.categoryIdIs(searchCriteria))
                        .and(ProductSpecification.partsOfNameLikeCriteria(searchCriteria)),
                PageRequest.of(searchCriteria.getPageNum()-1,searchCriteria.getPageSize(),
                        (searchCriteria.getSortDirection()== SortDirection.INCREASE) ?
                                Sort.Direction.ASC : Sort.Direction.DESC ,
                        sortBy)).toList());
        SimplePage<GuestProductDto> dtoPage=new SimplePage<>();

        dtoPage.setNextPageNum(this.getNextPage(searchCriteria,productPage));
        dtoPage.setPreviousPageNum(this.getPreviousPage(searchCriteria));
        dtoPage.setCurrentPageNum(searchCriteria.getPageNum());
        dtoPage.setResults(ProductMapper.mapProductsToGuestProductDtos(productPage.getResults(),searchCriteria.getLang()));

        return dtoPage;
    }
    private int getNextPage(ProductSearchCriteria searchCriteria,SimplePage<Product> productPage){
        if(productPage.getResults().size() < searchCriteria.getPageSize()){
            return searchCriteria.getPageNum();
        }
        return searchCriteria.getPageNum()+1;
    }
    private int getPreviousPage(ProductSearchCriteria searchCriteria){
        if(searchCriteria.getPageNum()>1){
            return searchCriteria.getPageNum()-1;
        }
        return 1;
    }


    @Override
    public GuestProductDto find(long id, Lang lang) {
        return ProductMapper.mapProductToGuestProductDto(this.productDao.findById(id).get(),lang);
    }

    @Override
    public AdminProductDto createProduct(AdminProductDto product) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.save(ProductMapper.mapProductDtoToProduct(product)));

    }

    @Override
    public AdminProductDto findFullInfo(long id) throws JsonProcessingException {
        return ProductMapper.mapProductToProductDto(this.productDao.findById(id).get());
    }

    @Override
    public List<AdminProductDto> getAllProducts() throws JsonProcessingException {
        return ProductMapper.mapProductsToProductDtos(
                StreamSupport.stream(this.productDao.findAllProd().spliterator(),false)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public SimplePage<AdminProductDto> getProductsPageByCriteriaAdmin(ProductSearchCriteria productSearchCriteria) throws JsonProcessingException {
        return null;
    }

    @Override
    public void update(AdminProductDto product) throws JsonProcessingException {
        Product productInDb=ProductMapper.mapProductDtoToProduct(product);
        this.productDao.save(product.getId(),productInDb.getShortDescription(),product.getBarcode(),
                product.getQuantityInStorage(),product.getPrice(),productInDb.getAttributes(),
                productInDb.getCategory(),productInDb.getProvider(),productInDb.getPhoto());
    }

    @Override
    public void delete(long id) {
        this.productDao.deleteById(id);
    }

    @Override
    public List<AdminProductDto> parseXLSOrXlSXFile(MultipartFile file, Lang lang) throws IOException {
        return ExelFilesWorker.parseXLSOrXlSXFile(file,lang);
    }

    @Override
    public List<AdminProductDto> createProducts(List<AdminProductDto> productDtos) throws JsonProcessingException {
        List<Product> products=ProductMapper.mapProductDtosToProducts(productDtos);
        List<Product> result=StreamSupport.stream(this.productDao.saveAll(products).spliterator(),false)
                .collect(Collectors.toList());
        return ProductMapper.mapProductsToProductDtos(result);
    }
}
