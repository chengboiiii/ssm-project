package com.cb.framework.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 *
 */
public interface Executor<K> {
	K execute(ShardedJedis jedis);
}
