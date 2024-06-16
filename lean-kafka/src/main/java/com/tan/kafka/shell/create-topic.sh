cd /Users/mac/apps/kafka
# 创建主题
#bin/kafka-topics.sh --create --topic events_test --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# 验证主题创建
#bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# 删除主题
#bin/kafka-topics.sh --delete --topic events_test --bootstrap-server localhost:9092


# 生产消息
#bin/kafka-console-producer.sh --topic events_test --bootstrap-server localhost:9092


# 消费消息
bin/kafka-console-consumer.sh --topic events_test --from-beginning --bootstrap-server localhost:9092