package org.example.repository;

import org.example.config.ConnectRedis;
import org.example.entity.ProductsEntity;
import org.example.entity.StatusEnum;
import redis.clients.jedis.Jedis;

public class CacheProducts {
    public void saveCache(ProductsEntity pe) {
        try (Jedis jedis = ConnectRedis.getConnectionRedis()) {
            jedis.set("products:" + pe.getId() + ":status", pe.getStatus().name());
        }
    }

    public StatusEnum getCache(int id) {
        try (Jedis jedis = ConnectRedis.getConnectionRedis()) {
            String value = jedis.get("products:" + id + ":status");
            return value != null ? StatusEnum.valueOf(value) : null;
        }
    }

    public void deleteCache(int productsId) {
        try (Jedis jedis = ConnectRedis.getConnectionRedis()) {
            jedis.del("products:" + productsId + ":status");
        }
    }
}
