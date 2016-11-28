package com.autocoding.ac.foundation.redis.impl;

import com.autocoding.ac.foundation.redis.JedisDataSource;
import com.autocoding.ac.foundation.redis.RedisClientTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis操作类
 * Created by JFW on 2016/11/21.
 */
@Repository
public class RedisClientTemplateImpl implements RedisClientTemplate {
    private static final Logger logger = Logger.getLogger(RedisClientTemplateImpl.class);

    @Autowired
    private JedisDataSource redisDataSource;

    private ShardedJedis shardedJedis = null;

    @Override
    public String set(String key, String value) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.set(key, value);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.get(key);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public void pipelineSet(Map<String, String> cacheMap) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            ShardedJedisPipeline pipeline = shardedJedis.pipelined();
            for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
                pipeline.set(entry.getKey(), entry.getValue());
            }
            pipeline.sync();
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public void sadd(String key, String[] members) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            shardedJedis.sadd(key, members);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Boolean sismember(String key, String member) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.sismember(key, member);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.exists(key);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Set<String> smembers(String key) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.smembers(key);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long rpush(String key, String[] values) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.rpush(key, values);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public List<String> lrange(String key, Long start, Long end) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.lrange(key, start, end);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public String ltrim(String key, Long start, Long end) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.ltrim(key, start, end);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.expire(key, seconds);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.ttl(key);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long incrby(String key, long num) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.incrBy(key, num);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long scard(String key) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.scard(key);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public List<String> srandmember(String key, int num) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.srandmember(key, num);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

    @Override
    public Long srem(String key, String[] members) {
        try {
            shardedJedis = redisDataSource.getRedisClient();
            return shardedJedis.srem(key, members);
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
    }

}
