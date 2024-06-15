#!/bin/bash
# 测试监听器接口消费的启动和暂停

# 测试暂停消费者
URL="http://localhost:8080/kafka/pause"
  curl -X POST $URL \
        -H "Content-Type: application/json" \
        -d ""

# 测试启动
#URL="http://localhost:8080/kafka/resume"
#  curl -X POST $URL \
#        -H "Content-Type: application/json" \
#        -d ""