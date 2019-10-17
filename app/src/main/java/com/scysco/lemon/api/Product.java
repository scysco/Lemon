package com.scysco.lemon.api;

public class Product {
    private String name;
    private double grams;
    private double cost;
    private double price;

    public Product(){
        this.name = null;
        this.grams = 0;
        this.cost = 0;
        this.price = 0;
    }

    public Product(String name, double grams, double cost, double price){
        this.name = name;
        this.grams = grams;
        this.cost = cost;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
