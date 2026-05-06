package org.example.controller;

import io.javalin.Javalin;
import org.example.entity.ProductsEntity;
import org.example.entity.StatusEnum;
import org.example.service.ProductsService;

import java.beans.Introspector;
import java.sql.SQLException;
import java.util.Map;

public class ProductsCont {
    public static void main(String[] args) throws SQLException {
        Javalin app = Javalin.create().start(9000);
        ProductsService service = new ProductsService();

        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).result("Error: " + e.getMessage());
        });

        app.get("/products/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(service.getById(id));
        });

        app.delete("/products/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            service.deleteById(id);
            ctx.result("Deleted");
        });

        app.post("/products", ctx -> {
            ProductsEntity pe = ctx.bodyAsClass(ProductsEntity.class);
            service.saveById(pe);
            ctx.result("Saved");
        });

        app.patch("/products/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ProductsEntity pe = service.getById(id);
            Map<String, Object> updates = ctx.bodyAsClass(Map.class);
            if (updates.containsKey("name")) {
                pe.setName((String) updates.get("name"));
            }
            if (updates.containsKey("number")) {
                pe.setNumber((int) updates.get("number"));
            }
            if (updates.containsKey("status")) {
                pe.setStatus(StatusEnum.valueOf((String) updates.get("status")));
            }
            service.updateById(pe);
            ctx.result("Updated");
        });
    }
}
