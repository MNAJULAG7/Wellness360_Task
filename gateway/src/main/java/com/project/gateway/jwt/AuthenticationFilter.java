package com.project.gateway.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AuthenticationFilter implements GlobalFilter , Ordered {

    @Autowired
    JwtUtils jwtUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("entered authentication filter");
        String path = exchange.getRequest().getURI().getPath();
        if(path.startsWith("/auth") || path.startsWith("/user"))
        {
            return chain.filter(exchange);
        }

        System.out.println("-------------- token parsion is strting -------------");
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if(token==null || !token.startsWith("Bearer ") || !jwtUtils.validateToken(token.substring(7)))
            {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
