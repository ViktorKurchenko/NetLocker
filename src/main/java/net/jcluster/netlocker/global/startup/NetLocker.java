package net.jcluster.netlocker.global.startup;

/**
 * Instantiate NetLocker
 *
 * Implemented as Singleton pattern
 */
public class NetLocker {

	private NetLocker() {}

	/*public static NetLocker getInstance() {
        NetLocker netLocker = NetLockerHolder.netLocker;
	}*/

	/**
	 * Used to store NetLocker instance
	 */
	private static class NetLockerHolder {
		private static NetLocker netLocker = new NetLocker();
	}

}
