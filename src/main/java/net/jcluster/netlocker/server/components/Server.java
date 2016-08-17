package net.jcluster.netlocker.server.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Viktor on 8/15/2016.
 */
public class Server {

	private ServerSocket serverSocket;
	private final ExecutorService SERVER_EXECUTOR;
	private final ExecutorService SOCKET_HANDLER_EXECUTOR;
	private volatile boolean isStarted;


	private Server() {
		SERVER_EXECUTOR = Executors.newSingleThreadExecutor();
		SOCKET_HANDLER_EXECUTOR = Executors.newCachedThreadPool();
	}

	public static Server initServer() throws IOException {
		Server server = new Server();
		server.serverSocket = new ServerSocket(7000);
		return server;
	}

	public void start() {
		isStarted = true;
		SERVER_EXECUTOR.submit(() -> {
			while (isStarted) {
				try {
					Socket socket = serverSocket.accept();
					handleSocket(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
	}

	private void handleSocket(Socket socket) throws IOException {
		SOCKET_HANDLER_EXECUTOR.submit(() -> {
			try {
				OutputStream os = socket.getOutputStream();
				os.write("HELLO FROM SERVER!\n".getBytes());
				os.flush();

				InputStream is = socket.getInputStream();
				int ch;
				while ((ch = is.read()) != -1) {
					System.out.print((char) ch);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void stop() {
		isStarted = false;
	}

}
