package com.digitalorder.np.model;

public class OrderMod {
    private String image;
    private String foodName;
    private String foodPrice;
    private String foodCategory;
    private String foodDescription;

    public OrderMod(String image, String foodName, String foodPrice, String foodCategory, String foodDescription) {
        this.image = image;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
        this.foodDescription = foodDescription;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
