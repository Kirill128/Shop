package by.itacademy.shop.rest;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.CategoryDto;
import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getMainPage(){
        ModelAndView modelAndView=new ModelAndView("/main");
        List<CategoryDto> categoryDtos=this.categoryService.getAllCategories();
        List<ProductDto> someProducts=this.productService.getLimitedProductsWithOffset(1,ProductControllerImpl.PRODUCT_PAGE_SIZE);
        modelAndView.addObject("products",someProducts);

        modelAndView.addObject("category",categoryDtos);
        return modelAndView;
    }
}
