package org.example.service;

import org.example.entity.ProductsEntity;
import org.example.repository.ProductsRep;

import java.sql.SQLException;

public class ProductsService {
    private final ProductsRep rep = new ProductsRep();

    public ProductsEntity getById(int id) throws SQLException {
        return rep.getByid(id);
    }

    public void deleteById(int id) throws SQLException {
        rep.deleteById(id);
    }

    public void saveById(ProductsEntity pe) throws SQLException {
        rep.save(pe);
    }

    public void updateById(ProductsEntity pe) throws SQLException {
        rep.updateById(pe);
    }
}
