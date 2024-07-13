package com.tan.redis;


import com.tan.redis.util.RedissonUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedissonUtilTest {
    @Autowired
    private RedissonUtil redissonUtil;

    private RedissonClient redissonClient;


    @BeforeEach
    public void setUp() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        redissonClient = Redisson.create(config);
        redissonUtil = new RedissonUtil(redissonClient);  // 假设 RedissonUtil 是你自定义的一个工具类
    }

    @AfterEach
    public void tearDown() {
        if (redissonClient != null) {
            redissonClient.shutdown();
        }
    }

    @Test
    public void testOperation() {
        redissonUtil.setString("key1", "value1");
        String key1 = redissonUtil.getString("key1");
        Assertions.assertEquals("value1", key1);
    }

    @Test
    public void testLock() {
        // 多线程模拟测试
        Runnable runnable = () -> {
            redissonUtil.lock("lockKey");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redissonUtil.unlock("lockKey");
        };
        new Thread(runnable,"T1").start();
        new Thread(runnable,"T2").start();
        new Thread(runnable,"T3").start();

        // 确保测试线程执行完毕后再关闭Redisson客户端
        try {
            Thread.sleep(3000);  // 根据需要调整等待时间，以确保所有线程执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        线程Thread-3获取锁成功
//        线程Thread-3释放锁成功
//        线程Thread-4获取锁成功
//        线程Thread-4释放锁成功
//        线程Thread-2获取锁成功
//        线程Thread-2释放锁成功
    }

}

