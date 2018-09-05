package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private String currencyString;
    private Currency defaultCurrency;
    private String imageName;
    private int productCategoryId;
    private int supplierId;


    public Product(String name, String description, float defaultPrice, String currencyString, String imageName,
                   int productCategoryId, int supplierId) {
        super(name, description);
        this.imageName = imageName;
        this.setPrice(defaultPrice, currencyString);
        this.productCategoryId = productCategoryId;
        this.supplierId = supplierId;

    }

    public Product(int id, String name, String description, float defaultPrice, String currencyString, String imageName,
                   int productCategoryId, int supplierId) {
        super(id, name, description);
        this.imageName = imageName;
        this.setPrice(defaultPrice, currencyString);
        this.productCategoryId = productCategoryId;
        this.supplierId = supplierId;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public Currency getDefaultCurrency() {
        if (defaultCurrency == null) {
            defaultCurrency = Currency.getInstance(currencyString);
        }
        return defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public int getProductCategory() {
        return productCategoryId;
    }

    public int getSupplier() {
        return supplierId;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "defaultPrice=" + defaultPrice +
                ", currencyString='" + currencyString + '\'' +
                ", defaultCurrency=" + defaultCurrency +
                ", imageName='" + imageName + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", supplierId=" + supplierId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
