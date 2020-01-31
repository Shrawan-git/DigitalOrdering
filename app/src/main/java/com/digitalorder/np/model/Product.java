package com.digitalorder.np.model;

public class Product {
    String Foodname,Price,Category;

    public Product(String foodname, String price, String category) {
        Foodname = foodname;
        Price = price;
        Category = category;
    }

    public String getFoodname() {
        return Foodname;
    }

    public void setFoodname(String foodname) {
        Foodname = foodname;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
