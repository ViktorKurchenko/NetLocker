package net.jcluster.netlocker.demo;

import net.jcluster.netlocker.server.components.Server;

import java.io.IOException;

/**
 * Demo launcher
 */
public class Application {

   public static void main(String[] args) throws IOException, InterruptedException {
      Server server = Server.initServer();
      System.out.println("Starting...");
      server.start();

      Thread.sleep(60_000);

      server.stop();
      System.out.println("Stopped!");
   }

}
