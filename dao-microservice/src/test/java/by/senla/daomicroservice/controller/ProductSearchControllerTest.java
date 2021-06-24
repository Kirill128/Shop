package by.senla.daomicroservice.controller;

import by.senla.daomicroservice.App;
import by.senla.daomicroservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ProductSearchController.class)
public class ProductSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void findById() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/admin/1"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect();
    }

    @Test
    public void getAllGuest() {
    }

    @Test
    public void getAllByCriteria() {
    }

    @Test
    public void findAdminById() {
    }

    @Test
    public void create() {
    }

    @Test
    public void getAllAdmin() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}