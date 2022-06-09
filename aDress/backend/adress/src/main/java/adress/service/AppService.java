package adress.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adress.api.ClientRepository;
import adress.api.OrderRepository;
import adress.api.ProductRepository;
import adress.dto.ClientDTO;
import adress.dto.OrderDTO;
import adress.model.Client;
import adress.model.Gender;
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
        List<Order> res = new ArrayList<>();
        Iterable<Order> temp = orderRep.findAll(); // we will find every order
        for (Order t : temp) {
            if (t.getClient().getId() == id) { // filter by the client id
                res.add(t);
            }
        }
        return res; // and return the ones with the right id
    }

    public Order addOrder(OrderDTO order) {
        return orderRep.save(new Order(order.getClient(), order.getProds(), order.getDate(), order.getTotal()));
    }

    public void save() {
        prodRep.save(new Product("Brown Pants", 39.99, "Brown", Gender.MALE, "Pants"));
        prodRep.save(new Product("Blue T-shirt", 29.99, "Blue", Gender.MALE, "T-shirt"));
        prodRep.save(new Product("Yellow T-shirt", 19.99, "Yellow", Gender.UNDEFINED, "T-shirt"));
        prodRep.save(new Product("Pringle Socks", 9.99, "Undefined", Gender.UNDEFINED, "Socks"));
        prodRep.save(new Product("Black Dress", 39.99, "Black", Gender.FEMALE, "Dress"));
        prodRep.save(new Product("Green Dress", 59.99, "Green", Gender.FEMALE, "Dress"));
        prodRep.save(new Product("Black Sneakers", 39.99, "Black", Gender.UNDEFINED, "Sneakers"));
        prodRep.save(new Product("White Sneakers", 59.99, "White", Gender.UNDEFINED, "Sneakers"));
        clientRep.save(new Client("Andreia", "2001-02-21", "2", "Sesamee", 1234, 5678, "Narnia"));

    }

    public Client createClient(Client c){
        return null;
    }
}
