package adress.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Location;
import adress.model.Order;
import adress.service.ClientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
        service.save(); // This will enter the initial data into the database (like products)
    }

    @GetMapping(path = "/clients/{id}/orders")
    // It will get all orders from a certain client using their ID
    public List<Order> getOrders(@PathVariable(value = "id") int clientid) {
        return service.getOrders(clientid);
    }

    @PostMapping(path = "/clients/{id}/orders")
    public ResponseEntity<Order> addOrder(@PathVariable(value = "id") int id,
            @RequestBody OrderDTO order) {
        HttpStatus status = HttpStatus.CREATED;
        Order o = service.addOrder(id, order);
        return new ResponseEntity<>(o, status);
    }

    @GetMapping(path = "/clients")
    public List<Client> getClients() {
        return service.getClients();
    }

    @GetMapping(path = "/clients/{id}")
    public Client getInformation(@PathVariable(value = "id") int id) {
        return service.getInformation(id);
    }

    @PutMapping(path = "/clients/{id}")
    public ResponseEntity<Client> updateInformation(@RequestBody ClientDTO c1) {
        HttpStatus status = HttpStatus.ACCEPTED;
        Client o = service.updateInformation(c1);
        return new ResponseEntity<>(o, status);
    }

    @GetMapping(path = "/clients/{id}/orders/{orderid}")
    public Location getOrderLocation(@PathVariable(value = "id") int id, @PathVariable(value = "orderid") int orderid) {
        return service.trackOrder(id, orderid);
    }

    @PostMapping(path = "/clients", produces="application/json")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO client ){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClient(client));
    }
}
