package com.dataeye.redis;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

public class RedisExpireHelper {

	private static ConcurrentHashMap<String, Integer> expireClusterMap = new ConcurrentHashMap<String, Integer>();
	@Deprecated
	private static ConcurrentHashMap<String, Integer> expireMap = new ConcurrentHashMap<String, Integer>();

	static {
		new RedisKeyExpirer().start();
	}

	public static void expireKey(String key, int ttl) {
		expireClusterMap.put(key, ttl);
	}

	// 处理集群 | 单机模式下的过期key
	@Deprecated
	public static void expireKey(String key, int ttl, boolean isCluster) {
		if (isCluster) {
			expireClusterMap.put(key, ttl);
		} else {
			expireMap.put(key, ttl);
		}
	}

	static class RedisKeyExpirer extends Thread {
		RedisClusterClient redisClient = RedisClusterClient.getInstance();
		Set<String> toDelKeys = new HashSet<String>();

		public void run() {
			while (true) {
				try {
					int size = expireClusterMap.size();
					long sleepTime = getSleepTime(size);
					// expire all the keys in expireClusterMap
					for (Entry<String, Integer> entry : expireClusterMap.entrySet()) {
						redisClient.expire(entry.getKey(), entry.getValue());
						toDelKeys.add(entry.getKey());
					}
					// clear the keys which had been expired in expireClusterMap
					for (String expiredKeys : toDelKeys) {
						expireClusterMap.remove(expiredKeys);
					}
					// clear toDelKeys
					toDelKeys.clear();
					Thread.sleep(sleepTime * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	public static int getSleepTime(int mapSize) {
		if (mapSize < 1000)
			return 5 * 60; // sleep for 10m
		if (mapSize < 10000)
			return 2 * 60; // sleep for 5m
		return 1 * 60; // sleep for 1m
	}

}
