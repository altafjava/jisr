package com.jisr.filter;

import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestInitializerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		request.getParameterNames(); // this takes care of initializing request `parts` Multipart file upload
		filterChain.doFilter(request, response);
	}
}