package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Carrier;
import cs3500.pa03.model.ships.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Carrier class's methods
 */
public class CarrierTest {
  private Ship c1;
  private Ship c2;
  private Ship c3;
  private List<Coord> coords1;
  private List<Coord> coords2;


  /**
   * Creates Carrier Ships and Coord lists for testing
   */
  @BeforeEach
  public void setUp() {
    c1 = new Carrier();
    c2 = new Carrier();
    c3 = new Carrier();
    coords1 = new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3), new Coord(1, 4), new Coord(1, 5), new Coord(1, 6)));
    coords2 = new ArrayList<>(Arrays.asList(new Coord(2, 1), new Coord(2, 2),
        new Coord(2, 3), new Coord(2, 4), new Coord(2, 5), new Coord(2, 6)));
  }

  /**
   * tests the getSunk method
   */
  @Test
  public void testGetSunk() {
    assertEquals(false, c1.getSunk());
    assertEquals(false, c2.getSunk());

    c1.setSunk(true);
    c2.setSunk(true);

    assertEquals(true, c1.getSunk());
    assertEquals(true, c2.getSunk());
  }

  /**
   * tests the setSunk method
   *
   */
  @Test
  public void testSetSunk() {
    assertEquals(false, c1.getSunk());
    assertEquals(false, c2.getSunk());

    c1.setSunk(true);
    c2.setSunk(true);

    assertEquals(true, c1.getSunk());
    assertEquals(true, c2.getSunk());
  }

  /**
   * tests the getSymbol method
   */
  @Test
  public void testGetSymbol() {
    assertEquals("C", c1.getSymbol());
    assertEquals("C", c2.getSymbol());
  }

  /**
   * tests the createShip method
   */
  @Test
  public void testCreateShip() {
    Ship testShip = c1.createShip(coords1);
    assertEquals(coords1, testShip.getCoveredSpaces());
    assertEquals(false, testShip.getSunk());
    assertEquals(6, testShip.getSize());
    Ship testShip2 = c2.createShip(new ArrayList<Coord>());
    assertEquals(new ArrayList<Coord>(), testShip2.getCoveredSpaces());
  }

  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, c1.equals(c2));
    Ship testShip1 = c1.createShip(coords1);
    assertEquals(false, testShip1.equals(c2));
    Ship testShip2 = c3.createShip(coords2);
    assertEquals(false, testShip2.equals(c2));
    Ship testShip3 = c2.createShip(coords2);
    assertEquals(true, testShip3.equals(testShip2));
    assertEquals(false, c2.equals(2));
  }

  /**
   * tests the hashCode method
   */
  @Test
  public void testHashCode() {
    assertEquals(true, c1.hashCode() == c2.hashCode());
    c2.getCoveredSpaces().addAll(coords1);
    assertEquals(false, c1.hashCode() == c2.hashCode());
  }
  /**
   * tests the getName method
   */

  @Test
  public void testGetName() {
    assertEquals("Carrier", c1.getName());
  }

  /**
   * tests the isHorizontal method
   */

  @Test
  public void testIsHorizontal() {
    Random rand = new Random(1);
    assertEquals(false, c1.isHorizontal(rand));
    assertEquals(true, c2.isHorizontal(rand));
  }

  /**
   * tests the fits method
   */
  @Test
  public void testFits() {
    assertEquals(true, c1.fits(6, 0));
    assertEquals(false, c1.fits(6, 3));
  }

  /**
   * tests the allCoveredSpaces method
   */
  @Test
  public void testAllCoveredSpaces() {
    Board b = new Board(7, 7);
    Space[][] board = b.getBoard();
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = new Space();
      }
    }
    Ship testShip = c1.createShip(coords1);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[4][1].setHit(true);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[1][1].setHit(true);
    board[2][1].setHit(true);
    board[3][1].setHit(true);
    board[5][1].setHit(true);
    board[6][1].setHit(true);
    assertEquals(true, testShip.allCoveredSpacesHit(b));
  }

  /**
   * tests the getStart method
   */
  @Test
  public void testGetStart() {
    Random rand3 = new Random(3);
    c1.place(10, 10, rand3);
    assertEquals(new Coord(0, 4), c1.getStart());

    c2.place(10, 10, new Random(6));
    assertEquals(new Coord(6, 1), c2.getStart());
  }

  /**
   * tests the getDir method
   */

  @Test
  public void testGetDir() {
    Random rand3 = new Random(3);
    c1.place(10, 10, rand3);
    assertEquals(Dir.HORIZONTAL, c1.getDir());

    c2.place(10, 10, new Random(6));
    assertEquals(Dir.VERTICAL, c2.getDir());
  }
}
