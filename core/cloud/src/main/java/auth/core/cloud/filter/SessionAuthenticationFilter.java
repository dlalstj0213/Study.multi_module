package auth.core.cloud.filter;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.server.*;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SessionAuthenticationFilter implements WebFilter {

    private PathPattern basePattern;

    private List<PathPattern> excludePatterns;

    public SessionAuthenticationFilter() {
        basePattern = new PathPatternParser().parse("/api/**");
        excludePatterns = new ArrayList<>();
        excludePatterns.add(new PathPatternParser().parse("/online/**"));
//        excludePatterns.add(new PathPatternParser().parse("/api/ping/**"));
    }

    @Override
    public @NonNull Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        log.info("##> SessionAuthenticationFilter: do Filter :-: cookies :: {}", exchange.getRequest().getCookies());
        // TYPE1
//        return exchange.getSession().map(session -> {
//            User principal = new User("minseoRhie", "", Collections.singleton((GrantedAuthority) () -> "ROLE_MEMBER"));
//            log.info("##> principal: {}", principal);
//            log.info("##> session: {}", session.getId());
//            //session.getAttributes();  //-> 세션값, Map형태로 되어 있다.
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
//                                                                                    "sessionId",
//                                                                                    Collections.singleton((GrantedAuthority) () -> "ROLE_MEMBER"));
//            //        authentication.setAuthenticated(true);
//            return chain.filter(exchange)
//                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
//        }).flatMap(Mono::from);
        ServerHttpRequest request = exchange.getRequest();
        log.info("{} : {} {}",
                 request.getHeaders().getFirst("X-Forwarded-For") == null
                         ? request.getRemoteAddress()
                         : request.getHeaders().getFirst("X-Forwarded-For"),
                 request.getMethod(),
                 request.getURI());

        if (excludePatterns.stream().noneMatch(pathPattern -> pathPattern.matches(request.getPath()
                                                                                         .pathWithinApplication()))) {
            return exchange.getSession().doOnNext(session -> {
                log.info("session ID :: {}", session.getId());
                Optional.ofNullable(session.getAttribute("user")).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Not found session, Please Login again."));
            }).then(chain.filter(exchange));
        } else {
            return chain.filter(exchange);
        }
    }
}