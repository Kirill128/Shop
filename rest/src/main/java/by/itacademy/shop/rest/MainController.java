package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.locale.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    public static final Lang GLOBAL_LANG=Lang.RU;
    public static final int PRODUCT_PAGE_SIZE=20;

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
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(MainController.GLOBAL_LANG);
        List<GuestProductDto> someProducts=this.productService.getLimitedProductsWithOffset(1,PRODUCT_PAGE_SIZE, GLOBAL_LANG);
        modelAndView.addObject("products",someProducts);
        modelAndView.addObject("categories",categoryDtos);
        return modelAndView;
    }
    @GetMapping("/pages/{num}")
    public ModelAndView getProductsPageGuest(@PathVariable int num) {
        ModelAndView modelAndView=new ModelAndView("/main");
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(MainController.GLOBAL_LANG);
        List<GuestProductDto> someProducts=this.productService.getLimitedProductsWithOffset(num,PRODUCT_PAGE_SIZE,MainController.GLOBAL_LANG);
        modelAndView.addObject("products",someProducts);
        modelAndView.addObject("categories",categoryDtos);
        return modelAndView;
    }


}
