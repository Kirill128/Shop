package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.forall.*;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.api.services.ProductService;
import by.senla.daomicroservice.microservices.constants.Constants;
import by.senla.daomicroservice.microservices.constants.Lang;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ProductSearchControllerTest {
    private static MockMvc mockMvc;
    private static CategoryService categoryService;
    private static ProductService productService;

    private static final Lang lang = Lang.RU;

    @BeforeAll
    public static void setup()  {
        productService=Mockito.mock(ProductService.class);
        categoryService=Mockito.mock(CategoryService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductSearchController(categoryService,productService)).build();
    }
    @Test
    public void testMockInitialization() {

    }

    //ANy argument example
    @Test
    public void getProductsPageGuest() throws Exception {
        List<GuestSubCategoryDto> subCategoryDtoList=List.of(
                new GuestSubCategoryDto(1L,"Title 1"),
                new GuestSubCategoryDto(2L,"Title 2"),
                new GuestSubCategoryDto(3L,"Title 3"),
                new GuestSubCategoryDto(4L,"Title 4"));
        List<GuestParentCategoryDto> parentCategoryDtoList=List.of(
                GuestParentCategoryDto.builder()
                        .id(2L)
                        .title("Guest Parent Category Title")
                        .subcategories(subCategoryDtoList)
                        .build());
        SimplePage<GuestProductDto> simplePage=new SimplePage<>(List.of(
                GuestProductDto.builder()
                        .id(1L).shortDescription("Short Descr 1")
                        .price(4.0).category(subCategoryDtoList.get(0))
                        .photo(null).build(),
                GuestProductDto.builder()
                        .id(2L).shortDescription("Short Descr 2")
                        .price(4.0).category(subCategoryDtoList.get(0))
                        .photo(null).build(),
                GuestProductDto.builder()
                        .id(3L).shortDescription("Short Descr 3")
                        .price(4.0).category(subCategoryDtoList.get(0))
                        .photo(null).build()
        ));
        simplePage.setCurrentPageNum(8);
        simplePage.setNextPageNum(9);
        simplePage.setPreviousPageNum(7);
        simplePage.setCountInDb(9);
        ProductSearchCriteria productSearchCriteria= ProductSearchCriteria.builder()
                .pageSize(20)
                .pageNum(1)
                .lang(lang)
                .build();
        Mockito.doReturn(parentCategoryDtoList).when(categoryService).getParentCategories(lang);
        Mockito.doReturn(simplePage).when(productService).getProductsPageByCriteria(Matchers.any(ProductSearchCriteria.class));//   Any

        ModelAndView result = mockMvc.perform(
                MockMvcRequestBuilders.get(Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();
        Assertions.assertTrue(result.getModel().containsValue(simplePage.getResults()));
        Assertions.assertTrue(result.getModel().containsValue(parentCategoryDtoList));

        Assertions.assertTrue(result.getModel().get("currentPage").equals(8));
        Assertions.assertTrue(result.getModel().get("previousPage").equals(7));
        Assertions.assertTrue(result.getModel().get("nextPage").equals(9));
        Assertions.assertNotNull(result.getModel().get("searchCriteria"));
        Assertions.assertNull(result.getModel().get("authentication"));

    }
}












