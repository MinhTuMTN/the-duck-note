package com.theduck.note.api_gateway.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theduck.note.api_gateway.util.JwtUtil;
import com.theduck.note.commons.dto.ApiResponse;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final String[] PUBLIC_ENDPOINTS = { "/auth/.*" };

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (this.isPublicEndpoint(exchange)) {
            return chain.filter(exchange);
        }

        List<String> token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

        if (!this.checkToken(token)) {
            return this.unauthenticatedResponse(exchange.getResponse());
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private boolean checkToken(List<String> token) {
        if (CollectionUtils.isEmpty(token)) {
            return false;
        }

        String accessToken = token.get(0);
        if (!accessToken.startsWith("Bearer")) {
            return false;
        }

        return this.jwtUtil.isValidToken(accessToken.substring(7));
    }

    private boolean isPublicEndpoint(ServerWebExchange exchange) {
        return Arrays.stream(this.PUBLIC_ENDPOINTS)
                .anyMatch(endpoint -> exchange.getRequest().getURI().getPath().matches(endpoint));
    }

    private Mono<Void> unauthenticatedResponse(ServerHttpResponse response) {
        ApiResponse<?> apiResponse = ApiResponse.builder().code(1401).message("Unauthenticated").build();

        String body = null;
        try {
            body = new ObjectMapper().writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));

    }
}
