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
import static org.mockito.Mockito.verify;

import adress.api.ProductRepository;
import adress.model.Gender;
import adress.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @Mock(lenient = true)
    private ProductRepository prodRep;

    @InjectMocks
    private ProductsService service;

    private Product p1;
    private Product p2;

    @BeforeEach
    public void setUp() throws UnirestException {

        p1 = new Product("brown pants", 19.99, "brown", Gender.MALE, "pants");
        p2 = new Product("red tshirt", 9.99, "red", Gender.MALE, "tshirt");
        List<Product> prods = Arrays.asList(p1, p2);
        // List<Order> orders = Arrays.asList(o1, o2);
        Mockito.when(prodRep.findById(1)).thenReturn(Optional.of(p1));
        Mockito.when(prodRep.findById(2)).thenReturn(Optional.of(p2));
        Mockito.when(prodRep.save(Mockito.any())).thenReturn(p1);
        Mockito.when(prodRep.findAll()).thenReturn(prods);

    }

    @Test
    void testSave() {
        // means that every initial product and client were correctly saved
        service.save();
        verify(prodRep, VerificationModeFactory.times(8)).save(Mockito.any());
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

}