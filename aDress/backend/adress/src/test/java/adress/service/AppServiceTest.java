package adress.service;

import java.util.Arrays;
import java.util.List;

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

import adress.api.ClientRepository;
import adress.api.ProductRepository;
import adress.model.Client;
import adress.model.Order;
import adress.model.Product;

@ExtendWith(MockitoExtension.class)
public class AppServiceTest {

    @Mock(lenient = true)
    private ProductRepository prodRep;
    @Mock(lenient = true)
    private ClientRepository clientRep;

    @InjectMocks
    private AppService service;

    private Product p1;
    private Product p2;
    private Order o1;
    private Order o2;
    private Client c1;

    @BeforeEach
    public void setUp() {

        p1 = new Product(1, "brown pants", 19.99, "brown", "male", "pants");
        p2 = new Product(2, "red tshirt", 9.99, "red", "male", "tshirt");
        List<Product> prods = Arrays.asList(p1, p2);
        c1 = new Client("andreia", "2001-02-21", "123", "sesame street", 1234, 5678, "Narnia");
        o1 = new Order(c1, prods, "2022-06-01", 28.89);
        o2 = new Order(c1, prods, "2022-06-05", 28.89);
        List<Order> orders = Arrays.asList(o1, o2);
        Mockito.when(prodRep.findByProductId(1)).thenReturn(p1);
        Mockito.when(prodRep.findByProductId(2)).thenReturn(p2);
        Mockito.when(prodRep.findAll()).thenReturn(prods);
        Mockito.when(clientRep.findById(0)).thenReturn(c1);
        Mockito.when(clientRep.save(c1)).thenReturn(c1);
        Mockito.when(clientRep.findOrdersById(0)).thenReturn(orders);
        Mockito.when(clientRep.saveOrder(0, o1)).thenReturn(o1);
    }

    @Test
    public void testListAllProducts() {
        List<Product> found = service.listAllProducts();
        assertThat(found.get(0).getColor()).isEqualTo("brown");
        assertThat(found.get(1).getPrice()).isEqualTo(9.99);
        verify(prodRep, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void givenThereIsAProduct_testGetProductById() {
        Product found = service.getProductById(2);
        verify(prodRep, VerificationModeFactory.times(1)).findByProductId(2);
        assertThat(found).isEqualTo(p2);
    }

    @Test
    public void givenThereIsNotAProduct_testGetProductByInexistentId() {
        Product found = service.getProductById(3);
        verify(prodRep, VerificationModeFactory.times(1)).findByProductId(3);
        assertThat(found).isNull();
    }

    @Test
    public void givenThereIsAClient_testGetInformationById() {
        Client found = service.getInformation(0);
        verify(clientRep, VerificationModeFactory.times(1)).findById(0);
        assertThat(found).isEqualTo(c1);
    }

    @Test
    public void givenThereIsNotAClient_testGetInformationByInexistentId() {
        Client found = service.getInformation(5);
        verify(clientRep, VerificationModeFactory.times(1)).findById(5);
        assertThat(found).isNull();
    }

    @Test
    public void givenThereIsAnUpdatedClient_testUpdateInformationById() {
        Client found = service.updateInformation(c1);
        verify(clientRep, VerificationModeFactory.times(1)).save(c1);
        assertThat(found).isEqualTo(c1);
    }

    @Test
    public void testGetOrders() {
        List<Order> found = service.getOrders(0);
        assertThat(found.get(0).getDate()).isEqualTo("2022-06-01");
        assertThat(found.get(1).getDate()).isEqualTo("2022-06-05");
        verify(clientRep, VerificationModeFactory.times(1)).findOrdersById(0);
    }

    @Test
    public void testAddOrders() {
        Order found = service.addOrder(0, o1);
        assertThat(found.getDate()).isEqualTo("2022-06-01");
        verify(clientRep, VerificationModeFactory.times(1)).saveOrder(0, o1);
    }

}