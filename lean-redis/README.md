# springboot with redis

## Jedis和Redisson的区别？
Jedis 适合需要直接操作 Redis 的场景，特别是单线程、高性能的应用。
Redisson 适合需要分布式功能支持和多线程操作的场景，特别是复杂的分布式系统。
具体选择哪一个取决于你的需求和应用场景。如果需要简单、高效的 Redis 操作，选择 Jedis；如果需要分布式锁、分布式集合等高级功能，选择 Redisson