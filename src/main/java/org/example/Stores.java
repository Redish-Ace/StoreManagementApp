package org.example;

public class Stores {
    private int id;
    private String name, street;
    private long phone;

    public Stores() {}

    public Stores(int id, String name, String street, long phone) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.phone = phone;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Stores{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", phone=" + phone +
                '}';
    }
}
