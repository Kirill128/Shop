package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.dto.admin.*;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.PhotoService;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.api.services.ProviderService;
import by.itacademy.shop.rest.MainController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class ProductControllerImpl {
    private ProductService productService;
    private CategoryService categoryService;
    private ProviderService providerService;
    private PhotoService photoService;

    public ProductControllerImpl(ProductService productService, CategoryService categoryService, ProviderService providerService, PhotoService photoService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.providerService = providerService;
        this.photoService = photoService;
    }

    @GetMapping
    public ModelAndView findAllProductsFullInfo() throws JsonProcessingException {
        List<ProductDto> productDtos=this.productService.getAllProducts();
        List<CategoryDto> subCategories=this.categoryService.getSubCategoriesFullInfo();
        List<ProviderDto> providerDtos=this.providerService.getAllProviders();

        ModelAndView modelAndView=new ModelAndView("/admin/products");

        modelAndView.addObject("products",productDtos);
        modelAndView.addObject("subCategories",subCategories);
        modelAndView.addObject("providers",providerDtos);
        modelAndView.addObject("productToCreate",new ProductDto());

        return modelAndView;
    }

    @PostMapping(value="/upload-file")
    public ModelAndView uploadProductsFile(@ModelAttribute ProductDto defValues, @RequestParam("exelFile") MultipartFile exelFile) throws IOException {
        List<ProductDto> productDtos=this.productService.parseXLSOrXlSXFile(exelFile, MainController.GLOBAL_LANG);
        List<CategoryDto> subCategories=this.categoryService.getSubCategoriesFullInfo();
        List<ProviderDto> providerDtos=this.providerService.getAllProviders();
        for(ProductDto productDto : productDtos){
            productDto.setCategoryId(defValues.getCategoryId());
            productDto.setProviderId(defValues.getProviderId());
        }
        ListProductDtos list=new ListProductDtos(productDtos);
        ModelAndView modelAndView=new ModelAndView("/admin/uploadfile");

        modelAndView.addObject("listProductDtos",list);
        modelAndView.addObject("lang",MainController.GLOBAL_LANG);
        modelAndView.addObject("subCategories",subCategories);
        modelAndView.addObject("providers",providerDtos);

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute ProductDto product,@RequestParam("imgPrCr") MultipartFile img) throws IOException {
        this.photoService.createPhoto(img);
        this.productService.createProduct(product);
        return new ModelAndView("redirect:/admin/products");
    }

    @PostMapping("/create/list")
    public ModelAndView createProduct(@ModelAttribute ListProductDtos products) throws JsonProcessingException {
        this.productService.createProducts(products.getProductDtoList());
        return new ModelAndView("redirect:/admin/products");
    }


    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute ProductDto product) throws JsonProcessingException {
        this.productService.update(product);
        return new ModelAndView("redirect:/admin/products");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute ProductDto product){
        this.productService.delete(product.getId());
        return new ModelAndView("redirect:/admin/products");
    }

    @InitBinder
    public void custoizeBinding(WebDataBinder binder){
        binder.setAutoGrowCollectionLimit(10000);
    }
}
