package com.pointsmallsystem.www.po;

import java.util.List;

public class Order {
    private List<Product> products;
    private Customer customer;
    private double totalAmount;
    private String paymentStatus;
    private double paymentAmount;

    public Order(){}

    public Order(List<Product> products, Customer customer, double totalAmount, String paymentStatus, double paymentAmount) {
        this.products = products;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
