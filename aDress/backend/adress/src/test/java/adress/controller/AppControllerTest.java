package adress.controller;

import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Order;
import adress.model.Product;
import adress.service.AppService;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@WebMvcTest(AppController.class)
class AppControllerTest {

    private List<Product> prods;
    private List<Order> orders;
    private Product p1;
    private Product p2;
    private Order o1;
    private Order o2;
    private Client c1;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppService service;

    @BeforeEach
    public void setUp() throws Exception {
        p1 = new Product("brown pants", 19.99, "brown", "male", "pants");
        p2 = new Product("red tshirt", 9.99, "red", "male", "tshirt");
        c1 = new Client("andreia", "2001-02-21", "123", "sesame street", 1234, 5678, "Narnia");
        o1 = new Order(c1, prods, "2022-06-01", 28.89);
        o2 = new Order(c1, prods, "2022-06-05", 28.89);
        prods = new ArrayList<Product>();
        prods.add(p1);
        prods.add(p2);
        orders = new ArrayList<Order>();
        orders.add(o1);
        orders.add(o2);
    }

    @Test
    void testListAllProducts() throws Exception {
        when(service.listAllProducts()).thenReturn(prods);
        mvc.perform(get("/api/v1/store").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].color", is("brown")))
                .andExpect(jsonPath("$[1].gender", is("male")));
        verify(service, times(1)).listAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        when(service.getProductById(Mockito.anyInt())).thenReturn(Optional.of(p1));
        mvc.perform(get("/api/v1/product/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", is("brown pants")));
        verify(service, times(1)).getProductById(1);
    }

    @Test
    void testGetOrders() throws Exception {
        when(service.getOrders(Mockito.anyInt())).thenReturn(orders);
        mvc.perform(get("/api/v1/orders/0") // vai buscar orders do cliente com id=0
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date", is("2022-06-01")))
                .andExpect(jsonPath("$[1].date", is("2022-06-05")));
        verify(service, times(1)).getOrders(Mockito.anyInt());
    }

    @Test
    void testAddOrder() throws Exception {
        when(service.addOrder((OrderDTO) Mockito.any())).thenReturn(o1);
        mvc.perform(post("/api/v1/orders").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(o1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date", is("2022-06-01")));
        verify(service, times(1)).addOrder((OrderDTO) Mockito.any());
    }

    @Test
    void testGetInformation() throws Exception {
        when(service.getInformation(Mockito.anyInt())).thenReturn(c1);
        mvc.perform(get("/api/v1/profile/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("andreia")));
        verify(service, times(1)).getInformation(Mockito.anyInt());
    }

    @Test
    void testUpdateInformation() throws Exception {
        when(service.updateInformation((ClientDTO) Mockito.any())).thenReturn(c1);
        mvc.perform(put("/api/v1/profile/0").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(c1)))
                .andExpect(status().isAccepted());
        verify(service, times(1)).updateInformation((ClientDTO) Mockito.any());
    }

}