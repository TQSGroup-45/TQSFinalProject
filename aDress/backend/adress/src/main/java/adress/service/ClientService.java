package adress.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return  clientRep.findAll();
    }

    public Client getInformation(int id) {
        return clientRep.findById(id);
    }

    public Client updateInformation(ClientDTO newClient) {
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
        clientRep.save(new Client("Andreia", "2001-02-21", "2", "Sesamee", 1234, 5678, "Narnia", "andreia@gmail.com"));

    }

    public ClientDTO createClient(ClientDTO c1){
        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(c1);

        if(!violations.isEmpty()){
            return new ClientDTO();
        }

        Client temp = new Client();
        temp.setName(c1.getName());
        temp.setEmail(c1.getEmail());
        temp.setDob(c1.getDob());
        temp.setSname(c1.getSname());
        temp.setSnum(c1.getSnum());
        temp.setCity(c1.getCity());
        temp.setPc1(c1.getPc1());
        temp.setPc2(c1.getPc2());

        return ClientClientDTOMapper.MAPPER.clientToClientDTO( clientRep.save(temp) );
    }

}
