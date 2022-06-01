package adress.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import adress.model.Client;
import adress.model.Order;
import java.util.Collections;

public class ClientRepository {// extends CrudRepository<Client, Integer> {

    public Client findById(int i) {
        return null;
    }

    public List<Order> findOrdersById(int i) {
        return Collections.emptyList();
    }

    public Client save(Client c1) {
        return null;
    }

    public Order saveOrder(int i, Order o1) {
        return null;
    }
}
