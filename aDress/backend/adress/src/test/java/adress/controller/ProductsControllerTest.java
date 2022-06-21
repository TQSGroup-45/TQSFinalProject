package adress.controller;

import adress.model.Gender;
import adress.model.Product;
import adress.service.ProductsService;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    private List<Product> prods;

    private Product p1;
    private Product p2;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService service;

    @BeforeEach
    public void setUp() throws Exception {
        p1 = new Product("brown pants", 19.99, "brown", Gender.MALE, "pants");
        p2 = new Product("red tshirt", 9.99, "red", Gender.MALE, "tshirt");

        prods = new ArrayList<Product>();
        prods.add(p1);
        prods.add(p2);

    }

    @Test
    void testListAllProducts() throws Exception {
        when(service.listAllProducts()).thenReturn(prods);
        mvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].color", is("brown")))
                .andExpect(jsonPath("$[1].type", is("tshirt")));
        verify(service, times(1)).listAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        when(service.getProductById(Mockito.anyInt())).thenReturn(Optional.of(p1));
        mvc.perform(get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", is("brown pants")));
        verify(service, times(1)).getProductById(1);
    }

}