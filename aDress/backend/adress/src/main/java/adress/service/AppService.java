package adress.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.api.ProductRepository;
import adress.model.Client;
import adress.model.Order;
import adress.model.Product;

@Service
public class AppService {
    @Autowired
    private ClientRepository clientRep;
    @Autowired
    private ProductRepository prodRep;
    @Autowired
    private OrderRepository orderRep;

    public List<Product> listAllProducts() {
        return (List<Product>) prodRep.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return prodRep.findById(id);
    }

    public Client getInformation(int id) {
        return clientRep.findById(id);
    }

    public Client updateInformation(Client c1) {
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
        List<Order> res = new ArrayList<>();
        Iterable<Order> temp = orderRep.findAll(); // we will find every order
        for (Order t : temp) {
            if (t.getClient().getId() == id) { // filter by the client id
                res.add(t);
            }
        }
        return res; // and return the ones with the right id
    }

    public Order addOrder(Order o) {
        return orderRep.save(o);
    }

    public void save() {
        String male = "Male";
        String undefined = "Undefined";
        String female = "Female";
        prodRep.save(new Product("Brown Pants", 39.99, "Brown", male, "Pants"));
        prodRep.save(new Product("Blue T-shirt", 29.99, "Blue", male, "T-shirt"));
        prodRep.save(new Product("Yellow T-shirt", 19.99, "Yellow", undefined, "T-shirt"));
        prodRep.save(new Product("Pringle Socks", 9.99, undefined, undefined, "Socks"));
        prodRep.save(new Product("Black Dress", 39.99, "Black", female, "Dress"));
        prodRep.save(new Product("Green Dress", 59.99, "Green", female, "Dress"));
        prodRep.save(new Product("Black Sneakers", 39.99, "Black", undefined, "Sneakers"));
        prodRep.save(new Product("White Sneakers", 59.99, "White", undefined, "Sneakers"));
        clientRep.save(new Client("Andreia", "2001-02-21", "2", "Sesamee", 1234, 5678, "Narnia"));

    }
}
