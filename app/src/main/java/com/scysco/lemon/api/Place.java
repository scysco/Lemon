package com.scysco.lemon.api;

public class Place {

    private String name;
    private String products;
    private double total;
    private double refund;
    private double benefit;

    public Place(){}
    public Place(String name, String products, double total, double refund, double benefit){
        this.name = name;
        this.products = products;
        this.total = total;
        this.refund = refund;
        this.benefit = benefit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }
}
