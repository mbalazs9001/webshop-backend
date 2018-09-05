package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.Product;

import java.util.List;

public class ProductDaoPSQL implements ProductDao {

    private ModelAssembler<Product> assembler = rs -> new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getFloat("price"),
            rs.getString("currency"),
            rs.getString("image"),
            rs.getInt("product_category_id"),
            rs.getInt("supplier_id")
    );

    @Override
    public int add(Product product) {
        return QueryProcessor.fetchOne(
                "INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id)" +
                        " VALUES (?, ?, CAST(? AS DOUBLE PRECISION), ?, ?, ?::INTEGER, ?::INTEGER) RETURNING id;",
                rs -> rs.getInt("id"),
                product.getName(),
                product.getDescription(),
                String.valueOf(product.getDefaultPrice()),
                product.getDefaultCurrency().toString(),
                product.getImageName(),
                String.valueOf(product.getProductCategory()),
                String.valueOf(product.getSupplier())
        );
    }

    @Override
    public Product find(int id) {
        return QueryProcessor.fetchOne("SELECT * FROM products WHERE id = ?::INTEGER;", assembler, String.valueOf(id));
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM products WHERE id = ?::INTEGER;", String.valueOf(id));
    }

    @Override
    public List<Product> getAll() {
        return QueryProcessor.fetchAll("SELECT * FROM products ORDER BY id;", assembler);
    }

    @Override
    public List<Product> getBySupplier(int supplierId) {
        return QueryProcessor.fetchAll(
                "SELECT * FROM products WHERE supplier_id = ?::INTEGER;",
                assembler, String.valueOf(supplierId)
        );
    }

    @Override
    public List<Product> getByProductCategory(int productCategoryId) {
        return QueryProcessor.fetchAll(
                "SELECT * FROM products WHERE product_category_id = ?::INTEGER;",
                assembler, String.valueOf(productCategoryId)
        );
    }

    @Override
    public List<Product> getBySupplierAndCategory(int supplierId, int productCategoryId) {
        return QueryProcessor.fetchAll(
                "SELECT * FROM products WHERE product_category_id = ?::INTEGER AND supplier_id = ?::INTEGER;",
                assembler, String.valueOf(productCategoryId), String.valueOf(supplierId)
        );
    }
}
