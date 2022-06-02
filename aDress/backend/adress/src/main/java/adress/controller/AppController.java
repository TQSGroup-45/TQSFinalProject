package adress.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import adress.api.ClientRepository;
import adress.api.ProductRepository;
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
        service.save();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/store")
    public List<Product> listAllProducts() {
        List<Product> temp = service.listAllProducts();
        return temp;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/product/{id}")
    public Optional<Product> getProductById(@PathVariable(value = "id") int id) {
        return service.getProductById(id);
    }

    @GetMapping(path = "/orders/{id}")
    public List<Order> getOrders(@PathVariable(value = "id") int id) {
        return service.getOrders(id);
    }

    @PostMapping(path = "/orders/{id}")
    public ResponseEntity<Order> addOrder(@PathVariable(value = "id") int id,
            @RequestBody Order order) {
        System.out.println("" + id + order);
        HttpStatus status = HttpStatus.CREATED;
        Order o = service.addOrder(order);
        return new ResponseEntity<Order>(o, status);
    }

    @GetMapping(path = "/profile/{id}")
    public Client getInformation(@PathVariable(value = "id") int id) {
        return service.getInformation(id);
    }

    @PutMapping(path = "/profile/{id}")
    public ResponseEntity<Client> updateInformation(@RequestBody Client c1) {
        HttpStatus status = HttpStatus.ACCEPTED;
        Client o = service.updateInformation(c1);
        return new ResponseEntity<Client>(o, status);
    }
}
