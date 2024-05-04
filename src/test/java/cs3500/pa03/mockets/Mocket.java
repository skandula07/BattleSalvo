package cs3500.pa03.mockets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.json.CoordinatesJson;
import cs3500.pa03.json.EndGameReceiveJson;
import cs3500.pa03.json.JsonUtils;
import cs3500.pa03.json.MessageJson;
import cs3500.pa03.json.SetupReceiveJson;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ships.ShipType;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to Mock a Socket for testing
 */
public class Mocket extends Socket {
  private static final JsonNode EMPTY =
      new ObjectMapper().getNodeFactory().nullNode();
  private InputStream in;
  private PrintStream out;
  private boolean isClosed;

  /**
   * instantiates a Mocket object
   */
  public Mocket() {
    this.in = System.in;
    this.out = System.out;
    isClosed = false;
  }

  /**
   * getter for the in field
   *
   * @return the in field
   */
  public InputStream getInputStream() {
    return in;
  }

  /**
   * getter for the out field
   *
   * @return the out field
   */
  public PrintStream getOutputStream() {
    return out;
  }

  /**
   * returns a MessageJson to join for testing
   *
   * @return a MessageJson requesting to join
   */
  public MessageJson sendJoinMessage() {
    MessageJson mj = new MessageJson("join", EMPTY);
    return mj;
  }

  /**
   * returns a MessageJson that represents a server's game setup request
   *
   * @return a MessagesJson requesting to set up the boards
   */
  public MessageJson sendSetupMessage() {

    Map<ShipType, Integer> map = new HashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.DESTROYER, 1);
    map.put(ShipType.SUBMARINE, 1);

    SetupReceiveJson ssj = new SetupReceiveJson(9, 8, map);
    JsonNode node = JsonUtils.serializeRecord(ssj);

    MessageJson mj = new MessageJson("setup", node);
    return mj;
  }

  /**
   * returns a MessageJson to take shots for testing
   *
   * @return a MessageJson requesting to take shots
   */
  public MessageJson sendTakeShots() {
    List<Coord> coords = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2), new Coord(0, 3)));

    CoordinatesJson cj = new CoordinatesJson(coords);
    JsonNode node = JsonUtils.serializeRecord(cj);
    MessageJson mj = new MessageJson("take-shots", node);
    return mj;
  }

  /**
   * returns a MessageJson to inform about successful hits for testing
   *
   * @return a MessageJson with information about successful hits
   */
  public MessageJson sendsSuccessfulHits() {
    List<Coord> coords = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2), new Coord(0, 3)));

    CoordinatesJson cj = new CoordinatesJson(coords);
    JsonNode node = JsonUtils.serializeRecord(cj);
    MessageJson mj = new MessageJson("successful-hits", node);
    return mj;
  }

  /**
   * returns a MessageJson requesting report damage
   *
   * @return a MessageJson requesting report damage
   */
  public MessageJson sendReportDamage() {
    List<Coord> coords = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2), new Coord(0, 3)));

    CoordinatesJson cj = new CoordinatesJson(coords);
    JsonNode node = JsonUtils.serializeRecord(cj);
    MessageJson mj = new MessageJson("report-damage", node);
    return mj;
  }

  /**
   * returns a MessageJson that represents a server request to end the game
   *
   * @return a MessageJson that requests to end the BattleSalvo game
   */
  public MessageJson sendEndGame() {
    EndGameReceiveJson end = new EndGameReceiveJson("WIN", "reason");
    JsonNode node = JsonUtils.serializeRecord(end);
    MessageJson mj = new MessageJson("end-game", node);
    return mj;
  }

  /**
   * returns a MessageJson with a bad message name for testing
   *
   * @return a MessageJson with a bad message name for testing
   */
  public MessageJson sendBadMessage() {
    MessageJson mj = new MessageJson("bad-message", EMPTY);
    return mj;
  }


  /**
   * getter for the isClosed field
   *
   * @return true if the socket is closed
   */
  public boolean isClosed() {
    return isClosed;
  }

  /**
   * an override for the close method
   */
  public void close() {
    isClosed = true;
  }
}