package adress.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import adress.model.Product;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}