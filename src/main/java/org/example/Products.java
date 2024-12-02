package org.example;

public class Products {
    private int id, quantity;
    private String name, type, store;
    private double price, sale;

    public Products(){}

    public Products(int id, String name, String type, int quantity, double price, String store) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.store = store;
    }

    public Products(int id, String name, String type, int quantity, double price, double sale, String store) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", store='" + store + '\'' +
                ", price=" + price +
                '}';
    }
}
