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
    public Gender gender;
    @Column(name = "type", nullable = false, length = 50)
    public String type;

    public Product() {
    }

    public Product(String name, double price, String color, Gender gender, String type) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.gender = gender;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

}
