package adress.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mashape.unirest.http.exceptions.UnirestException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import adress.api.CityDeliveryAPI;
import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Gender;
import adress.model.Location;
import adress.model.Order;
import adress.model.Product;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock(lenient = true)
    private ClientRepository clientRep;
    @Mock(lenient = true)
    private OrderRepository orderRep;
    @Mock(lenient = true)
    private CityDeliveryAPI cityDeliveryAPI;

    @InjectMocks
    private ClientService service;
    private Product p1;
    private Product p2;
    private Order o1;
    private OrderDTO o1dto = new OrderDTO();
    private Order o2;
    private Client c1;
    private ClientDTO c1dto = new ClientDTO();
    private Location l1;

    @BeforeEach
    public void setUp() throws UnirestException {

        p1 = new Product("brown pants", 19.99, "brown", Gender.MALE, "pants");
        p2 = new Product("red tshirt", 9.99, "red", Gender.MALE, "tshirt");
        List<Product> prods = Arrays.asList(p1, p2);
        c1 = new Client("andreia", "2001-02-21", "97", "rua doutor mario sacramento", 3810, 106, "Aveiro");
        c1dto.setName(c1.getName());
        c1dto.setDob(c1.getDob());
        c1dto.setSnum(c1.getSnum());
        c1dto.setSname(c1.getSname());
        c1dto.setPc1(c1.getPc1());
        c1dto.setPc2(c1.getPc2());
        c1dto.setCity(c1.getCity());
        o1 = new Order(c1, prods, "2022-06-01", 28.89);
        o1dto.setProds(o1.getProds());
        o1dto.setDate(o1.getDate());
        o1dto.setTotal(o1.getTotal());
        o2 = new Order(c1, prods, "2022-06-05", 28.89);
        c1.addOrder(o1);
        c1.addOrder(o2);
        // List<Order> orders = Arrays.asList(o1, o2);
        l1 = new Location(40.632084, -8.6606357);
        Mockito.when(clientRep.findById(0)).thenReturn(c1);
        Mockito.when(clientRep.save(Mockito.any())).thenReturn(c1);
        Mockito.when(orderRep.save(Mockito.any())).thenReturn(o1);
        Mockito.when(orderRep.findById(Mockito.anyInt())).thenReturn(Optional.of(o1));
        Mockito.when(cityDeliveryAPI.track(Mockito.anyString())).thenReturn(l1);
        Mockito.when(cityDeliveryAPI.send(Mockito.any())).thenReturn(o1);
    }

    @Test
    void testSave() throws UnirestException {
        // means that every initial product and client were correctly saved
        service.save();
        verify(clientRep, VerificationModeFactory.times(1)).save(Mockito.any());
    }

    @Test
    void givenThereIsAClient_testGetInformationById() {
        Client found = service.getInformation(0);
        verify(clientRep, VerificationModeFactory.times(1)).findById(0);
        assertThat(found).isEqualTo(c1);
    }

    @Test
    void givenThereIsNotAClient_testGetInformationByInexistentId() {
        Client found = service.getInformation(5);
        verify(clientRep, VerificationModeFactory.times(1)).findById(5);
        assertThat(found).isNull();
    }

    @Test
    void givenThereIsAnUpdatedClient_testUpdateInformationById() {
        Client found = service.updateInformation(c1dto);
        verify(clientRep, VerificationModeFactory.times(1)).save(Mockito.any());
        assertThat(found).isEqualTo(c1);
    }

    @Test
    void testGetOrders() {
        List<Order> found = service.getOrders(0);
        assertThat(found.get(0).getDate()).isEqualTo("2022-06-01");
        assertThat(found.get(1).getDate()).isEqualTo("2022-06-05");
        verify(clientRep, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
    }

    @Test
    void testAddOrders() {
        Order found = service.addOrder(1, o1dto);
        assertThat(found.getDate()).isEqualTo("2022-06-01");
        verify(orderRep, VerificationModeFactory.times(1)).save(Mockito.any());
        assertThat(found).isEqualTo(o1);
    }

    @Test
    void trackOrder() throws UnirestException {
        o1.setTrack("ABCDE");
        Location found = service.trackOrder(o1.getId());
        assertThat(found.getLat()).isEqualTo(40.632084);
        verify(cityDeliveryAPI, VerificationModeFactory.times(1)).track(Mockito.anyString());
    }

    @Test
    void sendOrderToCityDelivery() throws Exception {
        Order ret = service.sendOrderToCityDelivery(o1);
        assertEquals(ret.getId(), o1.getId());
        verify(cityDeliveryAPI, VerificationModeFactory.times(1)).send(Mockito.any());
    }
}
