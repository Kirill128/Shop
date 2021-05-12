package by.itacademy.shop.rest;

import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    private CategoryService categoryService;
    private ProductService productService;

    public MainController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getMainPage(Authentication authentication){
        return new ModelAndView("redirect:/products/pages/1");
    }

    @GetMapping("/sing-up")
    public ModelAndView singUp( ){
        ModelAndView modelAndView=new ModelAndView("/singup");
        modelAndView.addObject("newUser",new UserDto());
        return modelAndView;
    }


}
