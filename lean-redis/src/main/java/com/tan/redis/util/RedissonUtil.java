package com.tan.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedissonUtil {

    private final RedissonClient redissonClient;

    @Autowired
    public RedissonUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    // String 操作
    public void setString(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value, 30, TimeUnit.SECONDS);
    }

    public String getString(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    // List 操作
    public void addToList(String key, String value) {
        RList<String> list = redissonClient.getList(key);
        list.add(value);
    }

    public String getFromList(String key, int index) {
        RList<String> list = redissonClient.getList(key);
        return list.get(index);
    }

    // Set 操作
    public void addToSet(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        set.add(value);
    }

    public boolean isMemberOfSet(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        return set.contains(value);
    }

    // Hash 操作
    public void putToMap(String key, String field, String value) {
        RMap<String, String> map = redissonClient.getMap(key);
        map.put(field, value);
    }

    public String getFromMap(String key, String field) {
        RMap<String, String> map = redissonClient.getMap(key);
        return map.get(field);
    }

    // ZSet 操作
    public void addToSortedSet(String key, String value, double score) {
        RSortedSet<String> sortedSet = redissonClient.getSortedSet(key);
        sortedSet.add(value);
    }

    public RSortedSet<String> getSortedSet(String key) {
        return redissonClient.getSortedSet(key);
    }

    // 分布式锁
    public void lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 设置锁的超时时间为30秒，防止锁无法释放
            lock.lock(30, TimeUnit.SECONDS);
            log.info("线程" + Thread.currentThread().getName() + "成功获取锁：" + lockKey);
        } catch (Exception e) {
            log.error("线程" + Thread.currentThread().getName() + "获取锁失败：" + lockKey, e);
        }
    }

    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("线程" + Thread.currentThread().getName() + "成功释放锁：" + lockKey);
            } else {
                log.warn("线程" + Thread.currentThread().getName() + "尝试释放未持有的锁：" + lockKey);
            }
        } catch (IllegalMonitorStateException e) {
            log.error("线程" + Thread.currentThread().getName() + "释放锁失败，锁未被持有：" + lockKey, e);
        } catch (Exception e) {
            log.error("线程" + Thread.currentThread().getName() + "释放锁失败：" + lockKey, e);
        }
    }
}

