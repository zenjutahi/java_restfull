package com.learning.rest.webservice.restfullwebservices.model;

public class Item {
    private int id;
    private String name;
    private Integer quantity;
    private Integer price;
    public Item() {
    }

    public Item(int id, String name, Integer quantity, Integer price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }
}
