package adress.controller;

import adress.JsonUtils;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Gender;
import adress.model.Location;
import adress.model.Order;
import adress.model.Product;
import adress.service.ClientService;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    private List<Order> orders;
    private Order o1;
    private Order o2;
    private Client c1;
    private Location l1;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService service;

    @BeforeEach
    public void setUp() throws Exception {
        Product p1 = new Product("brown pants", 19.99, "brown", Gender.MALE, "pants");
        Product p2 = new Product("red tshirt", 9.99, "red", Gender.MALE, "tshirt");
        List<Product> prods = new ArrayList<Product>();
        prods.add(p1);
        prods.add(p2);

        c1 = new Client("andreia", "2001-02-21", "123", "sesame street", 1234, 5678, "Narnia", "andreia@gmail.com");
        o1 = new Order(c1, prods, "2022-06-01", 28.89);
        o2 = new Order(c1, prods, "2022-06-05", 28.89);
        l1 = new Location(40.632084, -8.6606357);
        orders = new ArrayList<Order>();
        orders.add(o1);
        orders.add(o2);
    }

    @Test
    void testGetOrders() throws Exception {
        when(service.getOrders(Mockito.anyInt())).thenReturn(orders);
        mvc.perform(get("/api/v1/clients/0/orders") // vai buscar orders do cliente com id=0
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date", is("2022-06-01")))
                .andExpect(jsonPath("$[1].date", is("2022-06-05")));
        verify(service, times(1)).getOrders(Mockito.anyInt());
    }

    @Test
    void testAddOrder() throws Exception {
        when(service.addOrder(Mockito.anyInt(), (OrderDTO) Mockito.any())).thenReturn(o1);
        mvc.perform(
                post("/api/v1/clients/0/orders").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(o1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date", is("2022-06-01")));
        verify(service, times(1)).addOrder(Mockito.anyInt(), (OrderDTO) Mockito.any());
    }

    @Test
    void testGetInformation() throws Exception {
        when(service.getInformation(Mockito.anyInt())).thenReturn(c1);
        mvc.perform(get("/api/v1/clients/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("andreia")));
        verify(service, times(1)).getInformation(Mockito.anyInt());
    }

    @Test
    void testUpdateInformation() throws Exception {
        when(service.updateInformation((ClientDTO) Mockito.any())).thenReturn(c1);
        mvc.perform(put("/api/v1/clients/0").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(c1)))
                .andExpect(status().isAccepted());
        verify(service, times(1)).updateInformation((ClientDTO) Mockito.any());
    }

    @Test
    void trackOrder() throws Exception {
        when(service.trackOrder(Mockito.anyInt(), Mockito.anyInt())).thenReturn(l1);
        // return latitude and longitude { lat: 40.632084, lng:-8.6606357 }
        mvc.perform(get("/api/v1/clients/0/orders/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lat", is(40.632084)))
                .andExpect(jsonPath("$.lng", is(-8.6606357)));
        verify(service, times(1)).trackOrder(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void whenPostClient_thenCreateClient() throws IOException, Exception{

        ClientDTO manuel = new ClientDTO( );

        manuel.setName("Manuel");
        manuel.setDob("1-1-2000");
        manuel.setSname("José");
        manuel.setSnum("123");
        manuel.setCity("Aveiro");
        manuel.setPc1(1);
        manuel.setPc2(1);

        when( service.createClient(Mockito.any())).thenReturn( manuel);

        mvc.perform(
            post("/api/v1/clients").contentType(MediaType.APPLICATION_JSON).content( JsonUtils.toJson(manuel) ) )
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is("Manuel")));
        
        verify(service, times(1)).createClient(Mockito.any());
    }
}
