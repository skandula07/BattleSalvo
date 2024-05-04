package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Destroyer;
import cs3500.pa03.model.ships.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Destroyer class's methods
 */
public class DestroyerTest {
  private Ship d1;
  private Ship d2;
  private Ship d3;
  private List<Coord> coords1;
  private List<Coord> coords2;


  /**
   * Creates 3 Destroyer objects and 2 Coord lists for testing
   */
  @BeforeEach
  public void setUp() {
    d1 = new Destroyer();
    d2 = new Destroyer();
    d3 = new Destroyer();
    coords1 = new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3)));
    coords2 = new ArrayList<>(Arrays.asList(new Coord(2, 1), new Coord(2, 2),
        new Coord(2, 3)));
  }

  /**
   * tests the getSunk method
   */
  @Test
  public void testGetSunk() {
    assertEquals(false, d1.getSunk());
    assertEquals(false, d2.getSunk());

    d1.setSunk(true);
    d2.setSunk(true);

    assertEquals(true, d1.getSunk());
    assertEquals(true, d2.getSunk());

    d1.setSunk(false);
    d2.setSunk(false);

    assertEquals(false, d1.getSunk());
    assertEquals(false, d2.getSunk());
  }

  /**
   * tests the setSunk method
   */
  @Test
  public void testSetSunk() {
    assertEquals(false, d1.getSunk());
    assertEquals(false, d2.getSunk());

    d1.setSunk(true);
    d2.setSunk(true);

    assertEquals(true, d1.getSunk());
    assertEquals(true, d2.getSunk());

    d1.setSunk(false);
    d2.setSunk(false);

    assertEquals(false, d1.getSunk());
    assertEquals(false, d2.getSunk());
  }

  /**
   * tests the getSymbol method
   */
  @Test
  public void testGetSymbol() {
    assertEquals("D", d1.getSymbol());
    assertEquals("D", d2.getSymbol());
  }

  /**
   * tests the createShip method
   */
  @Test
  public void testCreateShip() {
    Ship testShip = d1.createShip(coords1);
    assertEquals(coords1, testShip.getCoveredSpaces());
    assertEquals(false, testShip.getSunk());
    assertEquals(4, testShip.getSize());
    Ship testShip2 = d2.createShip(new ArrayList<>());
    assertEquals(new ArrayList<Coord>(), testShip2.getCoveredSpaces());
  }

  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, d1.equals(d2));
    Ship testShip1 = d1.createShip(coords1);
    assertEquals(false, testShip1.equals(d2));
    Ship testShip2 = d3.createShip(coords2);
    assertEquals(false, testShip2.equals(d2));
    Ship testShip3 = d2.createShip(coords2);
    assertEquals(true, testShip3.equals(testShip2));
    assertEquals(false, d2.equals(2));
  }

  /**
   * tests the hashCode method
   */
  @Test
  public void testHashCode() {
    assertEquals(true, d1.hashCode() == d2.hashCode());
    d2.getCoveredSpaces().addAll(coords1);
    assertEquals(false, d1.hashCode() == d2.hashCode());
  }


  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Destroyer", d1.getName());
  }

  /**
   * tests the isHorizontal method
   */
  @Test
  public void testIsHorizontal() {
    Random rand = new Random(1);
    assertEquals(false, d1.isHorizontal(rand));
    assertEquals(true, d2.isHorizontal(rand));
  }

  /**
   * tests the fits method
   */
  @Test
  public void testFits() {
    assertEquals(true, d1.fits(6, 0));
    assertEquals(false, d1.fits(6, 3));
  }

  /**
   * tests the allCoveredSpaces method
   */
  @Test
  public void testAllCoveredSpaces() {
    Board b = new Board(6, 6);
    Space[][] board = b.getBoard();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        board[i][j] = new Space();
      }
    }
    Ship testShip = d1.createShip(coords1);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[4][1].setHit(true);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[1][1].setHit(true);
    board[2][1].setHit(true);
    board[3][1].setHit(true);
    assertEquals(true, testShip.allCoveredSpacesHit(b));
  }

  /**
   * tests the getStart method
   */
  @Test
  public void testGetStart() {
    Random rand3 = new Random(3);
    d1.place(10, 10, rand3);
    assertEquals(new Coord(0, 4), d1.getStart());

    d2.place(6, 6, new Random(6));
    assertEquals(new Coord(0, 1), d2.getStart());
  }

  /**
   * tests the getDir method
   */

  @Test
  public void testGetDir() {
    Random rand3 = new Random(3);
    d1.place(10, 10, rand3);
    assertEquals(Dir.HORIZONTAL, d1.getDir());

    d2.place(6, 6, new Random(6));
    assertEquals(Dir.VERTICAL, d2.getDir());
  }
}
