package model;

import java.io.Serializable;

public class TripProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String destination;
    private int price;
    private String description;

    public TripProduct() {
        super();
    }

    public TripProduct(String name, String destination, int price, String description) {
        super();
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
