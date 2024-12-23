package com.jisr.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * A thread pool class that manages a pool of worker threads. The number of threads is calculated based on the formula:
 * N_threads = N_cpu * U_cpu * (1 + W/C) where: - N_cpu is the number of cores available - U_cpu is the target CPU
 * utilization (0.8 in this example) - W/C is the ratio of wait time to compute time (1 in this example)
 */
@Slf4j
@Getter
public class ThreadPool {

	private final ExecutorService executorService;

	public ThreadPool(int coreThreads, double cpuUtilization, double waitToComputeRatio) {
		int numThreads = (int) (coreThreads * cpuUtilization * (1 + waitToComputeRatio));
		log.info("Creating a thread pool with {} threads", numThreads);
		this.executorService = Executors.newFixedThreadPool(numThreads);
	}

	/**
	 * Executes the given task in the thread pool.
	 * 
	 * @param task the task to execute
	 */
	public void executeTask(NamedRunnable task) {
		log.info("Executing a new task in the thread pool: {}", task.getName());
		executorService.execute(task);
	}

	/**
	 * Shuts down the thread pool. Always call this method when you're done with the thread pool.
	 */
	public void shutdown() {
		log.info("Shutting down the thread pool");
		executorService.shutdown();
	}
}