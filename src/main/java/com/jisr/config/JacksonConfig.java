package com.jisr.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customJson() {
		return builder -> builder
				.serializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL) // Exclude null fields
				.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // Optional: Avoid failures for empty beans
	}
}
