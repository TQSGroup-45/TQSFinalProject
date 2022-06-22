package adress.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(generator = "client-sequence-generator")
    @GenericGenerator(name = "client-sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "clientssequence"),
            @Parameter(name = "initial_value", value = "2"),
            @Parameter(name = "increment_size", value = "1")
    })
    @Column(name = "id")
    private int id;
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "dob", nullable = false, length = 50)
    private String dob;
    @Column(name = "snum", nullable = false, length = 50)
    private String snum;
    @Column(name = "sname", nullable = false, length = 50)
    private String sname;
    @Column(name = "pc1", nullable = false, length = 50)
    private int pc1;
    @Column(name = "pc2", nullable = false, length = 50)
    private int pc2;
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    // @JoinColumn(name = "order")
    private List<Order> orders;

    @Column(name = "lat", nullable = false, length = 50)
    private double lat;
    @Column(name = "lng", nullable = false, length = 50)
    private double lng;

    public Client() {
        // empty constructor; only used no create an empty object that will later be
        // filled with the setters
    }

    public Client(String name, String dob, String snum, String sname, int pc1, int pc2, String city, String email)
            throws UnirestException {
        this.name = name;
        this.dob = dob;
        this.snum = snum;
        this.sname = sname;
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.city = city;
        this.orders = new ArrayList<>();
        Location location = new Location(snum, sname, pc1, pc2, city);
        this.lat = location.getLat();
        this.lng = location.getLng();
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDob() {
        return this.dob;
    }

    public String getSnum() {
        return this.snum;
    }

    public String getSname() {
        return this.sname;
    }

    public int getPc1() {
        return this.pc1;
    }

    public int getPc2() {
        return this.pc2;
    }

    public String getCity() {
        return this.city;
    }

    public Location getLocation() {

        return new Location(this.lat, this.lng);
    }

    public void updateLocation() throws UnirestException {
        Location location = new Location(this.snum, this.sname, this.pc1, this.pc2, this.city);
        this.lat = location.getLat();
        this.lng = location.getLng();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setPc1(int pc1) {
        this.pc1 = pc1;
    }

    public void setPc2(int pc2) {
        this.pc2 = pc2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order o) {
        this.orders.add(o);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
