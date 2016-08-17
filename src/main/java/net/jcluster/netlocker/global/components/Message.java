package net.jcluster.netlocker.global.components;

import java.io.Serializable;
import java.util.Date;

/**
 * Represent communication Message structure
 * Implemented as Builder pattern
 */
public abstract class Message implements Serializable {

	private Date timestamp;


	public Date getTimestamp() {
		return timestamp;
	}

	public Message setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
