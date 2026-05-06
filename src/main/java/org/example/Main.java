package org.example;

import org.example.entity.ProductsEntity;
import org.example.entity.StatusEnum;
import org.example.repository.CacheProducts;
import org.example.repository.ProductsRep;
import org.example.service.ProductsService;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        ProductsEntity pe = new ProductsEntity();
        ProductsService service = new ProductsService();
        pe.setName("Doll");
        pe.setNumber(102);
        pe.setStatus(StatusEnum.In_stock);

        service.saveById(pe);

        ProductsEntity findId = service.getById(4);
        System.out.println("Name: " + findId.getName());
        System.out.println("Number: " + findId.getNumber());
        System.out.println("Status: " + findId.getStatus());

        service.deleteById(11);

        CacheProducts ch = new CacheProducts();
        ch.saveCache(pe);

        System.out.println("Status: " + ch.getCache(0));

        ch.deleteCache(0);
    }
}