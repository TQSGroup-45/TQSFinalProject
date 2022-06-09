package adress.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import adress.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    public Client findById(int i);

    List<Client> findAll();

}
