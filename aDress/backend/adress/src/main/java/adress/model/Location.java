package adress.model;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Location {
    private double lat;
    private double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Location() {
        // e suposto ficar vazio
    }

    public Location(String snum, String sname, int pc1, int pc2, String city) throws UnirestException {
        String request = "https://maps.googleapis.com/maps/api/geocode/json?address=" + snum + "+"
                + sname.replace(" ", "+") + "+,+"
                + city.replace(" ", "+") + "+" + pc1 + "+,+" + pc2
                + ",Portugal&key=AIzaSyAgaH3w-lIbEwm5rWu2c-MIvziIWqoHhF8";
        JsonNode res = Unirest.get(request).asJson().getBody();
        String[] geocode = res.getArray().toString().split("location");
        // O JsonNode do Unirest nao deixa abrir objetos dentro de objetos etc por isso
        // editei a string
        String[] temp = geocode[1].split("lng\":")[1].split(",\"lat\":");
        this.lat = Double.parseDouble(temp[1].split("}")[0]);
        this.lng = Double.parseDouble(temp[0]);
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "{" +
                " lat='" + getLat() + "'" +
                ", lng='" + getLng() + "'" +
                "}";
    }

}
