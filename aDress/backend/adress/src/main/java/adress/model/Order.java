package adress.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany
    @JoinColumn(name = "products_id")
    private List<Product> prods;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int id;

    @Column(name = "date", nullable = false, length = 50)
    private String date;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private String status = "In route";

    public Order() {
    }

    public Order(Client client, int id, List<Product> prods, String date, double total) {
        this.client = client;
        this.id = id;
        this.prods = prods;
        this.date = date;
        this.total = total;
    }

    public Order(Client client, List<Product> prods, String date, double total) {
        this.client = client;
        this.prods = prods;
        this.date = date;
        this.total = total;
    }

    public Client getClient() {
        return this.client;
    }

    public List<Product> getProds() {
        return this.prods;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public double getTotal() {
        return this.total;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                " client='" + getClient() + "'" +
                ", prods='" + getProds() + "'" +
                ", id='" + getId() + "'" +
                ", date='" + getDate() + "'" +
                ", total='" + getTotal() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }

}
