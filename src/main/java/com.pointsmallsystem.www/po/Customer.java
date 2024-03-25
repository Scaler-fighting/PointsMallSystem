package com.pointsmallsystem.www.po;




import java.util.List;

public class Customer extends User{
    private List<String> addressList;

    private int points;
    private List<Product> shoppingCart;
    public Customer(){}

    public Customer(String username, String password, String gender, String phoneNumber, String email){
        super(username,password,gender,phoneNumber,email);
    }

    public Customer(String name, String password, String gender, String phone, String email, List<String> addressList, int points, List<Product> shoppingCart) {
        super(name, password, gender, phone, email);
        this.addressList = addressList;
        this.points = points;
        this.shoppingCart = shoppingCart;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
