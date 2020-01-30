package com.digitalorder.np.model;

public class Contacts {
    private String name;
    private String price;
    private String category;
    private int imageId;

    public Contacts(String name, String price, String category, int imageId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
