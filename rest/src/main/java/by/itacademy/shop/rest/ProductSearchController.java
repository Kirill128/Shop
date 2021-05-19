package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Log;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(Constants.ROLE_GUEST_PRODUCT_SEARCH)
public class ProductSearchController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductSearchController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping(Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES_NUM)
    @Log
    public ModelAndView getProductsPageGuest(@PathVariable int num,
                                             @Nullable @RequestParam Long categoryId,
                                             ProductSearchCriteria fromFrontSearchCriteria,
                                             Authentication authentication) {
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(Constants.GLOBAL_LANG);
        if(categoryId!=null){
            fromFrontSearchCriteria.setCategoryId(categoryId);
        }
        if(fromFrontSearchCriteria.getLang()==null) {
            fromFrontSearchCriteria.setLang(Constants.GLOBAL_LANG);
        }
        fromFrontSearchCriteria.setPageNum((num>0)? num : 1);
        fromFrontSearchCriteria.setPageSize(Constants.PRODUCT_PAGE_SIZE);
        SimplePage<GuestProductDto> simplePage=this.productService.getProductsPageByCriteria(fromFrontSearchCriteria);
        return new ModelAndView("/product")
                .addObject("lastPage", simplePage.getResults().size()<Constants.PRODUCT_PAGE_SIZE)
                .addObject("products",simplePage.getResults())
                .addObject("allProdCount",simplePage.getCountInDb())
                .addObject("categories",categoryDtos)
                .addObject("searchCriteria",fromFrontSearchCriteria)
                .addObject("authentication",authentication);
    }
    @PostMapping(Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES_NEXT)
    public ModelAndView getNextProductsPageGuest(@ModelAttribute ProductSearchCriteria productSearchCriteria){
        productSearchCriteria.setPageNum(productSearchCriteria.getPageNum()+1);
        return new ModelAndView("redirect:"+
                Constants.ROLE_GUEST_PRODUCT_SEARCH +
                Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES +
                productSearchCriteria.getPageNum());
    }
    @PostMapping(Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES_PREVIOUS)
    public ModelAndView getPreviousProductsPageGuest(@ModelAttribute ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getPageNum()>1)productSearchCriteria.setPageNum(productSearchCriteria.getPageNum()-1);
        return new ModelAndView("redirect:"+
                Constants.ROLE_GUEST_PRODUCT_SEARCH +
                Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES +
                productSearchCriteria.getPageNum());
    }
}
