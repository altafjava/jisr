package com.jisr.config;

import java.util.concurrent.Executor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import jakarta.annotation.PreDestroy;

@EnableAsync
@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class ThreadPoolConfig implements AsyncConfigurer {

	private final ThreadPool threadPool;

	public ThreadPoolConfig(ThreadPoolConfigProperties properties) {
		this.threadPool = new ThreadPool(properties.getCoreThreads(), properties.getCpuUtilization(), properties.getWaitToComputeRatio());
	}

	@Bean
	public ThreadPool threadPool() {
		return threadPool;
	}

	@Bean
	public Executor taskExecutor(ThreadPool threadPool) {
		return threadPool.getExecutorService();
	}

	@PreDestroy
	public void preDestroy() {
		threadPool.shutdown();
	}

}