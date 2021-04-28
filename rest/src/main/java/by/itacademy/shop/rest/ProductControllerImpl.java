package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.CategoryDto;
import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getProductsPage(@RequestParam int num){
        ModelAndView modelAndView=new ModelAndView("/main");
        List<CategoryDto> categoryDtos=this.categoryService.getAllCategories();
        List<ProductDto> someProducts=this.productService.getLimitedProductsWithOffset(num,PRODUCT_PAGE_SIZE);
        modelAndView.addObject("products",someProducts);
        modelAndView.addObject("category",categoryDtos);
        return modelAndView;
    }
    

    @PostMapping("/upload-file")
    public ModelAndView uploadProductsFile(@RequestBody MultipartFile file){

        return null;
    }
    //---------------------------------------------
    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable int id){
        ProductDto productDto=this.productService.find(id);
        return null;
    }

    @GetMapping("/all")
    public ModelAndView findAllProducts(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("productList",this.productService.getAllProducts());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(ProductDto product){
        return null;
    }

    @PutMapping("/update")
    public ModelAndView update(@RequestBody ProductDto product){
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        return null;
    }
}
