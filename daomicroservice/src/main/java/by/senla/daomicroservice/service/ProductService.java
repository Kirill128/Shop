package by.senla.daomicroservice.service;

import by.senla.daomicroservice.microservices.constants.Lang;
import by.senla.daomicroservice.dto.ProductSearchCriteria;
import by.senla.daomicroservice.microservices.dto.admin.AdminProductDto;
import by.senla.daomicroservice.microservices.dto.forall.GuestProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
   AdminProductDto create(AdminProductDto entity) throws JsonProcessingException;

   AdminProductDto findAdmin(long id) throws JsonProcessingException;
   GuestProductDto findGuest(long id, Lang lang) throws JsonProcessingException;

   void update(AdminProductDto entity) throws JsonProcessingException;

   void delete(long id);

   List<GuestProductDto> findAllGuest() throws JsonProcessingException;
   List<AdminProductDto> findAllAdmin() throws JsonProcessingException;

   Page<GuestProductDto> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria, Lang lang) throws JsonProcessingException;

}
