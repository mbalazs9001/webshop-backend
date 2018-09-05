package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoPSQL;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/webshop/product-list"})
public class ProductListController extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = new ProductDaoPSQL();
        String supplier = req.getParameter("supplier");
        String productCategory = req.getParameter("product-category");
        List<Product> products = queryProductData(productDataStore, supplier, productCategory);
        PrintWriter out = resp.getWriter();
        out.println(gson.toJson(products));
    }

    private List<Product> queryProductData(ProductDao productDataStore, String supplier, String productCategory) {
        List<Product> products;
        if (supplier != null && productCategory != null) {
            products = productDataStore.getBySupplierAndCategory(
                    Integer.valueOf(supplier), Integer.valueOf(productCategory)
            );
        } else if (productCategory != null) {
            products = productDataStore.getByProductCategory(Integer.valueOf(productCategory));
        } else if (supplier != null) {
            products = productDataStore.getBySupplier(Integer.valueOf(supplier));
        } else {
            products = productDataStore.getAll();
        }
        return products;
    }

}
