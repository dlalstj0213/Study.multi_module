package auth.core.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@Slf4j
public class GatewayAuthorizationSessionFilter extends AbstractGatewayFilterFactory<GatewayAuthorizationSessionFilter.Config> {
    public GatewayAuthorizationSessionFilter() {
        super(Config.class);
    }

    public static class Config {
        // application.yml 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("##> AuthorizationSessionFilter apply()");
            org.springframework.cloud.gateway.route.Route route = exchange.getAttribute(org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            log.info("##> AuthorizationSessionFilter - 2 :: {}", route);
            ServerHttpRequest request = exchange.getRequest();
            log.info("{} : {} {}",
                     request.getHeaders().getFirst("X-Forwarded-For") == null
                             ? request.getRemoteAddress()
                             : request.getHeaders().getFirst("X-Forwarded-For"),
                     request.getMethod(),
                     request.getURI());

            return exchange.getSession().doOnNext(session -> {
                log.info("session ID :: {}", session.getId());
                Optional.ofNullable(session.getAttribute("user")).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Not found session, Please Login again."));
            }).then(chain.filter(exchange));
        };
    }
}