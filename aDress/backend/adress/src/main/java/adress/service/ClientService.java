package adress.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import adress.api.CityDeliveryAPI;
import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.mappers.ClientClientDTOMapper;
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

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public List<Client> getClients() {
        return clientRep.findAll();
    }

    public Client getInformation(int id) {
        return clientRep.findById(id);
    }

    public Client updateInformation(ClientDTO newClient) throws UnirestException {
        // We can use the same save() method to update an existing entry in our
        // database. - https://www.baeldung.com/spring-data-crud-repository-save
        // Since we don't know which field was updated, we will "update" them all
        int id = newClient.getId();
        Client temp = clientRep.findById(id);
        temp.setName(newClient.getName());
        temp.setSname(newClient.getSname());
        temp.setSnum(newClient.getSnum());
        temp.setCity(newClient.getCity());
        temp.setPc1(newClient.getPc1());
        temp.setPc2(newClient.getPc2());
        temp.updateLocation();
        return clientRep.save(temp);
    }

    public List<Order> getOrders(int id) {
        return clientRep.findById(id).getOrders(); // and return the ones with the right id
    }

    public Order addOrder(int clientId, OrderDTO order) {
        Order temp = new Order(clientRep.findById(clientId), order.getProds(), order.getDate(), order.getTotal());
        return orderRep.save(temp);
    }

    public Location trackOrder(int orderId) throws UnirestException {
        Optional<Order> o = orderRep.findById(orderId);
        if (o.isPresent()) {
            return cityDeliveryAPI.track(o.get().getTrack());
        }
        return null;
    }

    public Order sendOrderToCityDelivery(Order o1) throws UnirestException {
        return cityDeliveryAPI.send(o1);
    }

    public void save() throws UnirestException {
        clientRep.save(new Client("andreia", "2001-02-21", "97", "rua doutor mari sacramento", 3810, 106, "Aveiro",
                "andreia123@gmail.com"));
    }

    public ClientDTO createClient(ClientDTO c1) throws UnirestException {
        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(c1);

        if (!violations.isEmpty()) {
            return new ClientDTO();
        }
        Client temp = new Client(c1.getName(), c1.getDob(), c1.getSnum(), c1.getSname(), c1.getPc1(), c1.getPc2(),
                c1.getCity(), c1.getEmail());
        return ClientClientDTOMapper.MAPPER.clientToClientDTO(clientRep.save(temp));
    }

}
