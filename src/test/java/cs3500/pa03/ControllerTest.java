package cs3500.pa03;

import static cs3500.pa03.controller.Controller.getNameInfo;
import static cs3500.pa03.controller.Controller.getValidDimension;
import static cs3500.pa03.controller.Controller.isValidDimension;
import static cs3500.pa03.controller.Controller.isValidFleet;
import static cs3500.pa03.controller.Controller.validShot;
import static cs3500.pa03.controller.Controller.validShots;
import static cs3500.pa03.model.gameresult.GameResult.DRAW;
import static cs3500.pa03.model.gameresult.GameResult.LOSS;
import static cs3500.pa03.model.gameresult.GameResult.WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.controller.Controller;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Controller class's methods
 */
public class ControllerTest {
  private Controller controller;
  private Scanner scanner;
  private Shots shots;
  private Player ai;
  private Coord c1;
  private Coord c2;
  private Coord c3;
  private Coord c4;
  private List<Coord> testList;
  private List<Coord> emptyList;

  /**
   * Creates a Controller object and Scanner for testing
   */
  @BeforeEach
  public void setUp() {
    scanner = new Scanner("Player\n");
    controller = new Controller(scanner);

    ai = new AiPlayer();
    shots = new Shots();
    c1 = new Coord(0, 0);
    c2 = new Coord(0, 1);
    c3 = new Coord(1, 0);
    c4 = new Coord(1, 1);
    testList = new ArrayList<>(Arrays.asList(c1, c2));
    emptyList = new ArrayList<>();
  }

  /**
   * tests the isValidDimension method
   */
  @Test
  public void testIsValidDimension() {
    assertEquals(false, isValidDimension(1));
    assertEquals(false, isValidDimension(16));
    assertEquals(true, isValidDimension(6));
    assertEquals(true, isValidDimension(15));
  }

  /**
   * tests the getNameInfo method
   */
  @Test
  public void testGetNameInfo() {
    Scanner s = new Scanner("Player\n");
    assertEquals("Player", getNameInfo(s));
  }

  /**
   * tests the isValidFleet method
   */
  @Test
  public void testIsValidFleet() {
    assertEquals(false, isValidFleet(0, 1, 1, 1, 6));
    assertEquals(false, isValidFleet(1, 0, 1, 1, 6));
    assertEquals(false, isValidFleet(1, 1, 0, 1, 6));
    assertEquals(false, isValidFleet(1, 1, 1, 0, 6));
    assertEquals(false, isValidFleet(4, 1, 1, 1, 6));
    assertEquals(true, isValidFleet(1, 1, 1, 1, 7));
    assertEquals(true, isValidFleet(1, 1, 1, 2, 6));
  }

  /**
   * tests the isOver method
   */
  @Test
  public void testIsOver() {
    assertEquals(true, controller.isOver());
    Fleet userFleet = controller.getUserFleet();
    userFleet.getFleet().add(new Battleship());
    assertEquals(true, controller.isOver());
    Fleet aiFleet = controller.getAiFleet();
    aiFleet.getFleet().add(new Battleship());
    assertEquals(false, controller.isOver());
  }

  /**
   * tests the getValidDimension method
   */
  @Test
  public void testGetValidDimension() {
    assertEquals(6, getValidDimension(new Scanner("0\n6")));
    assertEquals(7, getValidDimension(new Scanner("20\n7")));
    assertEquals(8, getValidDimension(new Scanner("s\n8")));
  }

  /**
   * tests the setup method
   */
  @Test
  public void testGetSetupInfo() {
    Scanner s = new Scanner("Player\n6\n6\n1\n1\n1\n1");
    Controller c = new Controller(s);
    assertEquals(6,   c.getSetupInfo(s));
  }

  /**
   * tests the validShots method
   */
  @Test
  public void testValidShots() {
    List<Coord> testList2 = new ArrayList<>(Arrays.asList(c1, c2, c1));

    Board b = new Board(2, 2);

    assertEquals(true, validShots(new ArrayList<>(Arrays.asList(c1)), shots, b));
    assertEquals(false, validShots(testList2, shots, b));
    shots.getShotsFired().add(c1);
    assertEquals(false, validShots(new ArrayList<>(Arrays.asList(c1)), shots, b));
    List<Coord> testList3 = new ArrayList<>(Arrays.asList(new Coord(-1, 0)));
    assertEquals(false, validShots(testList3, shots, b));
    List<Coord> testList4 = new ArrayList<>(Arrays.asList(new Coord(20, 0)));
    assertEquals(false, validShots(testList4, shots, b));
  }

  /**
   * tests the getAiShotInfo method
   */
  @Test
  public void testGetAiShotInfo() {
    Scanner s = new Scanner("Player\n6\n6\n1\n1\n1\n1");
    Controller c = new Controller(s);
    c.getSetupInfo(s);
    assertEquals(4, c.getAiShotInfo().size());
    assertEquals(4, c.getAiShotInfo().size());
  }

  /**
   * tests the getUserShotInfo method
   */
  @Test
  public void testGetUserShotInfo() {
    Scanner s = new Scanner("Player\n6\n6\n1\n1\n1\n1\n"
        + "0 0 0 0 2 2 3 3 4 4\n"
        + "s 0 1 1 2 2 3 3 4 4\n"
        + "0 0 1 1 2 2 3 3 4 4\n");
    Controller c = new Controller(s);
    c.getSetupInfo(s);
    assertEquals(4, c.getUserShotInfo(s).size());
  }

  /**
   * tests the checkEnd method
   */
  @Test
  public void testCheckEnd() {
    assertEquals(DRAW, controller.checkEnd());
    Fleet userFleet = controller.getUserFleet();
    userFleet.getFleet().add(new Battleship());
    assertEquals(WIN, controller.checkEnd());
    userFleet.getFleet().remove(new Battleship());
    Fleet aiFleet = controller.getAiFleet();
    aiFleet.getFleet().add(new Battleship());
    assertEquals(LOSS, controller.checkEnd());
  }

  /**
   * tests the getValidFleet method
   */
  @Test
  public void testGetValidFleet() {
    Scanner s = new Scanner("Player\n4 2 1 1\n 1 1 1 2\n");
    Controller c = new Controller(s);
    Map<ShipType, Integer> testMap = c.getValidFleet(s, 6);
    assertEquals(1, testMap.get(ShipType.CARRIER));
    assertEquals(1, testMap.get(ShipType.BATTLESHIP));
    assertEquals(1, testMap.get(ShipType.DESTROYER));
    assertEquals(2, testMap.get(ShipType.SUBMARINE));

    Scanner s2 = new Scanner("Player\ns m 2 1\n 1 1 1 2\n");
    Controller c2 = new Controller(s2);
    Map<ShipType, Integer> testMap2 = c2.getValidFleet(s2, 6);
    assertEquals(1, testMap2.get(ShipType.CARRIER));
    assertEquals(1, testMap2.get(ShipType.BATTLESHIP));
    assertEquals(1, testMap2.get(ShipType.DESTROYER));
    assertEquals(2, testMap2.get(ShipType.SUBMARINE));
  }

  /**
   * tests the validShot method
   */
  @Test
  public void testValidShot() {
    assertEquals(true, validShot(0, 0, 6, 6));
    assertEquals(true, validShot(2, 2, 6, 6));

    assertEquals(false, validShot(-1, 2, 6, 6));
    assertEquals(false, validShot(2, -1, 6, 6));

    assertEquals(false, validShot(2, 7, 6, 6));
    assertEquals(false, validShot(7, 2, 6, 6));
  }
}
