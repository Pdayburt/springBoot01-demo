package com.lagou.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory
                                                  redisConnectionFactory) {
// 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
// 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
// 定制缓存数据序列化方式及时效
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        //上述代码中，在RedisConfig配置类中使用@Bean注解注入了一个默认名称为方法名的 cacheManager组件。在定义的Bean组件中，通过RedisCacheConfiguration对缓存数据的key和value 分别进行了序列化方式的定制，其中缓存数据的key定制为StringRedisSerializer(即String格式)，而 value定制为了Jackson2JsonRedisSerializer(即JSON格式)，同时还使用 entryTtl(Duration.ofDays(1))方法将缓存数据有效期设置为1天
                        // 完成基于注解的Redis缓存管理器RedisCacheManager定制后，可以对该缓存管理器的效果进行 测试(使用自定义序列化机制的RedisCacheManager测试时，实体类可以不用实现序列化接口)
                        .entryTtl(Duration.ofDays(1))
                        .serializeKeysWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(strSerializer))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(jacksonSeial))
                        .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(redisConnectionFactory).cacheDefaults(config).build();
        return cacheManager;
    }
}
