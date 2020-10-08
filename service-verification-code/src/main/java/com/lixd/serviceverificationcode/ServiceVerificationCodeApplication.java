package com.lixd.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootApplication
public class ServiceVerificationCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationCodeApplication.class, args);
    }
    @Bean
    public StringRedisTemplate jacksonRedisSerializer(RedisConnectionFactory fc){
        StringRedisTemplate tp = new StringRedisTemplate(fc);
        tp.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return tp;
    }

}
