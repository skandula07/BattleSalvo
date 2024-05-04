package cs3500.pa03.mockets;

import java.io.InputStream;
import java.net.Socket;

/**
 * represents a Bad Mock Socket
 */
public class BadMocket extends Socket {
  public InputStream getInputStream() {
    return null;
  }
}
