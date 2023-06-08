package auth.core.cloud.config;

import auth.core.cloud.filter.SessionAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.server.ServerWebExchange;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class GatewaySecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors(cors -> {
                CorsConfigurationSource source = exchange -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true); // 쿠키를 받을건지
                    config.addAllowedOrigin("*");
                    config.addAllowedHeader("*");
                    config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT", "PATCH"));
                    config.setMaxAge(Duration.ofMinutes(30));
                    return config;
                };
                cors.configurationSource(source);
            })

            .csrf(ServerHttpSecurity.CsrfSpec::disable)

            .headers(ServerHttpSecurity.HeaderSpec::disable)

            .formLogin(ServerHttpSecurity.FormLoginSpec::disable).securityContextRepository(
                    NoOpServerSecurityContextRepository.getInstance())

//            .authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.OPTIONS)
//                                                   .permitAll()
//                                                   .pathMatchers("/auth/**")
//                                                   .permitAll()
//                                                   .pathMatchers("/online/**")
//                                                   .permitAll()
//                                                   .anyExchange()
//                                                   .authenticated())
//
//            .addFilterAt(new SessionAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.OPTIONS)
                                                   .permitAll()
                                                   .anyExchange()
                                                   .permitAll())

            .logout(ServerHttpSecurity.LogoutSpec::disable);

//            .httpBasic(withDefaults())
//            .formLogin(withDefaults());
        return http.build();
    }
}