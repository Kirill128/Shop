package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ListProductDtos;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl {
    public static final int PRODUCT_PAGE_SIZE=20;

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductControllerImpl(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    //--------------------------------------------
    @GetMapping("/main")
    public ModelAndView getProductsPage(@RequestParam int num) {
        ModelAndView modelAndView=new ModelAndView("/main");
        List<GuestParentCategoryDto> categoryDtos=this.categoryService.getParentCategories(MainController.GLOBAL_LANG);
        List<GuestProductDto> someProducts=this.productService.getLimitedProductsWithOffset(num,PRODUCT_PAGE_SIZE,MainController.GLOBAL_LANG);
        modelAndView.addObject("products",someProducts);
        modelAndView.addObject("categories",categoryDtos);
        return modelAndView;
    }
    

    @PostMapping(value="/upload-file")
    public ModelAndView uploadProductsFile(@RequestParam("exelFile") MultipartFile exelFile) throws IOException {
        List<ProductDto> productDtos=this.productService.parseXLSOrXlSXFile(exelFile,MainController.GLOBAL_LANG);
        ListProductDtos list=new ListProductDtos(  productDtos);
        ModelAndView modelAndView=new ModelAndView("/admin/uploadfile");
        modelAndView.addObject("listProductDtos",list);
        modelAndView.addObject("lang",MainController.GLOBAL_LANG);
        return modelAndView;
    }
    //---------------------------------------------
    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable int id){
        GuestProductDto productDto=this.productService.find(id,MainController.GLOBAL_LANG);
        return null;
    }

    @GetMapping("/")
    public ModelAndView findAllProducts() throws JsonProcessingException {
        List<ProductDto> productDtos=this.productService.getAllProducts();
        ModelAndView modelAndView=new ModelAndView("/admin/products");
        modelAndView.addObject("products",productDtos);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(ProductDto product){
        return null;
    }

    @PostMapping("/list")
    public ModelAndView createProduct(@ModelAttribute ListProductDtos products) throws JsonProcessingException {
        this.productService.createProducts(products.getProductDtoList());
        return new ModelAndView("redirect:/products/");
    }


    @PostMapping("/update")
    public ModelAndView update(@RequestBody ProductDto product){
        return null;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@PathVariable int id){
        return null;
    }
}
