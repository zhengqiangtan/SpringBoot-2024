# 使用指南



# 报错问题
### 1.错误 1
```
Caused by: java.lang.IllegalStateException: Could not evaluate condition on org.springframework.boot.actuate.autoconfigure.audit.AuditEventsEndpointAutoConfiguration due to org/springframework/core/annotation/MergedAnnotations$SearchStrategy not found. Make sure your own configuration does not rely on that class. This can also happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)
	at org.springframework.boot.autoconfigure.condition.SpringBootCondition.matches(SpringBootCondition.java:53)
	at org.springframework.context.annotation.ConditionEvaluator.shouldSkip(ConditionEvaluator.java:108)
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:218)
	at org.springframework.context.annotation.ConfigurationClassParser.processImports(ConfigurationClassParser.java:586)
	... 89 more
```

解决办法：
Redisson 3.16.4可能依赖于较新的Spring版本，Spring Boot 2.1.6较老，建议升级Spring Boot版本。如果可以升级，推荐升级到Spring Boot 2.5.x或更高版本
```xml
  <!-- Redisson 降低版本 -->
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson-spring-boot-starter</artifactId>
            <version>3.11.6</version>
        </dependency>
```

### 2. 错误 2
```
Caused by: java.lang.NoSuchMethodError: org.apache.commons.pool2.impl.GenericObjectPoolConfig.setMaxWait(Ljava/time/Duration;)V

```
