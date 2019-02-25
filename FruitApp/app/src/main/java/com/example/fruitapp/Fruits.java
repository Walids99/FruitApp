package com.example.fruitapp;

public class Fruits {
    private String type;
    private String price;
    private String weight;

    public Fruits(String type, String weight, String price) {
        this.type = type;
        this.price = price;
        this.weight = weight;

    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }
    public String getWeight() {
        return weight;
    }
}
