package by.itacademy.shop.services;

import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.utils.ExelFilesWorker;
import by.senla.microservices.constants.Constants;
import by.senla.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Profile("dao-microservice")
public class ProductServiceImplDaoMicroservice implements ProductService {

    @Override
    public List<GuestProductDto> getAllProducts(Lang lang) {
        return (List<GuestProductDto>)new RestTemplate().getForObject(
                Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_PRODUCT_GET_ALL,
                List.class);
    }

    @Override
    public SimplePage<GuestProductDto> getProductsPageByCriteria(ProductSearchCriteria searchCriteria) {
        SimplePage<GuestProductDto> dtoPage=new SimplePage<GuestProductDto>(new RestTemplate()
                .postForEntity(Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_PRODUCT_GET_ALL_BY_CRITERIA,
                        searchCriteria,List.class).getBody());

        dtoPage.setNextPageNum(this.getNextPage(searchCriteria,dtoPage));
        dtoPage.setPreviousPageNum(this.getPreviousPage(searchCriteria));
        dtoPage.setCurrentPageNum(searchCriteria.getPageNum());

        return dtoPage;
    }
    private int getNextPage(ProductSearchCriteria searchCriteria,SimplePage<GuestProductDto> productPage){
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
        return new RestTemplate().getForObject(Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_PRODUCT_GET
        ,GuestProductDto.class,id);
    }

    @Override
    public AdminProductDto createProduct(AdminProductDto product) throws JsonProcessingException {
        return new RestTemplate().postForEntity(
                Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_CREATE,
                product,AdminProductDto.class).getBody();
    }

    @Override
    public AdminProductDto findFullInfo(long id) throws JsonProcessingException {
        return new RestTemplate().getForObject(Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_GET,
                AdminProductDto.class,id);
    }

    @Override
    public List<AdminProductDto> getAllProducts() throws JsonProcessingException {
        return new RestTemplate().getForObject(Constants.DAO_MICROSERVICE_URL+ Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_GET_ALL,
                List.class);
    }

    @Override
    public SimplePage<AdminProductDto> getProductsPageByCriteriaAdmin(ProductSearchCriteria productSearchCriteria) throws JsonProcessingException {
        return null;
    }

    @Override
    public void update(AdminProductDto product) throws JsonProcessingException {
        new RestTemplate().put(Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_UPDATE,
                product);
    }

    @Override
    public void delete(long id) {
        new RestTemplate().delete(Constants.DAO_MICROSERVICE_URL+Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_DELETE,
                id);
    }

    @Override
    public List<AdminProductDto> parseXLSOrXlSXFile(MultipartFile file, Lang lang) throws IOException {
        return ExelFilesWorker.parseXLSOrXlSXFile(file,lang);
    }

    @Override
    public List<AdminProductDto> createProducts(List<AdminProductDto> productDtos) throws JsonProcessingException {
       return null;
    }
}
