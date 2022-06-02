package adress.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
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

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "client",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Order> orders;

    public Client() {
        // TODO constructor
    }

    public String getName(){
        return null;
    }

    public Client(String name, String dob, String snum, String sname, int pc1, int pc2, String city) {
        this.name = name;
        this.dob = dob;
        this.snum = snum;
        this.sname = sname;
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.city = city;
    }
}
