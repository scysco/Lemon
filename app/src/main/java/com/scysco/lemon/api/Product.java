package com.scysco.lemon.api;

public class Product {

    private String name;
    private double grams;
    private double cost;
    private double price;
    private String image;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public double getGrams() {return grams;}

    public void setGrams(double grams) {this.grams = grams;}

    public double getCost() {return cost;}

    public void setCost(double cost) {this.cost = cost;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}
}
