package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    //Users
    List<GuestProductDto> getAllProducts(Lang lang) throws JsonProcessingException;
    SimplePage<GuestProductDto> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria) throws JsonProcessingException;
    GuestProductDto find(long id, Lang lang);


    //Admin
    AdminProductDto createProduct(AdminProductDto user) throws JsonProcessingException;
    AdminProductDto findFullInfo(long id) throws JsonProcessingException;
    List<AdminProductDto> getAllProducts() throws JsonProcessingException;
    SimplePage<AdminProductDto> getProductsPageByCriteriaAdmin(ProductSearchCriteria productSearchCriteria) throws JsonProcessingException;
    void update(AdminProductDto user) throws JsonProcessingException;
    void delete(long id);
    List<AdminProductDto> parseXLSOrXlSXFile(MultipartFile file, Lang lang) throws IOException;
    List<AdminProductDto> createProducts(List<AdminProductDto> productDtos) throws JsonProcessingException;

}
