#!/bin/bash

# 配置 Kafka 端点和条目数
URL="http://localhost:8080/api/kafka/publish"
NUM_MESSAGES=10  # 发送消息的条目数

# 生成随机字符串函数
generate_random_string() {
    local LENGTH=$1
    # mac上运行会有报错提示，解决方法：将LC_CTYPE=C 替换为 LC_ALL=C
    # tr -dc A-Za-z0-9 </dev/urandom | head -c ${LENGTH}
    LC_CTYPE=C tr -dc A-Za-z0-9 </dev/urandom | head -c ${LENGTH}

}

# 发送消息函数
send_message() {
    local ID=$1
    local MESSAGE=$2
    curl -X POST $URL \
        -H "Content-Type: application/json" \
        -d "{\"id\": \"${ID}\", \"message\": \"${MESSAGE}\"}"
}

# 发送指定条目数的消息
for ((i=1; i<=NUM_MESSAGES; i++))
do
    RANDOM_ID=$(generate_random_string 8)  # 生成8位随机字符串作为ID
    RANDOM_MESSAGE="Hello Kafka $(generate_random_string 10)"  # 生成10位随机字符串作为消息内容
    send_message $RANDOM_ID "$RANDOM_MESSAGE"
    echo "Sent message $i with ID: $RANDOM_ID and message: $RANDOM_MESSAGE"
done

echo "All messages sent. msg count: $NUM_MESSAGES"