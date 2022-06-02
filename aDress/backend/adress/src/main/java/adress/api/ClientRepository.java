package adress.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import adress.model.Client;
import adress.model.Order;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Client findById(int i);

    public List<Order> findOrdersById(int i);

    public Order saveOrder(int i, Order o1);

}
