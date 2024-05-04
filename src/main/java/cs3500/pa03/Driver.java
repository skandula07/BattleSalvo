package cs3500.pa03;

import cs3500.pa03.controller.Controller;
import cs3500.pa03.controller.ProxyController;
import cs3500.pa03.model.player.AiPlayer;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      runSingle();
    } else if (args.length == 2) {
      String host = args[0];
      String port = args[1];
      runServer(host, port);
    }
  }

  /**
   * runs a game with a ManualUser vs an AiPlayer
   */
  public static void runSingle() {
    Scanner s = new Scanner(System.in);
    Controller c = new Controller(s);
    c.getSetupInfo(s);
    c.play();
  }

  /**
   * runs a game with an AiPlayer vs a server AiPlayer
   */
  public static void runServer(String host, String port) {
    if (isValidArg(port)) {
      try {
        Socket server = new Socket(host, Integer.parseInt(port));
        ProxyController controller = new ProxyController(server, new AiPlayer());
        controller.run();
      } catch (IOException e) {
        System.err.println("server does not exist!");
      }
    } else {
      System.err.println("Invalid port argument: " + port + " Port must be an int.");
    }
  }


  /**
   * determines if the given arg for port is an int
   *
   * @param port the String representation of the port argument
   *
   * @return true if the port can be parsed into an int, false otherwise
   */
  public static boolean isValidArg(String port) {
    try {
      Integer.parseInt(port);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
