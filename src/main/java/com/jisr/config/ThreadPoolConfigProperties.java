package com.jisr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "app.threadpool")
public class ThreadPoolConfigProperties {
	private int coreThreads = Runtime.getRuntime().availableProcessors();
	private double cpuUtilization = 0.8;
	private double waitToComputeRatio = 1.0;
}
