package com.example.productapi.model;

public class Product {
    private String barcode;
    private String item;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer available;

    // Constructors
    public Product() {
    }

    public Product(String barcode, String item, String category, Integer price, Integer discount, Integer available) {
        this.barcode = barcode;
        this.item = item;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.available = available;
    }

    // Getters e Setters
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}