package com.theduck.note.authentication.config;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (servletRequestAttributes == null) {
            return;
        }

        String authHeader = servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader)) {
            template.header("Authorization", authHeader);
        }
    }

}
