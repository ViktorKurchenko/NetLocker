package net.jcluster.netlocker.server;

import net.jcluster.netlocker.server.components.SchedulerEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static net.jcluster.netlocker.server.components.SchedulerEngine.SchedulerEngineConfig;
import static org.junit.Assert.assertEquals;

/**
 * Server SchedulerEngine test cases
 */
public class SchedulerEngineTest {

	private final long PERIOD = 100;
	private final long WORK_TIME = 1_000;


	@Before
	public void before() {
	}

	@Test
	public void test_01_SchedulerEngine_normalWork() throws InterruptedException {
		/*** Prepare ***/
		final int TASK_COUNT = 5;
		final int[] COUNTER = new int[TASK_COUNT];
		final Runnable[] RUNNABLE = new Runnable[TASK_COUNT];
		for (int i = 0; i < TASK_COUNT; i ++) {
			COUNTER[i] = 0;
			final int _I = i;
			RUNNABLE[i] = () -> COUNTER[_I] ++;
		}
		SchedulerEngine schedulerEngine = SchedulerEngine.create(SchedulerEngineConfig.create()
				.withInitDelay(1)
				.withPoolSize(1)
				.withTimeUnit(TimeUnit.MILLISECONDS));

		/*** Run task ***/
		for (int i = 0; i < TASK_COUNT; i ++) {
			schedulerEngine.submitFixed(RUNNABLE[i], PERIOD);
		}
		/*** Wait and stop ***/
		Thread.sleep(WORK_TIME);
		schedulerEngine.stop();

		/*** Check constraints ***/
		for (int i = 0; i < TASK_COUNT; i ++) {
			assertEquals(WORK_TIME / PERIOD, COUNTER[i]);
		}
	}

}
