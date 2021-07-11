package com.example.provathiagolopes.SQLite;

public class Finance {

    private long id;
    private Double price;
    private String name;
    private String type;

    public Finance(long id, Double price, String name, String type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public Finance(Double price, String name, String type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public Finance(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
