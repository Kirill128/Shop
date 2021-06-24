package test.by.itacademy.shop.service;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.api.mappers.ProductMapper;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import by.itacademy.shop.utilenum.SortDirection;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import test.by.itacademy.shop.config.TestProductServiceConfig;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestProductServiceConfig.class,
        loader = AnnotationConfigContextLoader.class)
public class TestProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductService productService;

    private static final String descrString="ОПИСАНИЕ ДЛЯ ПОИСКА";
    private static final Lang lang = Lang.RU;

    private static final long id=2L;
    private static final Map<String,String> attributes=null;
    private static final int quantityInStorage=4;
    private static final Map<String,String> shortDescription=Map.of(lang.value,descrString);
    private static final double price=4.0;
    private static final String barcode="some barcode";
    private static final Provider provider=null;
    private static final Photo photo=null;

    private static final String categoryRussianTitle="Описание Категории";
    private static final Map<String,String> categoryTitleMap=Map.of(lang.value,categoryRussianTitle);
    private static final Category category= Category.builder()
            .id(5L)
            .title(categoryTitleMap)
            .build();

    public static Product createFullProductEntity(){
        return Product.builder()
                .id(id)
                .attributes(attributes)
                .quantityInStorage(quantityInStorage)
                .shortDescription(shortDescription)
                .price(price)
                .barcode(barcode)
                .category(category)
                .photo(photo)
                .provider(provider)
                .build();
    }
    //-------------------------Find One block-------------------------------:Times Example
    @Test
    public void testFindNotExistingProduct(){
        int idToFind=7;

        Mockito.when(productDao.find(idToFind)).thenThrow(new NoSuchElementException());

        Assert.assertThrows(NoSuchElementException.class, () -> productService.find(idToFind,lang));
        Assert.assertThrows(NoSuchElementException.class, () -> productService.findFullInfo(idToFind));

        Mockito.verify(productDao,Mockito.times(2)).find(idToFind);
    }
    @Test
    public void testFindExistingProduct() throws JsonProcessingException {
        Product product=createFullProductEntity();
        Mockito.doReturn(product).when(productDao).find(id);

        AdminProductDto adminProductDto=productService.findFullInfo(id);
        GuestProductDto guestProductDto=productService.find(id,lang);
       
        assertEqualsProductAndAdminProductsDto(product,adminProductDto);
        assertEqualsProductAndGuestProductsDto(product,guestProductDto);
    }
    //---------------------------Find all block-----------------------------

    @Test
    public void getAllProducts() throws JsonProcessingException {
        Product product=createFullProductEntity();

        Mockito.doReturn(List.of(
                Product.builder()
                        .id(id).attributes(attributes).quantityInStorage(quantityInStorage)
                        .shortDescription(shortDescription).price(price)
                        .barcode(barcode).category(category).photo(photo).provider(provider).build(),
                Product.builder()
                        .id(id).attributes(attributes).quantityInStorage(quantityInStorage)
                        .shortDescription(shortDescription).price(price)
                        .barcode(barcode).category(category).photo(photo).provider(provider).build(),
                Product.builder()
                        .id(id).attributes(attributes).quantityInStorage(quantityInStorage)
                        .shortDescription(shortDescription).price(price)
                        .barcode(barcode).category(category).photo(photo).provider(provider).build()
                )
        ).when(productDao).findAll();

        List<AdminProductDto> adminProductsToTest=this.productService.getAllProducts();
        List<GuestProductDto> guestProductsToTest=this.productService.getAllProducts(lang);

        Assertions.assertEquals(3,adminProductsToTest.size());
        Assertions.assertEquals(3,guestProductsToTest.size());

        adminProductsToTest.forEach(e-> {
            try {
                assertEqualsProductAndAdminProductsDto(product,e);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });
        guestProductsToTest.forEach(e->assertEqualsProductAndGuestProductsDto(product,e));

    }
    //----------------------------Find by Criteria block----------------------------

    @Test
    public void getProductsPageByCriteria() throws JsonProcessingException {
        Product product=createFullProductEntity();
        ProductSearchCriteria productSearchCriteria=ProductSearchCriteria.builder()
                .lang(lang)
                .categoryId(category.getId())
                .pageNum(1)
                .pageSize(20)
                .partOfName("some part")
                .sortBy("description")
                .sortDirection(SortDirection.DECREASE)
                .build();
        SimplePage<Product> productSimplePage= new SimplePage<>(List.of(product,product,product));
        Mockito.doReturn(productSimplePage).when(productDao).getProductsPageByCriteria(productSearchCriteria);

        SimplePage<GuestProductDto> resultProducts=productService.getProductsPageByCriteria(productSearchCriteria);

        Assertions.assertArrayEquals(
                ProductMapper.mapProductsToGuestProductDtos(productSimplePage.getResults(),lang).toArray(new GuestProductDto[0]),
                resultProducts.getResults().toArray(new GuestProductDto[0]));
    }
    //-----------------------------Update block---------------------------//Argument Captor example

    @Test
    public void update() throws JsonProcessingException {
        Product product=createFullProductEntity();
        productService.update(ProductMapper.mapProductToProductDto(product));

        ArgumentCaptor<Product> argumentCaptor=ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productDao).update(argumentCaptor.capture());
        Product capturedProduct = argumentCaptor.getValue();
        assertEqualsProductAndProduct(product,capturedProduct);
    }
    //-------------------------------Delete block-------------------------

    @Test
    public void delete() {
        Product product=createFullProductEntity();
        Mockito.doReturn(product).when(productDao).find(product.getId());
        productService.delete(product.getId());

        ArgumentCaptor<Product> argumentCaptor=ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productDao).delete(argumentCaptor.capture());
        Product capturedProduct = argumentCaptor.getValue();
        Assertions.assertEquals(product.getId(),capturedProduct.getId());
    }
    //--------------------------------Create One block------------------------

    @Test
    public void createProduct() throws JsonProcessingException {
        Product product=createFullProductEntity();
        AdminProductDto adminProductDto=ProductMapper.mapProductToProductDto(product);
        productService.createProduct(adminProductDto);

        ArgumentCaptor<Product> argumentCaptor=ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productDao).create(argumentCaptor.capture());
        Product capturedProduct = argumentCaptor.getValue();
        assertEqualsProductAndProduct(ProductMapper.mapProductDtoToProduct(adminProductDto),capturedProduct);
    }

    //-----------------------------------Product Equals block---------------------

    private void assertEqualsProductAndAdminProductsDto(Product product,AdminProductDto adminProductDto) throws JsonProcessingException {
        Assertions.assertEquals(product.getId(),adminProductDto.getId());
        Assertions.assertEquals(product.getQuantityInStorage(),adminProductDto.getQuantityInStorage());
        Assertions.assertEquals(new ObjectMapper().writeValueAsString(product.getShortDescription()),
                adminProductDto.getShortDescription());
        Assertions.assertEquals(product.getPrice(),adminProductDto.getPrice());
        Assertions.assertEquals(product.getBarcode(),adminProductDto.getBarcode());
        Assertions.assertEquals(product.getCategory().getId(),adminProductDto.getCategoryId());
        Assertions.assertNull(adminProductDto.getPhotoId());
        Assertions.assertNull(adminProductDto.getProviderId());
        Assertions.assertNull(adminProductDto.getAttributes());
    }
    private void assertEqualsProductAndGuestProductsDto(Product product,GuestProductDto guestProductDto){
        Assertions.assertEquals(id,guestProductDto.getId());
        Assertions.assertEquals(descrString,guestProductDto.getShortDescription());
        Assertions.assertEquals(price,guestProductDto.getPrice());
        Assertions.assertEquals(category.getId(),guestProductDto.getCategory().getId());
        Assertions.assertEquals(categoryRussianTitle,guestProductDto.getCategory().getTitle());
        Assertions.assertNull(guestProductDto.getPhoto());
        Assertions.assertNull(guestProductDto.getAttributes());
    }
    private static void assertEqualsProductAndProduct(Product product,Product anotherProduct) throws JsonProcessingException {
        Assertions.assertEquals(product.getId(),anotherProduct.getId());
        Assertions.assertEquals(product.getQuantityInStorage(),anotherProduct.getQuantityInStorage());
        Assertions.assertEquals(new ObjectMapper().writeValueAsString(product.getShortDescription()),
                new ObjectMapper().writeValueAsString(anotherProduct.getShortDescription()));
        Assertions.assertEquals(product.getPrice(),anotherProduct.getPrice());
        Assertions.assertEquals(product.getBarcode(),anotherProduct.getBarcode());
        Assertions.assertEquals(product.getCategory().getId(),anotherProduct.getCategory().getId());
        Assertions.assertEquals(product.getPhoto(),anotherProduct.getPhoto());
        Assertions.assertEquals(product.getProvider(),anotherProduct.getProvider());
        Assertions.assertEquals(product.getAttributes(),anotherProduct.getAttributes());
    }
}
