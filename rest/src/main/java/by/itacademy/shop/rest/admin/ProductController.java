package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.*;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.PhotoService;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.api.services.ProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProviderService providerService;
    private final PhotoService photoService;

    public ProductController(ProductService productService, CategoryService categoryService, ProviderService providerService, PhotoService photoService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.providerService = providerService;
        this.photoService = photoService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT)
    public ModelAndView findAllProductsFullInfo() throws JsonProcessingException {
        return new ModelAndView("/admin/products")
                .addObject("products",this.productService.getAllProducts())
                .addObject("subCategories",this.categoryService.getSubCategoriesFullInfo())
                .addObject("providers",this.providerService.getAllProviders())
                .addObject("productToCreate",new AdminProductDto());
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_UPLOAD_FILE)
    @LogExceptionCatchable
    public ModelAndView uploadProductsFile(@ModelAttribute AdminProductDto defValues,
                                           @RequestParam("exelFile") MultipartFile exelFile,
                                           @ModelAttribute AdminExelFileMetadata fileMetadata) throws IOException {
        List<AdminProductDto> productDtos=this.productService.parseXLSOrXlSXFile(exelFile, Constants.GLOBAL_LANG);
        for(AdminProductDto productDto : productDtos){
            productDto.setCategoryId(defValues.getCategoryId());
            productDto.setProviderId(defValues.getProviderId());
        }
        return new ModelAndView("/admin/uploadfile")
                .addObject("listProductDtos",new AdminListProductDtos(productDtos))
                .addObject("lang",Constants.GLOBAL_LANG)
                .addObject("subCategories",this.categoryService.getSubCategoriesFullInfo())
                .addObject("providers",this.providerService.getAllProviders())
                .addObject("fileMetadata",new AdminExelFileMetadata());
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE)
    @LogExceptionCatchable
    public ModelAndView createProduct(@ModelAttribute AdminProductDto product, @RequestParam("imgPrCr") MultipartFile img) throws IOException {
        this.photoService.createPhoto(img);
        this.productService.createProduct(product);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE_LIST)
    @LogExceptionCatchable
    public ModelAndView createProduct(@ModelAttribute AdminListProductDtos products) throws JsonProcessingException {
        this.productService.createProducts(products.getProductDtoList());
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT);
    }


    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_UPDATE)
    @LogExceptionCatchable
    public ModelAndView update(@ModelAttribute AdminProductDto product) throws JsonProcessingException {
        this.productService.update(product);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_DELETE)
    @LogExceptionCatchable
    public ModelAndView delete(@ModelAttribute AdminProductDto product){
        this.productService.delete(product.getId());
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT);
    }

    @InitBinder
    public void custoizeBinding(WebDataBinder binder){
        binder.setAutoGrowCollectionLimit(10000);
    }
}
