package auth.core.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.data.redis.ReactiveRedisSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
@EnableWebFlux
@EnableSpringWebSession
@EnableRedisWebSession
public class WebfluxRedisSessionConfig {

    //////////////////////////////////////////
    // Normal Redis Settings
    //////////////////////////////////////////
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory("localhost", 6379);
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return redisTemplate;
//    }

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private Integer port;

    //////////////////////////////////////////
    // Reactive Redis Settings
    //////////////////////////////////////////
    @Bean
    public ReactiveRedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(redisConfig);
    }

//    @Bean
//    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
//
//        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext.<String, Object>newSerializationContext(
//                                                                                                          new StringRedisSerializer())
//                                                                                                  .hashKey(new StringRedisSerializer())
//                                                                                                  .hashValue(new GenericJackson2JsonRedisSerializer())
//                                                                                                  .build();
//
//        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
//    }


    @Bean
    ReactiveRedisOperations<String, Object> redisOperations(ReactiveRedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext.newSerializationContext(
                new StringRedisSerializer());

        RedisSerializationContext<String, Object> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(redisConnectionFactory, context);
    }

    //////////////////////////////////////////
    // repository setting
    //////////////////////////////////////////

    @Bean
    public ReactiveRedisSessionRepository redisSessionRepository(ReactiveRedisOperations<String, Object> redisOperations) {
        ReactiveRedisSessionRepository redisSessionRepository = new ReactiveRedisSessionRepository(redisOperations);
        redisSessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(1800L));
        return redisSessionRepository;
    }

    //////////////////////////////////////////
    // session id 를 헤더에 설정하는 빈
    //////////////////////////////////////////

    @Bean
    public WebSessionIdResolver webSessionIdResolver() {
        HeaderWebSessionIdResolver sessionIdResolver = new HeaderWebSessionIdResolver();
        sessionIdResolver.setHeaderName("X-AUTH-TOKEN");
        return sessionIdResolver;
    }
}