package adress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adress.api.ProductRepository;
import adress.model.Gender;
import adress.model.Product;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository prodRep;

    public List<Product> listAllProducts() {
        return (List<Product>) prodRep.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return prodRep.findById(id);
    }

    public void save() {
        prodRep.save(new Product("Brown Pants", 39.99, "Brown", Gender.MALE, "Pants"));
        prodRep.save(new Product("Blue T-shirt", 29.99, "Blue", Gender.MALE, "T-shirt"));
        prodRep.save(new Product("Yellow T-shirt", 19.99, "Yellow", Gender.UNDEFINED, "T-shirt"));
        prodRep.save(new Product("Pringle Socks", 9.99, "Undefined", Gender.UNDEFINED, "Socks"));
        prodRep.save(new Product("Black Dress", 39.99, "Black", Gender.FEMALE, "Dress"));
        prodRep.save(new Product("Green Dress", 59.99, "Green", Gender.FEMALE, "Dress"));
        prodRep.save(new Product("Black Sneakers", 39.99, "Black", Gender.UNDEFINED, "Sneakers"));
        prodRep.save(new Product("White Sneakers", 59.99, "White", Gender.UNDEFINED, "Sneakers"));

    }

}
