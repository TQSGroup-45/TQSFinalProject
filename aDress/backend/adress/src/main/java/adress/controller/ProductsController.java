package adress.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adress.model.Product;
import adress.service.ProductsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductsController {

    private ProductsService service;

    public ProductsController(ProductsService service) {
        this.service = service;
        service.save(); // This will enter the initial data into the database (like products)
    }

    @GetMapping(path = "/products")
    public List<Product> listAllProducts() {
        return service.listAllProducts();
    }

    @GetMapping(path = "/products/{id}")
    public Optional<Product> getProductById(@PathVariable(value = "id") int id) {
        return service.getProductById(id);
    }

}
