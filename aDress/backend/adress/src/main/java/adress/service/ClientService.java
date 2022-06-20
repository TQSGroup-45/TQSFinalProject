package adress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adress.api.CityDeliveryAPI;
import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Location;
import adress.model.Order;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRep;
    @Autowired
    private OrderRepository orderRep;
    private CityDeliveryAPI cityDeliveryAPI = new CityDeliveryAPI();

    public List<Client> getClients() {
        return (List<Client>) clientRep.findAll();
    }

    public Client getInformation(int id) {
        return clientRep.findById(id);
    }

    public Client updateInformation(ClientDTO c1) {
        // We can use the same save() method to update an existing entry in our
        // database. - https://www.baeldung.com/spring-data-crud-repository-save
        // Since we don't know which field was updated, we will "update" them all
        int id = c1.getId();
        Client temp = clientRep.findById(id);
        temp.setName(c1.getName());
        temp.setSname(c1.getSname());
        temp.setSnum(c1.getSnum());
        temp.setCity(c1.getCity());
        temp.setPc1(c1.getPc1());
        temp.setPc2(c1.getPc2());
        return clientRep.save(temp);
    }

    public List<Order> getOrders(int id) {
        return clientRep.findById(id).getOrders(); // and return the ones with the right id
    }

    public Order addOrder(int clientId, OrderDTO order) {
        Order temp = new Order(clientRep.findById(clientId), order.getProds(), order.getDate(), order.getTotal());
        return orderRep.save(temp);
    }

    public Location trackOrder(int clientId, int orderId) {
        return cityDeliveryAPI.track(clientId, orderId);
    }

    public void save() {
        clientRep.save(new Client("Andreia", "2001-02-21", "2", "Sesamee", 1234, 5678, "Narnia"));

    }
}
