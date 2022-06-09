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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import adress.api.CityDeliveryAPI;
import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.api.ProductRepository;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Gender;
import adress.model.Location;
import adress.model.Order;
import adress.model.Product;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @Mock(lenient = true)
    private ProductRepository prodRep;
    @Mock(lenient = true)
    private ClientRepository clientRep;
    @Mock(lenient = true)
    private OrderRepository orderRep;
    @Mock(lenient = true)
    private CityDeliveryAPI cityDeliveryAPI;

    @InjectMocks
    private AppService service;

    private Product p1;
    private Product p2;
    private Order o1;
    private OrderDTO o1dto = new OrderDTO();
    private Order o2;
    private Client c1;
    private ClientDTO c1dto = new ClientDTO();
    private Location l1;

    @BeforeEach
    public void setUp() {

        p1 = new Product("brown pants", 19.99, "brown", Gender.MALE, "pants");
        p2 = new Product("red tshirt", 9.99, "red", Gender.MALE, "tshirt");
        List<Product> prods = Arrays.asList(p1, p2);
        c1 = new Client("andreia", "2001-02-21", "123", "sesame street", 1234, 5678, "Narnia");
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
        Mockito.when(prodRep.findById(1)).thenReturn(Optional.of(p1));
        Mockito.when(prodRep.findById(2)).thenReturn(Optional.of(p2));
        Mockito.when(prodRep.save(Mockito.any())).thenReturn(p1);
        Mockito.when(prodRep.findAll()).thenReturn(prods);
        Mockito.when(clientRep.findById(0)).thenReturn(c1);
        Mockito.when(clientRep.save(Mockito.any())).thenReturn(c1);
        Mockito.when(orderRep.save(Mockito.any())).thenReturn(o1);
        // Mockito.when(orderRep.findAll()).thenReturn(orders);
        Mockito.when(cityDeliveryAPI.track(Mockito.anyInt(), Mockito.anyInt())).thenReturn(l1);
    }

    @Test
    void testSave() {
        // means that every initial product and client were correctly saved
        service.save();
        verify(prodRep, VerificationModeFactory.times(8)).save(Mockito.any());
        verify(clientRep, VerificationModeFactory.times(1)).save(Mockito.any());
    }

    @Test
    void testListAllProducts() {
        List<Product> found = service.listAllProducts();
        assertThat(found.get(0).getColor()).isEqualTo("brown");
        assertThat(found.get(1).getPrice()).isEqualTo(9.99);
        verify(prodRep, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    void givenThereIsAProduct_testGetProductById() {
        Optional<Product> found = service.getProductById(2);
        verify(prodRep, VerificationModeFactory.times(1)).findById(2);
        assertThat(found).contains(p2);
    }

    @Test
    void givenThereIsNotAProduct_testGetProductByInexistentId() {
        Optional<Product> found = service.getProductById(3);
        verify(prodRep, VerificationModeFactory.times(1)).findById(3);
        assertThat(found).isEmpty();
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
    void trackOrder() {
        Location found = service.trackOrder(c1.getId(), o1.getId());
        assertThat(found.getLat()).isEqualTo(40.632084);
        verify(cityDeliveryAPI, VerificationModeFactory.times(1)).track(Mockito.anyInt(), Mockito.anyInt());
    }
}