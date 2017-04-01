package com.dataeye.redis.client;

import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.dataeye.redis.RedisClusterClient;

public class RedisClient {
	
	public static void main(String[] args) {

		RedisClusterClient redis = RedisClusterClient.getInstance();

		// redis.smembers

		// 从集群获取所有redis单机，然后遍历所有key

		Map<String, JedisPool> jedisPoolMap = redis.getClusterNodes();
		for (Entry<String, JedisPool> e : jedisPoolMap.entrySet()) {
			
			Jedis jedis = e.getValue().getResource();

			for (String key : jedis.keys("adt:*")) {
				System.out.println(key);
			}
		}
	}
}
