package adress.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import adress.model.Client;
import adress.model.Order;
import java.util.Collections;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Integer> {

    public Client findById(int i);

    public List<Order> findOrdersById(int i);

    public Client save(Client c1);

    public Order saveOrder(int i, Order o1);

    public List<Client> findAll();
}
