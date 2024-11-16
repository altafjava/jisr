package com.jisr.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NamedRunnable implements Runnable {
	private final String name;
	private final Runnable task;

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		task.run();
	}

}