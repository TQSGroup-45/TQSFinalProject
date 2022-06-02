package adress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false, length = 50)
    public String name;
    @Column(name = "price")
    public double price;
    @Column(name = "color", nullable = false, length = 50)
    public String color;
    @Column(name = "gender", nullable = false, length = 50)
    public String gender;
    @Column(name = "type", nullable = false, length = 50)
    public String type;

    public Product() {
        // TODO constructor
    }

    public Product(int id, String name, double price, String color, String gender, String type) {
        // TODO constructor
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

}
