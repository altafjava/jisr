package com.jisr.filter;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestBodyCachingFilter extends OncePerRequestFilter {

    private static final ThreadLocal<String> cachedRequestBody = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Wrap the request to cache the body
        CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);
        // Read the body and store it in ThreadLocal
        String body = IOUtils.toString(wrappedRequest.getReader());
        cachedRequestBody.set(body);
        try {
            filterChain.doFilter(wrappedRequest, response);
        } finally {
            // Clear ThreadLocal after the request is processed
            cachedRequestBody.remove();
        }
    }

    public static String getCachedRequestBody() {
        return cachedRequestBody.get();
    }
}
