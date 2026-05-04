package org.example.config;

import redis.clients.jedis.Jedis;

public class ConnectRedis {
    private static final String HOST = "localhost";
    private static final int PORT = 6379;

    public static Jedis getConnectionRedis(){
        return new Jedis(HOST, PORT);
    }
}
