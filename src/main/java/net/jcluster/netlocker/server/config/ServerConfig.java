package net.jcluster.netlocker.server.config;

import java.util.concurrent.TimeUnit;

/**
 * Store Global Server configuration parameters
 */
public interface ServerConfig {

	int SCHEDULER_ENGINE_POOL_SIZE = 1;
	int SCHEDULER_ENGINE_INIT_DELAY = 1;
	TimeUnit SCHEDULER_ENGINE_TIME_UNIT = TimeUnit.SECONDS;
	boolean SCHEDULER_ENGINE_DAEMON_FLAG = false;

}
