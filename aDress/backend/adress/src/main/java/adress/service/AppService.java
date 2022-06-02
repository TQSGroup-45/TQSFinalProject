package adress.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import adress.api.ClientRepository;
import adress.api.ProductRepository;
import adress.model.Client;
import adress.model.Order;
import adress.model.Product;

public class AppService {
    @Autowired
    private ClientRepository clientRep;
    @Autowired
    private ProductRepository prodRep;

    public List<Product> listAllProducts() {
        return (List<Product>) prodRep.findAll();
    }

    public Product getProductById(int id) {
        return prodRep.findByProductId(id);
    }

    public Client getInformation(int id) {
        return clientRep.findById(id);
    }

    public Client updateInformation(Client c1) {
        // nao sei se o id será necessário
        return clientRep.save(c1);
    }

    public List<Order> getOrders(int id) {
        return clientRep.findOrdersById(id);
    }

    public Order addOrder(int id, Order o) {
        // complete
        return clientRep.saveOrder(id, o);
    }

    public ClientRepository getClientRep() {
        return this.clientRep;
    }

    public ProductRepository getProdRep() {
        return this.prodRep;
    }

}
