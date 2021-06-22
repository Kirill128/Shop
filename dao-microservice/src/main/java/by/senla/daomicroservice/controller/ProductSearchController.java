package by.senla.daomicroservice.controller;

import by.senla.daomicroservice.dto.ProductSearchCriteria;
import by.senla.daomicroservice.service.ProductService;
import by.senla.daomicroservice.microservices.constants.Constants;
import by.senla.daomicroservice.microservices.dto.admin.AdminProductDto;
import by.senla.daomicroservice.microservices.dto.forall.GuestProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductSearchController {

    private ProductService productService;

    public ProductSearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(Constants.DAO_MICROSERVICE_PRODUCT_GET)
    public GuestProductDto findById(@PathVariable long id) throws JsonProcessingException {
        return this.productService.findGuest(id,Constants.GLOBAL_LANG);
    }

    @GetMapping(Constants.DAO_MICROSERVICE_PRODUCT_GET_ALL)
    public List<GuestProductDto> getAllGuest() throws JsonProcessingException {
        return this.productService.findAllGuest();
    }
    @PostMapping(Constants.DAO_MICROSERVICE_PRODUCT_GET_ALL_BY_CRITERIA)
    public List<GuestProductDto> getAllByCriteria(@RequestBody ProductSearchCriteria searchCriteria) throws JsonProcessingException {
        List<GuestProductDto> list=this.productService.getProductsPageByCriteria(searchCriteria,Constants.GLOBAL_LANG).toList();
        return list;
    }

//-----------------------ADMIN-----------------
    @GetMapping(Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_GET)
    public AdminProductDto findAdminById(@PathVariable long id) throws JsonProcessingException {
        return this.productService.findAdmin(id);
    }
    @PostMapping(Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_CREATE)//create
    public AdminProductDto create(@RequestBody AdminProductDto adminProductDto) throws JsonProcessingException {
        return this.productService.create(adminProductDto);
    }
    @GetMapping(Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_GET_ALL)
    public List<AdminProductDto> getAllAdmin() throws JsonProcessingException {
        return this.productService.findAllAdmin();
    }
    @PutMapping(Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_UPDATE)
    public void update(@RequestBody AdminProductDto adminProductDto) throws JsonProcessingException {
         this.productService.update(adminProductDto);
    }
    @DeleteMapping(Constants.DAO_MICROSERVICE_ADMIN_PRODUCT_DELETE)
    public void delete (@RequestBody Long id){
        this.productService.delete(id);
    }
}
