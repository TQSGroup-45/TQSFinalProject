package adress.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import adress.model.Client;
import adress.model.Gender;
import adress.model.Location;
import adress.model.Order;
import adress.model.Product;

@ExtendWith(MockitoExtension.class)
public class CityDeliveryApiTest {

    private CityDeliveryAPI api = spy(CityDeliveryAPI.class); // permite fazer mock de alguns metodos enquanto outros
                                                              // sao realmente chamados

    private Location loc;
    private String accepted;
    private String unreachable;
    private Client client;
    private Order order;

    @BeforeEach
    public void setUp() throws JSONException, UnirestException {
        loc = new Location(-30, 30);

        accepted = "{'status':200,'tracking':'AHSHFE'}";
        unreachable = "{'status':400}";
        List<Product> prods = Arrays.asList(new Product("brown pants", 19.99, "brown", Gender.MALE, "pants"));
        client = new Client("andreia", "2001-02-21", "97", "rua doutor mario sacramento", 3810, 106, "Aveiro");
        order = new Order(client, prods, "2022-06-01", 19.99);

    }

    @Test
    public void testTrack() throws UnirestException, IOException {
        JsonNode json = new JsonNode(new Gson().toJson(loc));
        Mockito.doReturn(json).when(api).getLocationFromAPI(Mockito.anyString());
        order.setTrack("ABCDEF");
        Location res = api.track("ABCDEF");
        assertEquals(res.getLat(), loc.getLat());
        assertEquals(res.getLng(), loc.getLng());
        verify(api, VerificationModeFactory.times(1)).track(Mockito.anyString());
        verify(api, VerificationModeFactory.times(1)).getLocationFromAPI(Mockito.anyString());
    }

    @Test
    public void test_not_sent_order() throws UnirestException, IOException {
        JsonNode json = new JsonNode(unreachable);
        Mockito.doReturn(json).when(api).sendOrderToAPI(Mockito.anyMap());
        Order res = api.send(order);
        assertNull(res);
        verify(api, VerificationModeFactory.times(1)).send(Mockito.any());
        verify(api, VerificationModeFactory.times(1)).sendOrderToAPI(Mockito.anyMap());
    }

    @Test
    public void test_successful_sent_order() throws UnirestException, IOException {
        JsonNode json = new JsonNode(accepted);
        Mockito.doReturn(json).when(api).sendOrderToAPI(Mockito.anyMap());
        Order res = api.send(order);
        assertEquals(res.getId(), order.getId());
        assertEquals("AHSHFE", res.getTrack());
        verify(api, VerificationModeFactory.times(1)).send(Mockito.any());
        verify(api, VerificationModeFactory.times(1)).sendOrderToAPI(Mockito.anyMap());
    }

}
