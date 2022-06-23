package adress.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import adress.model.Client;
import adress.model.Location;
import adress.model.Order;

public class CityDeliveryAPI {

    private String apiUrl; // add endpoint
    private Location adressLocation;

    public CityDeliveryAPI() {
        apiUrl = "http://localhost:4200";
        adressLocation = new Location(40.6327088, -8.6577065);
    }

    public JsonNode getLocationFromAPI(String trackingCode) throws UnirestException {
        System.out.println(trackingCode);
        return Unirest.get(apiUrl).asJson().getBody(); // adicionar parametros ao pedido
    }

    public Location track(String trackingCode) throws UnirestException {
        Location res = new Location(0, 0);
        JsonNode t = getLocationFromAPI(trackingCode);
        res.setLat(t.getObject().getDouble("lat"));
        res.setLng(t.getObject().getDouble("lng"));
        return res;
    }

    public Order send(Order o) throws UnirestException {
        Client c = o.getClient();
        Map<String, Object> fields = new HashMap<>();
        fields.put("from", adressLocation);
        fields.put("to", c.getLocation());
        JsonNode jnode = sendOrderToAPI(fields);
        JSONObject jobj = jnode.getObject();
        if (jobj.getInt("status") == 200) {
            o.setStatus("inDelivery");
            o.setTrack(jobj.getString("tracking")); // mudar para nome correto
            return o;
        }
        return null;
    }

    public JsonNode sendOrderToAPI(Map<String, Object> fields) throws UnirestException {
        return Unirest.post(apiUrl).fields(fields).asJson().getBody();
    }
}
