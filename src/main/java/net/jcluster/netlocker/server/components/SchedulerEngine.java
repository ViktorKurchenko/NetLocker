package net.jcluster.netlocker.server.components;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

import static net.jcluster.netlocker.server.config.ServerConfig.*;


/**
 * Used to execute periodic tasks (like CRON daemon)
 */
public class SchedulerEngine {

	private final int INIT_DELAY;
	private final TimeUnit TIME_UNIT;

	private ScheduledExecutorService scheduledService;


	private SchedulerEngine() {
		this(new SchedulerEngineConfig());
	}

	private SchedulerEngine(SchedulerEngineConfig config) {
		INIT_DELAY = config.initDelay;
		TIME_UNIT = config.timeUnit;
		scheduledService = Executors.newScheduledThreadPool(config.poolSize, runnable -> {
			Thread thread = new Thread(runnable);
			thread.setDaemon(config.isDaemon);
			return thread;
		});
	}

	/**
	 * Factory method
	 * Create and start SchedulerEngine
	 *
	 * @return  new instance of SchedulerEngine
	 */
	public static SchedulerEngine create() {
		return new SchedulerEngine();
	}

	/**
	 * Factory method
	 * Create and start SchedulerEngine
	 *
	 * @param config   SchedulerEngine configuration
	 * @return         new instance of SchedulerEngine
	 */
	public static SchedulerEngine create(SchedulerEngineConfig config) {
		return new SchedulerEngine(config);
	}

	/**
	 * Submit Runnable task into scheduled thread pool, with fixed execution
	 *
	 * @param runnable   Runnable task
	 * @param period     Execute period
	 * @return           Future result
	 */
	public ScheduledFuture<?> submitFixed(@NotNull Runnable runnable, long period) {
		 return scheduledService.scheduleAtFixedRate(runnable, INIT_DELAY, period, TIME_UNIT);
	}

	/**
	 * Execute shutdown command for scheduled pool, wait for all executed tasks termination
	 */
	public void stop() {
		stop(false);
	}

	/**
	 * Execute shutdown command for scheduled pool
	 *
	 * @param immediateShutdown   true - try immediately shutdown the pool
	 *                            false - shutdown, but wait for all executed tasks termination
	 */
	public void stop(boolean immediateShutdown) {
		if (scheduledService != null) {
			if (immediateShutdown) {
				scheduledService.shutdownNow();
			} else {
				scheduledService.shutdown();
			}
		}
	}

	/**
	 * Used to create SchedulerEngine configuration
	 * Implemented as Builder pattern
	 */
	public static class SchedulerEngineConfig {

		private int poolSize = SCHEDULER_ENGINE_POOL_SIZE;
		private int initDelay = SCHEDULER_ENGINE_INIT_DELAY;
		private TimeUnit timeUnit = SCHEDULER_ENGINE_TIME_UNIT;
		private boolean isDaemon = SCHEDULER_ENGINE_DAEMON_FLAG;


		public static SchedulerEngineConfig create() {
			return new SchedulerEngineConfig();
		}

		public SchedulerEngineConfig withPoolSize(int poolSize) {
			this.poolSize = poolSize;
			return this;
		}

		public SchedulerEngineConfig withInitDelay(int initDelay) {
			this.initDelay = initDelay;
			return this;
		}

		public SchedulerEngineConfig withTimeUnit(TimeUnit timeUnit) {
			this.timeUnit = timeUnit;
			return this;
		}

		public SchedulerEngineConfig withIsDaemon(boolean isDaemon) {
			this.isDaemon = isDaemon;
			return this;
		}
	}

}
