package com.ashish.spring.boot.redis.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EntityScan(basePackages={"com.ashish.spring.boot.redis.dao"})
@EnableRedisRepositories(basePackages = {"com.ashish.spring.boot.redis.repository"})
public class RedisConfig {
	@Bean
	public StringRedisTemplate redisTemplate() {
	    /*RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setStringSerializer(stringSerializer());
	    template.setHashKeySerializer(stringSerializer());
	    template.setHashValueSerializer(stringSerializer());
	    return template;*/
	    
	    StringRedisTemplate redisTemplate = new StringRedisTemplate();
	    redisTemplate.setConnectionFactory(jedisConnectionFactory());
	    return redisTemplate;
	}
	
	@Bean
	public StringRedisSerializer stringSerializer() {
		return new StringRedisSerializer();
	}
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName("localhost");
	    jedisConFactory.setPort(6379);
	    return jedisConFactory;
	}
	
	
}
