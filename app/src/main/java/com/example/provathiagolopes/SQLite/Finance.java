package com.example.provathiagolopes.SQLite;

public class Finance {

    private long id;
    private float price;
    private String name;
    private String type;

    public Finance(long id, float price, String name, String type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }
    public Finance(float price, String name, String type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
