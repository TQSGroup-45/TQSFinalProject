package adress.dto;

import java.util.List;

import adress.model.Client;
import adress.model.Product;

public class OrderDTO {
    private Client client;
    private List<Product> prods;
    private int id;
    private String date;
    private double total;
    private String status = "In route";

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProds() {
        return this.prods;
    }

    public void setProds(List<Product> prods) {
        this.prods = prods;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
