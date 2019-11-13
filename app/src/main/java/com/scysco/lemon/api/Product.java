package com.scysco.lemon.api;

public class Product {

    public String name;
    public double grams;
    public double cost;
    public double price;
    public String image;

    public Product(){
        this.name = null;
        this.grams = 0;
        this.cost = 0;
        this.price = 0;
        this.image = null;
    }

    public Product(String name, double grams, double cost, double price, String image){
        this.name = name;
        this.grams = grams;
        this.cost = cost;
        this.price = price;
        this.image = image;
    }
}
