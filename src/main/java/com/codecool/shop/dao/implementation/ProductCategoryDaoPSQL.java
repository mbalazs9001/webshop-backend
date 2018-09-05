package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryDaoPSQL implements ProductCategoryDao {

    private ModelAssembler<ProductCategory> assembler = rs -> new ProductCategory(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("department"),
            rs.getString("description")
    );


    @Override
    public int add(ProductCategory category) {
        return QueryProcessor.fetchOne(
                "INSERT INTO product_category(name, department, description) VALUES (?, ?, ?) RETURNING id;",
                rs -> rs.getInt("id"),
                category.getName(),category.getDepartment(),category.getDescription());
    }

    @Override
    public ProductCategory find(int id) {
        return QueryProcessor.fetchOne("SElECT * FROM product_category WHERE id = ?::INTEGER;", assembler,
                                        String.valueOf(id));
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM product_category WHERE id = ?::INTEGER;", String.valueOf(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return QueryProcessor.fetchAll("SELECT * FROM product_category;", assembler);
    }
}

