package com.scysco.lemon.api;

public class History {
    private String place;
    private String product;
    private int gramos;
    private int cost;
    private int price;
    private  int benefit;

    public History(){}
    public History(String place, String product, int gramos, int cost, int price, int benefit){
        this.place=place;
        this.product=product;
        this.gramos=gramos;
        this.cost=cost;
        this.price=price;
        this.benefit=benefit;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getGramos() {
        return gramos;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }
}
//FDF