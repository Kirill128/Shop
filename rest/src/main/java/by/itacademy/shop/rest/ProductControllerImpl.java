package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.api.dto.UserDto;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl {
    private ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView getStartPage(){
        ModelAndView modelAndView=new ModelAndView("/main");
        List<ProductDto> someProducts=this.productService.getLimitedProductsWithOffset(1,10);
        modelAndView.addObject("products",someProducts);
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ProductDto find(@PathVariable int id){
        return this.productService.find(id);
    }

    @GetMapping("/all")
    public ModelAndView findAllUsers(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("productList",this.productService.getAllProducts());
        return modelAndView;
    }


    @PostMapping("/create")
    public ProductDto createUser(UserDto user){
        return null;
    }
    @PutMapping("/update")
    public void update(@RequestBody UserDto user){
    }
    @DeleteMapping("/delete")
    public void delete(){

    }
}
