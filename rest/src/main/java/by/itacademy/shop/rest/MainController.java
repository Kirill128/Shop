package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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

    @GetMapping("/errors/404")
    public ModelAndView getNoPagesFount(){
        return new ModelAndView("/errors/404");
    }

    @GetMapping("/sing-up")
    public ModelAndView singUp(){
        ModelAndView modelAndView=new ModelAndView("/singup");
        modelAndView.addObject("newUser",new UserDto());
        return modelAndView;
    }


}
