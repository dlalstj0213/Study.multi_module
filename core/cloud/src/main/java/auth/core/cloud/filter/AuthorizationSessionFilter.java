package auth.core.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationSessionFilter extends AbstractGatewayFilterFactory<AuthorizationSessionFilter.Config> {

    public AuthorizationSessionFilter() {
        super(Config.class);
    }

    public static class Config {
        // application.yml 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("##> AuthorizationSessionFilter apply()");
//            exchange.getSession().map(session -> {
//                session.g
//
//                // It does not print anything
//                System.out.println("userId in session: " + session.getAttribute("userId"));
//                return session;
//            });
            return chain.filter(exchange);
        };
    }
}