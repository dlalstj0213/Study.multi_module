package com.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@AutoConfiguration
@EnableRedisHttpSession
//@ConditionalOnProperty(name = "spring.data.redis.port", havingValue = "8888", matchIfMissing = true)
@EnableConfigurationProperties(CommonSessionProperties.class)
//@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class CommonSessionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CommonSessionConfiguration.class);

    private final CommonSessionProperties properties;

    public CommonSessionConfiguration(CommonSessionProperties properties) {
        this.properties = properties;
        log.info("##> Import AutoConfiguration - create instance : {}", this.getClass());
    }

    @Bean
    @ConditionalOnMissingBean
    public LettuceConnectionFactory lettuceConnectionFactory() {
//        String host = environment.getProperty("spring.data.redis.host");
//        String port = environment.getProperty("spring.data.redis.port");
        String host = properties.getHost();
        String port = properties.getPort();
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,
                                                                                                           Integer.parseInt(
                                                                                                                   port));
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public CommonSessionTemplate commonSessionTemplate() {
        return new CommonSession();
    }
}