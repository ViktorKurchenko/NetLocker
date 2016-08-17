package net.jcluster.netlocker.global.components;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * Created by Viktor on 8/17/2016.
 */
public class MessageTest {

	@Test
	public void test() throws IOException {
		StartMessage startMessage = new StartMessage();
		startMessage.setTimestamp(new Date());
		ByteArrayOutputStream array = new ByteArrayOutputStream();
		new ObjectOutputStream(array).writeObject(startMessage);
		new ObjectInputStream(array).readObject();
	}

}
