package adress.controller;

import java.util.List;
import java.util.Optional;

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
import adress.model.Order;
import adress.model.Product;
import adress.service.AppService;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    private AppService service;

    public AppController(AppService service) {
        this.service = service;
        service.save(); // This will enter the initial data into the database (like products)
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/store")
    public List<Product> listAllProducts() {
        return service.listAllProducts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/product/{id}")
    public Optional<Product> getProductById(@PathVariable(value = "id") int id) {
        return service.getProductById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/orders/{id}")
    // It will get all orders from a certain client using their ID
    public List<Order> getOrders(@PathVariable(value = "id") int id) {
        return service.getOrders(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/orders")
    public ResponseEntity<Order> addOrder(
            @RequestBody OrderDTO order) {
        HttpStatus status = HttpStatus.CREATED;
        Order o = service.addOrder(order);
        return new ResponseEntity<>(o, status);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/profile/{id}")
    public Client getInformation(@PathVariable(value = "id") int id) {
        return service.getInformation(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/profile/{id}")
    public ResponseEntity<Client> updateInformation(@RequestBody ClientDTO c1) {
        HttpStatus status = HttpStatus.ACCEPTED;
        Client o = service.updateInformation(c1);
        return new ResponseEntity<>(o, status);
    }
}
