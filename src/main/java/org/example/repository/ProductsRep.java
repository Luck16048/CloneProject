package org.example.repository;

import org.example.config.ConnectDb;
import org.example.entity.ProductsEntity;
import org.example.entity.StatusEnum;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.SQLException;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class ProductsRep {
    public ProductsEntity getByid(int id) throws SQLException {
        DSLContext dsl = ConnectDb.getDSL();

        org.jooq.Record record = dsl.select()
                .from("products")
                .where(field("id").eq(id))
                .fetchOne();

        if (record != null) {
            ProductsEntity pe = new ProductsEntity();
            pe.setId(record.get("id", Integer.class));
            pe.setName(record.get("name", String.class));
            pe.setNumber(record.get("number", Integer.class));
            pe.setStatus(StatusEnum.valueOf(record.get("status", String.class)));

            return pe;
        }
        return null;
    }

    public void deleteById(int id) throws SQLException {
        DSLContext dsl = ConnectDb.getDSL();

        dsl.deleteFrom(table("products"))
                .where(field("id").eq(id))
                .execute();
    }

    public void save(ProductsEntity pe) throws SQLException {
        DSLContext dsl = ConnectDb.getDSL();

        dsl.insertInto(table("products"),
                        field("name"),
                        field("number"),
                        field("status"))
                .values(pe.getName(),
                        pe.getNumber(),
                        pe.getStatus().name())
                .execute();
    }


    public void updateById(ProductsEntity pe) throws SQLException {
        DSLContext dsl = ConnectDb.getDSL();

        dsl.update(table("products"))
                .set(field("name"), pe.getName())
                .set(field("number"), pe.getNumber())
                .set(field("status"), pe.getStatus().name())
                .where(field("id").eq(pe.getId()))
                .execute();
    }
}
