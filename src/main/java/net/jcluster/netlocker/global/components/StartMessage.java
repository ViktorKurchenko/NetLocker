package net.jcluster.netlocker.global.components;

/**
 * Created by Viktor on 8/17/2016.
 */
public class StartMessage extends Message {

	private MessageType messageType = MessageType.START;


	public MessageType getMessageType() {
		return messageType;
	}

}
