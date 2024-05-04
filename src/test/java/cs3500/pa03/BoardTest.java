package cs3500.pa03;

import static cs3500.pa03.model.ships.ShipType.BATTLESHIP;
import static cs3500.pa03.model.ships.ShipType.CARRIER;
import static cs3500.pa03.model.ships.ShipType.DESTROYER;
import static cs3500.pa03.model.ships.ShipType.SUBMARINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.ShipType;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Board class's methods
 */
public class BoardTest {
  private Board board1;
  private Board board2;
  private Board board3;

  private Ship battleship;
  private Map<ShipType, Integer> testMap;

  /**
   * Creates 2 Board objects, Coord lists, and HashMap for testing
   */
  @BeforeEach
  public void setUp() {
    board1 = new Board(6, 6);
    board2 = new Board(7, 9);
    board3 = new Board(3, 3);

    battleship = new Battleship();
    testMap = new HashMap<>();
    testMap.put(CARRIER, 1);
    testMap.put(DESTROYER, 1);
    testMap.put(SUBMARINE, 2);
    testMap.put(BATTLESHIP, 1);
  }

  /**
   * tests the setBoard method
   */
  @Test
  public void testSetBoard() {
    assertEquals(5, board1.setBoard(testMap).size());
  }

  /**
   * tests the getHeight method
   */
  @Test
  public void testGetHeight() {
    assertEquals(6, board1.getHeight());
    assertEquals(7, board2.getHeight());
  }

  /**
   * tests the getWidth method
   */
  @Test
  public void testGetWidth() {
    assertEquals(6, board1.getWidth());
    assertEquals(9, board2.getWidth());
  }

  /**
   * tests the setShips method
   */

  @Test
  public void testSetShips() {
    board1.setShips(battleship, 1, new Random(6));
  }

  /**
   * tests the updateBoard method
   */
  @Test
  public void testUpdateBoard() {
    Ship s = new Submarine();
    List<Coord> testCoord = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2)));
    s.getCoveredSpaces().addAll(testCoord);
    List<Ship> testFleet = new ArrayList<>(Arrays.asList(s));
    Fleet f = new Fleet(testFleet);
    Shots testShots = new Shots();

    board3.updateBoard(testShots, f);
    for (Space[] row : board3.getBoard()) {
      for (Space space : row) {
        assertEquals(false, space.getOccupied());
        assertEquals(false, space.getHit());
        assertEquals(false, space.getMissed());
      }
    }

    board3.setCoveredSpaces(f);
    testShots.getShotsReceived().add(new Coord(0, 0));
    board3.updateBoard(testShots, f);
    assertEquals(true, board3.getBoard()[0][0].getHit());

    testShots.getShotsReceived().add(new Coord(0, 1));
    testShots.getShotsReceived().add(new Coord(0, 2));
    board3.updateBoard(testShots, f);
    assertEquals(true, board3.getBoard()[0][0].getOccupyingShip().getSunk());

    testShots.getShotsReceived().add(new Coord(1, 1));
    board3.updateBoard(testShots, f);
    assertEquals(true, board3.getBoard()[1][1].getMissed());
  }

  /**
   * tests the setCoveredSpaces method
   */
  @Test
  public void testSetCoveredSpaces() {
    Ship s = new Submarine();
    List<Coord> testCoord = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2)));
    s.getCoveredSpaces().addAll(testCoord);
    List<Ship> testFleet = new ArrayList<>(Arrays.asList(s));
    Fleet f = new Fleet(testFleet);
    Shots testShots = new Shots();

    board3.updateBoard(testShots, f);
    for (Space[] row : board3.getBoard()) {
      for (Space space : row) {
        assertEquals(false, space.getOccupied());
      }
    }

    board3.setCoveredSpaces(f);
    assertEquals(true, board3.getBoard()[0][0].getOccupied());
    assertEquals(true, board3.getBoard()[1][0].getOccupied());
    assertEquals(true, board3.getBoard()[2][0].getOccupied());
    assertEquals(false, board3.getBoard()[0][2].getOccupied());

  }

  /**
   * tests the validPlacement method
   */
  @Test
  public void testValidPlacement() {
    Ship s = new Submarine();
    List<Coord> testCoord = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2)));
    s.getCoveredSpaces().addAll(testCoord);
    List<Ship> testFleet = new ArrayList<>(Arrays.asList(s));
    Fleet f = new Fleet(testFleet);

    List<Coord> goodPlacement = new ArrayList<>(Arrays.asList(new Coord(1, 1)));
    List<Coord> badPlacement = new ArrayList<>(Arrays.asList(new Coord(0, 0)));

    board3.setCoveredSpaces(f);
    assertEquals(false, board3.validPlacement(badPlacement));
    assertEquals(true, board3.validPlacement(goodPlacement));

  }

  /**
   * tests the setSpaces method
   */
  @Test
  public void testSetSpaces() {
    Ship s = new Submarine();
    List<Coord> testCoord = new ArrayList<>(Arrays.asList(new Coord(0, 0),
        new Coord(0, 1), new Coord(0, 2)));
    s.getCoveredSpaces().addAll(testCoord);
    List<Ship> testFleet = new ArrayList<>(Arrays.asList(s));
    Fleet f = new Fleet(testFleet);
    assertEquals(false, board3.getBoard()[0][0].getOccupied());
    board3.setSpaces(f);
    assertEquals(true, board3.getBoard()[0][0].getOccupied());
  }
}
