package com.codecool.shop.model;


public class ProductCategory extends BaseModel {

    private String department;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
    }

    public ProductCategory(int id, String name, String department, String description) {
        super(id, name, description);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "department='" + department + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}