package adress.controller;

import java.util.ArrayList;
import java.util.List;

import adress.model.Client;
import adress.model.Order;
import adress.model.Product;

public class AppController {
    public List<Product> listAllProducts() {
        return new ArrayList<>();
    }

    public Product getProductById() {
        return new Product();
    }

    public void addOrder(int id) {
        // TODO addProductToCart
    }

    public List<Order> getOrders(int id) {
        return new ArrayList<>();
    } 
    public Client getInformation(int id){
        return new Client();
    }
    public void updateInformation(int id){
        //TODO updateInformation
    }
}

 

