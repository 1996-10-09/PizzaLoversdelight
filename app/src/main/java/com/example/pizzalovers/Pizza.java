package com.example.pizzalovers;

public class Pizza {
    int pizzaId;
    String name;
    String details;
    float price;
   int imageURL;

    public Pizza(int pizzaId, String name, String details, float price, int imageURL) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.details = details;
        this.price = price;
        this.imageURL = imageURL;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }
}
