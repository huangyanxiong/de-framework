package com.dataeye.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * <pre/>
 * Redis缓存集群客户端
 * 
 * @author sam.xie
 * @date 2015年5月18日 下午2:12:33
 * @version 1.0
 */
public class RedisClusterClient implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RedisClusterClient.class);

	/** Redis集群 */
	private static JedisCluster jedisCluster;

	/** 记录累计执行的命令和总耗时<commoand,cost>，一般作调试用 */
	private ConcurrentHashMap<String, AtomicLong> costMap = new ConcurrentHashMap<String, AtomicLong>();
	/** 记录累计执行的命令和总次数<commoand,times>，一般作调试用 */
	private ConcurrentHashMap<String, AtomicLong> timesMap = new ConcurrentHashMap<String, AtomicLong>();

	private RedisClusterClient() {
	};

	static {
		jedisCluster = RedisConnectionUtils.getJedisCluster();
	}

	public static RedisClusterClient getInstance() {
		return new RedisClusterClient();
	}

	/**
	 * 根据 key 从缓存中获取数据
	 * 
	 * @see #get(String,boolean)
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return jedisCluster.get(key);
	}

	/**
	 * 根据 key 从缓存中获取数据（指定是否从master读取最新数据）
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key, boolean fromMaster) {
		if (fromMaster) {
			/**
			 * 指定从master redis读取记录 排除可能存在master短时间有变动未同步到slave，从库中读取的并不是最新内容
			 */
			return jedisCluster.get(key);
		} else {
			return get(key);
		}
	}

	/**
	 * 加入新的key-value键值对（Set if Not eXists，只在key值不存在的情况下才设置） 如果不指定时间，将使用默认seconds
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long setnx(String key, String value) {
		return jedisCluster.setnx(key, value);
	}

	/**
	 * <pre>
	 * 加入新的key-value键值对（Set if Not eXists，只在key值不存在的情况下才设置）
	 * 设置失败（key已存在），返回0；
	 * 设置成功（key不存在），返回1
	 * @param key
	 * @param value
	 * @return
	 */
	public Long setnx(String key, String value, int seconds) {
		long result = jedisCluster.setnx(key, value);
		if (result == 1) {
			/*
			 * 设置新key后才设置过期时间。 如果key值已存在，仍然采用之前设置的过期时间。
			 */
			jedisCluster.expire(key, seconds);
		}
		return result;
	}

	/**
	 * 加入新的key-value键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}

	/**
	 * 加入新的key-value键值对
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value, int seconds) {
		jedisCluster.set(key, value);
		jedisCluster.expire(key, seconds);
	}

	/**
	 * 删除指定key
	 * 
	 * @param key
	 * @return
	 */
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	/**
	 * 向list头部加入值
	 * 
	 * @param key
	 * @param strings
	 * @return
	 */
	public Boolean lpush(String key, String... strings) {
		for (String string : strings) {
			jedisCluster.lpush(key, string);
		}
		return true;
	}

	/**
	 * 向list尾部加入值
	 * 
	 * @param key
	 * @param strings
	 * @return
	 */
	public Boolean rpush(String key, String... strings) {
		for (String string : strings) {
			jedisCluster.rpush(key, string);
		}
		return true;
	}

	/**
	 * 返回list中指定范围的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, long start, long end) {
		LOG.debug("return the specified elements of the list stored at the specified key. key=" + key);
		return jedisCluster.lrange(key, start, end);
	}

	public long pfadd(String key, String member, int ttl, boolean asyncExpire) {
		long result = pfadd(key, member);
		// 设置 key 的过期时间
		if (asyncExpire) { // 异步设置过期
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}
		return result;
	}

	public Long pfadd(final String key, final String... elements) {
		return jedisCluster.pfadd(key, elements);
	}

	public long pfcount(final String key) {
		return jedisCluster.pfcount(key);
	}

	/**
	 * 返回list长度
	 * 
	 * @param key
	 * @return
	 */
	public Long llen(String key) {
		return jedisCluster.llen(key);
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员 
	 * 
	 * @see 如果需要同时添加多个member，请查看 {@link #sadd(String, String...)}
	 * @param key
	 * @param members
	 * @return 如果是作为新元素添加返回1，如果元素在集合中已存在返回0
	 */
	public long sadd(String key, String member) {
		long begin = System.currentTimeMillis();
		long result = jedisCluster.sadd(key, member);
		record("sadd", System.currentTimeMillis() - begin);
		return result;
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员
	 * b)同时设置key的过期时间为 ttl(秒) 注意，这里不关心 ttl 设置成功或失败
	 * 
	 * @see 如果需要同时添加多个member，请查看 {@link #sadd(String, int, String...)}
	 * @param key
	 * @param member
	 * @param ttl
	 * @return 如果是作为新元素添加返回1，如果元素在集合中已存在返回0
	 */
	public long sadd(String key, String member, int ttl) {
		long result = sadd(key, member);
		// 设置 key 的过期时间
		expire(key, ttl);
		return result;
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员
	 * b)同时设置key的过期时间为 ttl(秒) 注意，这里不关心 ttl 设置成功或失败
	 * c)选择是否采用异步过期（asyncExpire:true表示异步过期）
	 * 
	 * @see 如果需要同时添加多个member，请查看 {@link #sadd(String, int, boolean, String...)}
	 * @param key
	 * @param member
	 * @param ttl
	 * @param asyncExpire
	 * @return 如果是作为新元素添加返回1，如果元素在集合中已存在返回0
	 */
	public long sadd(String key, String member, int ttl, boolean asyncExpire) {
		long result = sadd(key, member);
		// 设置 key 的过期时间
		if (asyncExpire) { // 异步设置过期
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}
		return result;
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员（可同时加入一个或多个） 
	 * 
	 * @param key
	 * @param member
	 * @return 返回实际新增的成员数（比如集合myset中已经存在成员a，此时sadd(myset,a,b,c)只有b,c作为新成员插入，返回2）
	 */
	public long sadd(String key, String... member) {
		long begin = System.currentTimeMillis();
		long result = jedisCluster.sadd(key, member);
		record("sadd", System.currentTimeMillis() - begin);
		return result;
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员（可同时加入一个或多个）
	 * b)同时设置key的过期时间为 ttl(秒) 注意，这里不关心 ttl 设置成功或失败
	 * 
	 * @param key
	 * @param ttl
	 * @param member
	 * @return 返回实际新增的成员数（比如集合myset中已经存在成员a，此时sadd(myset,60,a,b,c)只有b,c作为新成员插入，返回2）
	 */
	public long sadd(String key, int ttl, String... member) {
		long result = sadd(key, member);
		// 设置 key 的过期时间
		expire(key, ttl);
		return result;
	}

	/**
	 * <pre>
	 * a)向指定key的set集合中加入新成员（可同时加入一个或多个）
	 * b)同时设置key的过期时间为 ttl(秒) 注意，这里不关心 ttl 设置成功或失败
	 * c)选择是否采用异步过期（asyncExpire:true表示异步过期）
	 * 
	 * @param key
	 * @param ttl
	 * @param asyncExpire
	 * @param member
	 * @return 返回实际新增的成员数（比如集合myset中已经存在成员a，此时sadd(myset,60,true,a,b,c)只有b,c作为新成员插入，返回2）
	 */
	public long sadd(String key, int ttl, boolean asyncExpire, String... member) {
		long result = sadd(key, member);
		// 设置 key 的过期时间
		if (asyncExpire) { // 异步设置过期
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}
		return result;
	}

	/**
	 * 判断某个成员是否属于指定key的set集合
	 * 
	 * @see #sismember(String, String, boolean)
	 * @param
	 * @return boolean
	 */
	public Boolean sismember(String key, String member) {
		return jedisCluster.sismember(key, member);
	}

	/**
	 * 判断某个成员是否属于指定key的set集合（指定是否从master读取最新数据）
	 * 
	 * @param
	 * @return boolean
	 */
	public Boolean sismember(String key, String member, boolean fromMaster) {
		return jedisCluster.sismember(key, member);
	}

	/**
	 * 返回指定key的set集合
	 * 
	 * @param
	 * @return boolean
	 */
	public Set<String> smembers(String key) {
		return jedisCluster.smembers(key);
	}

	/**
	 * 删除存储在key中指定member的集合成员
	 * 
	 * @param key
	 * @param members
	 * @return
	 */
	public Long srem(String key, String... members) {
		return jedisCluster.srem(key, members);
	}

	/**
	 * 返回指定key中set的元素个数
	 * 
	 * @param key
	 * @return
	 */
	public Long scard(String key) {
		return jedisCluster.scard(key);
	}

	/**
	 * <pre>
	 * 为指定的key设定Filed/Value对， 
	 * 如果Key不存在，该命令将创建新Key设置Field/Value对，返回true
	 * 如果参数中的Field在该Key中已经存在，则用新值覆盖其原有值，返回false
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean hset(String key, String field, String value) {
		long result = jedisCluster.hset(key, field, value);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Long hset(String key, String field, String value, int ttl, boolean asyncExpire) {
		long result = jedisCluster.hset(key, field, value);
		if (asyncExpire) {
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}
		return result;
	}

	/**
	 * 从指定key的Hash列表中返回参数Field的关联值
	 * 
	 * @see #hget(String, String, boolean)
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	/**
	 * 从指定key的Hash列表中返回参数Field的关联值（指定是否从master读取最新数据）
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field, boolean fromMaster) {
		if (fromMaster) {
			return jedisCluster.hget(key, field);
		} else {
			return hget(key, field);
		}
	}

	/**
	 * 从指定Key的Hash队列中删除Field的关联值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Long hdel(String key, String field) {
		return jedisCluster.hdel(key, field);
	}

	/**
	 * 返回该Key对应的hash表中的实体数量 如果Key值不存在，返回0
	 * 
	 * @param key
	 * @return
	 */
	public Long hlen(String key) {
		return jedisCluster.hlen(key);
	}

	/**
	 * 返回Key对应的hash表中所有键值对
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {
		return jedisCluster.hgetAll(key);
	}

	/**
	 * 从指定Key的hash中获取多个fields对应的value值
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key, String... fields) {
		return jedisCluster.hmget(key, fields);
	}

	/**
	 * 向指定Key的hash中设置多个fields-value值
	 * 
	 * @param key
	 * @param hash
	 * @return
	 */
	public String hmset(String key, Map<String, String> hash) {
		return jedisCluster.hmset(key, hash);
	}

	/**
	 * <pre>
	 * 对指定Key的hash结构中的field对应的value作增加操作
	 * 返回新增后的值
	 * @param key
	 * @param hash
	 * @return
	 */
	public Long hincrBy(String key, String field, int value) {
		long begin = System.currentTimeMillis();
		long result = jedisCluster.hincrBy(key, field, value);
		record("hincrBy", System.currentTimeMillis() - begin);
		return result;
	}

	/**
	 * <pre>
	 * 对指定Key的hash结构中的field对应的value作增加操作
	 * 同时设置过期时间
	 * @param key
	 * @param hash
	 * @return
	 */
	public Long hincrBy(String key, String field, int value, int ttl) {
		long count = hincrBy(key, field, value);
		expire(key, ttl);
		return count;
	}

	/**
	 * <pre>
	 * 对指定Key的hash结构中的field对应的value作增加操作
	 * 同时设置过期时间（对于ttl比较大的情况，将多次expire操作合并为一次）
	 * @param key
	 * @param hash
	 * @return
	 */
	public Long hincrBy(String key, String field, int value, int ttl, boolean asyncExpire) {
		long count = hincrBy(key, field, value);
		if (asyncExpire) {
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}
		return count;
	}

	public Long hincrByRest(String key, String field, int value, int ttl, boolean asyncExpire) {

		String hincr = jedisCluster.hget(key, field);

		int total = 0;
		if (hincr != null) {
			total = Integer.valueOf(hincr);
		}
		int inc = value - total;

		long count = hincrBy(key, field, inc);
		if (asyncExpire) {
			RedisExpireHelper.expireKey(key, ttl);
		} else {
			expire(key, ttl);
		}

		return count;
	}

	/**
	 * 将key中存储的数字值增加1，如果key值不存在，那么key值先初始化为0，然后执行incr操作
	 * 
	 * @param key
	 * @return 返回自增后的值
	 */
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	/**
	 * 将key中存储的数字值增加指定值，如果key值不存在，那么key值先初始化为0，然后执行incr操作
	 * 
	 * @see RedisClusterClient#incrBy(String, long)
	 * @param key
	 * @return 返回自增后的值
	 */
	public Long incrby(String key, long increment) {
		return jedisCluster.incrBy(key, increment);
	}

	/**
	 * 将key中存储的数字值增加指定值，如果key值不存在，那么key值先初始化为0，然后执行incr操作
	 * 
	 * @param key
	 * @return 返回自增后的值
	 */
	public Long incrBy(String key, long increment) {
		return jedisCluster.incrBy(key, increment);
	}

	/**
	 * 将key中存储的数字值增加指定值，如果key值不存在，那么key值先初始化为0，然后执行incr操作
	 * 
	 * @param key
	 * @return 返回自增后的值
	 */
	public Double incrByFloat(String key, double increment) {
		return jedisCluster.incrByFloat(key, increment);
	}

	/**
	 * 重置缓存过期时间（自定义时间）
	 * 
	 * @param key
	 * @return
	 */
	public Long expire(String key, int seconds) {
		long result = jedisCluster.expire(key, seconds);
		return result;
	}

	/**
	 * 重置缓存过期时间（自定义时间）
	 * 
	 * @param key
	 * @return
	 */
	public Long expireAt(String key, long unixTime) {
		return jedisCluster.expireAt(key, unixTime);

	}

	public Map<String, JedisPool> getClusterNodes() {
		return jedisCluster.getClusterNodes();
	}

	public void record(String command, long milliseconds) {
		AtomicLong times = timesMap.get(command);
		AtomicLong cost = costMap.get(command);
		if (null == times) {
			times = new AtomicLong(0);
		}
		if (null == cost) {
			cost = new AtomicLong(0);
		}
		long totalTimes = times.incrementAndGet();
		long totalCost = cost.addAndGet(milliseconds);
		timesMap.put(command, times);
		costMap.put(command, cost);
		if (totalTimes % 10000 == 0) { // 每10000个命令统计一次总耗时
			LOG.info(">>>>>>>>>>redis stats, command:" + command + ", 10000 commands cost: " + totalCost + " ms");
			times.set(0);
			cost.set(0);
		}
	}
}
