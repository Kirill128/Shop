package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    private CategoryService categoryService;
    private ProductService productService;
    private Logger logger= Logger.getLogger(ProductSearchController.class);

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
        logger.info("\n\n\n\nHUITA IN CONTROLLER\n\n\n");

        ModelAndView modelAndView=new ModelAndView("/product");
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(Constants.GLOBAL_LANG);

        if(category_id!=null){
            fromFrontSearchCriteria.setCategoryId(category_id);
        }
        if(fromFrontSearchCriteria.getLang()==null) {
            fromFrontSearchCriteria.setLang(Constants.GLOBAL_LANG);
        }
        fromFrontSearchCriteria.setPageNum((num>0)? num : 1);
        fromFrontSearchCriteria.setPageSize(Constants.PRODUCT_PAGE_SIZE);
        SimplePage<GuestProductDto> simplePage=this.productService.getProductsPageByCriteria(fromFrontSearchCriteria);

        modelAndView.addObject("lastPage", simplePage.getResults().size()<Constants.PRODUCT_PAGE_SIZE);
        modelAndView.addObject("products",simplePage.getResults());
        modelAndView.addObject("allProdCount",simplePage.getCountInDb());
        modelAndView.addObject("categories",categoryDtos);
        modelAndView.addObject("searchCriteria",fromFrontSearchCriteria);
        modelAndView.addObject("authentication",authentication);

        return modelAndView;
    }
    @PostMapping("/pages/next")
    public ModelAndView getNextProductsPageGuest(@ModelAttribute ProductSearchCriteria productSearchCriteria){
        productSearchCriteria.setPageNum(productSearchCriteria.getPageNum()+1);
        return new ModelAndView("redirect:/products/pages/"+productSearchCriteria.getPageNum());
    }
    @PostMapping("/pages/previous")
    public ModelAndView getPreviousProductsPageGuest(@ModelAttribute ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getPageNum()>1)productSearchCriteria.setPageNum(productSearchCriteria.getPageNum()-1);
        return new ModelAndView("redirect:/products/pages/"+productSearchCriteria.getPageNum());
    }
}
