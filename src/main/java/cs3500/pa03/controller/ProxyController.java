package cs3500.pa03.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.json.CoordinatesJson;
import cs3500.pa03.json.EndGameReceiveJson;
import cs3500.pa03.json.JoinJson;
import cs3500.pa03.json.JsonUtils;
import cs3500.pa03.json.MessageJson;
import cs3500.pa03.json.SetupReceiveJson;
import cs3500.pa03.json.SetupSendJson;
import cs3500.pa03.json.ShipAdapter;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.gameresult.GameResult;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.ShipType;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class for a ProxyController to communicate with a server
 */
public class ProxyController {

  private final Socket server;
  private final ObjectMapper mapper = new ObjectMapper();
  private InputStream in;
  private PrintStream out;
  private final Player aiPlayer;
  private Board aiBoard;
  private final Shots aiShots;
  private Fleet aiFleet;


  /**
   * instantiates a ProxyController
   *
   * @param server   the Socket to connect to
   * @param aiPlayer the AiPlayer
   */
  public ProxyController(Socket server, Player aiPlayer) {
    this.server = server;
    this.aiPlayer = aiPlayer;
    aiShots = new Shots();
    try {
      this.in = server.getInputStream();
      this.out = new PrintStream(server.getOutputStream());
    } catch (IOException e) {
      System.err.println("server cannot connect");
    }
  }

  /**
   * runs a game between this aiPlayer and the server's aiPlayer,
   * handles message reception
   */

  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        try {
          MessageJson message = parser.readValueAs(MessageJson.class);
          delegateMessage(message);
        } catch (IOException e) {
          //e.printStackTrace();
        }
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      System.err.println(e.toString());
    }
  }

  /**
   * assigns the server input to the relevant handler methods
   *
   * @param message a MessageJson record that the server gives
   */
  public void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin(arguments);
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots(arguments);
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalArgumentException("Invalid message name");
    }
  }

  /**
   * sends back a MessageJson with "join' as the method name and
   * with the GitHub username and gametype as arguments
   *
   * @param arguments the arguments of the received MessageJson
   */
  private void handleJoin(JsonNode arguments) {
    JoinJson response = new JoinJson("import-killer", "SINGLE");
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson send = new MessageJson("join", jsonResponse);
    JsonNode node = JsonUtils.serializeRecord(send);
    this.out.print(node);

  }

  /**
   * parses the given JsonNode into a SetupReceiveJson,
   * passes on the height, width, and fleetSpecs to build the board and fleet
   * sends back the Fleet of ships with their positions
   *
   * @param arguments the arguments of the received MessageJson
   */
  private void handleSetup(JsonNode arguments) {
    SetupReceiveJson setupRec = this.mapper.convertValue(arguments, SetupReceiveJson.class);
    int height = setupRec.height();
    int width = setupRec.width();

    Map<ShipType, Integer> fleetSpec = setupRec.fleetSpec();
    List<Ship> ships = aiPlayer.setup(height, width, fleetSpec);
    makeFleet(ships);
    List<ShipAdapter> adaptedShips = new ArrayList<>();
    aiBoard = new Board(height, width);
    aiBoard.setSpaces(aiFleet);

    for (Ship s : aiFleet.getFleet()) {
      ShipAdapter adaptedShip = new ShipAdapter(s.getStart(), s.getSize(), s.getDir());
      adaptedShips.add(adaptedShip);
    }
    SetupSendJson response = new SetupSendJson(adaptedShips);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson send = new MessageJson("setup", jsonResponse);
    JsonNode node = JsonUtils.serializeRecord(send);
    this.out.print(node);
  }

  /**
   * returns a List of coordinates that represent a volley of shots
   * fired at the server's board.
   *
   * @param arguments arguments of the given MessageJson
   */
  private void handleTakeShots(JsonNode arguments) {
    List<Coord> shots = new ArrayList<>();
    if (aiShots.getShotsFired().size() < aiBoard.getHeight() * aiBoard.getWidth()) {
      shots = aiPlayer.takeShots();
      List<Coord> newShots = new ArrayList<>();
      for (int i = 0; i < aiFleet.determineNumShots(aiShots, aiBoard); i++) {
        newShots.add(shots.get(i));
      }
      shots = newShots;
      aiShots.getShotsFired().addAll(shots);
    }

    CoordinatesJson response = new CoordinatesJson(shots);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson send = new MessageJson("take-shots", jsonResponse);
    JsonNode node = JsonUtils.serializeRecord(send);
    this.out.print(node);
  }


  private void handleReportDamage(JsonNode arguments) {
    CoordinatesJson coordRec = this.mapper.convertValue(arguments, CoordinatesJson.class);
    aiShots.getShotsReceived().addAll(coordRec.coords());
    aiBoard.updateBoard(aiShots, aiFleet);
    List<Coord> damage = aiPlayer.reportDamage(coordRec.coords());

    CoordinatesJson response = new CoordinatesJson(damage);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson send = new MessageJson("report-damage", jsonResponse);
    JsonNode node = JsonUtils.serializeRecord(send);
    this.out.print(node);
  }

  /**
   * parses the given JsonNode into a CoordinatesJson,
   * adds the Coords to the shotsHitOpponent of the aiShots field
   *
   * @param arguments the arguments of the received MessageJson
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson coords = this.mapper.convertValue(arguments, CoordinatesJson.class);
    aiShots.getShotsHitOpponent().addAll(coords.coords());
    aiBoard.updateBoard(aiShots, aiFleet);
    CoordinatesJson response = new CoordinatesJson(new ArrayList<>());
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    MessageJson send = new MessageJson("successful-hits", jsonResponse);
    JsonNode node = JsonUtils.serializeRecord(send);
    this.out.print(node);
  }

  /**
   * parses the given JsonNode into an EndGameJson,
   * sends back an empty response,
   * closes the server
   *
   * @param arguments the arguments of the received MessageJson
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameReceiveJson endGame = this.mapper.convertValue(arguments, EndGameReceiveJson.class);
    GameResult result = endGame.getResult();
    String reason = endGame.reason();
    aiPlayer.endGame(result, reason);
    JsonNode emptyNode = mapper.createObjectNode();
    MessageJson mj = new MessageJson("end-game", emptyNode);
    JsonNode node = JsonUtils.serializeRecord(mj);
    System.out.println(reason);
    this.out.println(node);

    try {
      server.close();
    } catch (IOException e) {
      System.err.println("Cannot close server");
    }
  }

  /**
   * sets this aiBoard to a new board with the given height and width
   *
   * @param height the height of the board
   * @param width  the width of the board
   */
  public void makeBoard(int height, int width) {
    this.aiBoard = new Board(height, width);
  }

  /**
   * getter for the aiBoard field
   *
   * @return the aiBoard field
   */
  public Board getBoard() {
    return aiBoard;
  }

  /**
   * sets this aiFleet to a new Fleet containing the Ships in the given List
   *
   * @param fleet the List of Ship used to make this aiFleet
   */
  private void makeFleet(List<Ship> fleet) {
    this.aiFleet = new Fleet(fleet);
  }

  /**
   * getter for the aiFleet field
   *
   * @return the aiFleet field
   */
  public Fleet getFleet() {
    return aiFleet;
  }

  /**
   * getter for the aiShots field
   *
   * @return the aiShots field
   */
  public Shots getShots() {
    return aiShots;
  }
}


