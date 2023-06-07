package com.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.RedisSessionRepository;

import java.time.Duration;

@AutoConfiguration
@EnableSpringHttpSession
@EnableConfigurationProperties(CommonSessionProperties.class)
//@ConditionalOnProperty(name = "spring.data.redis.port", havingValue = "8888", matchIfMissing = true)
public class CommonSessionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CommonSessionConfiguration.class);

    private final CommonSessionProperties properties;

    public CommonSessionConfiguration(CommonSessionProperties properties) {
        this.properties = properties;
        log.info("##> Import AutoConfiguration - create instance : {}", this.getClass());
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(properties.getHost(),
                                                                                    properties.getPort());
//        redisConfig.setPassword(password);
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean
    public RedisOperations<String, Object> sessionRedisOperations(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public RedisSessionRepository sessionRepository(RedisOperations<String, Object> sessionRedisOperations) {
        RedisSessionRepository redisSessionRepository = new RedisSessionRepository(sessionRedisOperations);
        redisSessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(properties.getExpiredTime()));
        return redisSessionRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public CommonSessionTemplate commonSessionTemplate(RedisOperations<String, Object> sessionRedisOperations) {
        return new CommonSession(sessionRedisOperations);
    }
}