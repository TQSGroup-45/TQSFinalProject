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

    public void save() {
        prodRep.save(new Product("Brown Pants", 39.99, "Brown", "Male", "Pants"));
        prodRep.save(new Product("Blue T-shirt", 29.99, "Blue", "Male", "T-shirt"));
        prodRep.save(new Product("Yellow T-shirt", 19.99, "Yellow", "Undefined", "T-shirt"));
        prodRep.save(new Product("Pringle Socks", 9.99, "Undefined", "Undefined", "Socks"));
        prodRep.save(new Product("Black Dress", 39.99, "Black", "Female", "Dress"));
        prodRep.save(new Product("Green Dress", 59.99, "Green", "Female", "Dress"));
        prodRep.save(new Product("Black Sneakers", 39.99, "Black", "Undefined", "Sneakers"));
        prodRep.save(new Product("White Sneakers", 59.99, "White", "Undefined", "Sneakers"));
    }

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
        // nao sei se o id será necessário
        return clientRep.save(c1);
    }

    public List<Order> getOrders(int id) {
        List<Order> res = new ArrayList<Order>();
        Iterable<Order> temp = orderRep.findAll();
        System.out.println(temp);
        for (Order t : temp) {
            if (t.getClient().getId() == id) {
                res.add(t);
            }
        }
        return res;
    }

    public Order addOrder(Order o) {
        // complete
        return orderRep.save(o);
    }

    public ClientRepository getClientRep() {
        return this.clientRep;
    }

    public ProductRepository getProdRep() {
        return this.prodRep;
    }

}
