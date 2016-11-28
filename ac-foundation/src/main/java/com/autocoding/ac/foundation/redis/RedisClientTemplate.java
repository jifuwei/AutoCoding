package com.autocoding.ac.foundation.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis
 * Created by JFW on 2016/11/21.
 */
public interface RedisClientTemplate {
    String set(String key, String value);

    String get(String key);

    void pipelineSet(Map<String, String> cacheMap);

    void sadd(String key, String[] members);

    Boolean sismember(String key, String member);

    Boolean exists(String key);

    Set<String> smembers(String key);

    Long rpush(String key, String[] values);

    List<String> lrange(String key, Long start, Long end);

    String ltrim(String key, Long start, Long end);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incrby(String key, long num);

    Long scard(String key);

    List<String> srandmember(String key, int num);

    Long srem(String key, String[] members);
}
