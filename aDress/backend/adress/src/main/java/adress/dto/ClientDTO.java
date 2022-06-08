package adress.dto;

public class ClientDTO {
    private int id;
    private String name;
    private String dob;
    private String snum;
    private String sname;
    private int pc1;
    private int pc2;
    private String city;

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

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSnum() {
        return this.snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getSname() {
        return this.sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getPc1() {
        return this.pc1;
    }

    public void setPc1(int pc1) {
        this.pc1 = pc1;
    }

    public int getPc2() {
        return this.pc2;
    }

    public void setPc2(int pc2) {
        this.pc2 = pc2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
