package com.autocoding.ac.foundation.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * jedis操作类
 * Created by JFW on 2016/11/21.
 */
public interface JedisDataSource {
    ShardedJedis getRedisClient();

    void returnResource(ShardedJedis shardedJedis);

    void returnResource(ShardedJedis shardedJedis, boolean broken);
}
