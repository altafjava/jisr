package com.jisr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class ApiVersioningConfig implements WebMvcConfigurer {

	@Value("${api.base-path}")
	private String apiBasePath;

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		PathPatternParser parser = new PathPatternParser();
		configurer.setPatternParser(parser);
		configurer.addPathPrefix(apiBasePath, clazz -> clazz.isAnnotationPresent(RestController.class));
	}
}
