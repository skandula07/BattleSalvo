package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Fleet class's methods
 */
public class FleetTest {
  private Ship s1;
  private Ship s2;
  private List<Ship> testList;
  private Fleet testFleet;

  /**
   * Creates 2 Ship objects, a Ship List, and Fleet for testing
   */
  @BeforeEach
  public void testSetUp() {
    s1 = new Submarine();
    s2 = new Battleship();
    testList = new ArrayList<>(Arrays.asList(s1, s2));
    testFleet = new Fleet(testList);
  }

  /**
   * tests the getFleet method
   */
  @Test
  public void testGetFleet() {
    assertEquals(testList, testFleet.getFleet());

    Fleet testFleet2 = new Fleet(new ArrayList<>());
    assertEquals(new ArrayList<>(), testFleet2.getFleet());
  }

  /**
   * tests the determineNumShots method
   */
  @Test
  public void testDetermineNumShots() {
    Shots s = new Shots();
    Board b = new Board(2, 2);

    assertEquals(2, testFleet.determineNumShots(s, b));
    s.getShotsFired().add(new Coord(0, 0));
    s.getShotsFired().add(new Coord(1, 0));
    s.getShotsFired().add(new Coord(1, 1));
    assertEquals(1, testFleet.determineNumShots(s, b));

  }

}
