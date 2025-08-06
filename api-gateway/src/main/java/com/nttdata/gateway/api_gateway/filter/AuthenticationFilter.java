package com.nttdata.gateway.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final String AUTH_HEADER = "Authorization";
    private static final String FIXED_TOKEN = "Bearer token-nttdata";




}
