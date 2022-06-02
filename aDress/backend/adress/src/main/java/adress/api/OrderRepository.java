package adress.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import adress.model.Client;
import adress.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}