package auth.core.cloud.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.server.ServerWebExchange;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())

            .cors(cors -> {
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

            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

            .logout(ServerHttpSecurity.LogoutSpec::disable);

//            .httpBasic(withDefaults())
//            .formLogin(withDefaults());
        return http.build();
    }
}