package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.utilenum.SortDirection;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    private CategoryService categoryService;
    private ProductService productService;

    public ProductSearchController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/pages/{num}")
    @Loggable
    public ModelAndView getProductsPageGuest(@PathVariable int num,
                                             @Nullable @RequestParam Long category_id,
                                             ProductSearchCriteria fromFrontSearchCriteria,
                                             Authentication authentication) {
        ModelAndView modelAndView=new ModelAndView("/product");
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(Constants.GLOBAL_LANG);

        if(category_id!=null){
            fromFrontSearchCriteria.setCategoryId(category_id);
        }
        if(fromFrontSearchCriteria.getLang()==null) {
            fromFrontSearchCriteria.setLang(Constants.GLOBAL_LANG);
        }
        fromFrontSearchCriteria.setPageNum(num);
        fromFrontSearchCriteria.setPageSize(Constants.PRODUCT_PAGE_SIZE);
        SimplePage<GuestProductDto> simplePage=this.productService.getProductsPageByCriteria(fromFrontSearchCriteria);

        modelAndView.addObject("products",simplePage.getResults());
        modelAndView.addObject("allProdCount",simplePage.getCountInDb());
        modelAndView.addObject("categories",categoryDtos);
        modelAndView.addObject("searchCriteria",fromFrontSearchCriteria);
        this.changeModelAndViewIfAuth(modelAndView,authentication);

        return modelAndView;
    }
    //TODO: make in thymleaf
    private void changeModelAndViewIfAuth(ModelAndView modelAndView,Authentication authentication){
        boolean isAuth;
        if(authentication==null)isAuth=false;
        else isAuth=authentication.isAuthenticated();
        String name=(isAuth) ? ((UserDetails)authentication.getPrincipal()).getUsername() : "Guest";
        modelAndView.addObject("userAuthenticated",isAuth);
        modelAndView.addObject("userName",name);
    }
}
