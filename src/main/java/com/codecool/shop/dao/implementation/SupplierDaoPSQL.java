package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierDaoPSQL implements SupplierDao {
    private ModelAssembler<Supplier> assembler = rs -> new Supplier(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description")
    );


    @Override
    public int add(Supplier supplier) {
        return QueryProcessor.fetchOne(
                "INSERT INTO suppliers (name, description) VALUES (?,?) RETURNING id;",
                rs -> rs.getInt("id"),
                supplier.getName(), supplier.getDescription()
        );
    }

    @Override
    public Supplier find(int id) {
        return QueryProcessor.fetchOne("SELECT * FROM suppliers WHERE id = ?::INTEGER;",assembler,String.valueOf(id));
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM suppliers WHERE id = ?::INTEGER;", String.valueOf(id));
    }

    @Override
    public List<Supplier> getAll() {
        return QueryProcessor.fetchAll("SELECT * FROM suppliers;", assembler);
    }
}
