package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Submarine class's methods
 */
public class SubmarineTest {
  private Ship s1;
  private Ship s2;
  private Ship s3;
  private List<Coord> coords1;
  private List<Coord> coords2;


  /**
   * Creats Submarine objects and Coord lists for testing
   */
  @BeforeEach
  public void setUp() {
    s1 = new Submarine();
    s2 = new Submarine();
    s3 = new Submarine();
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
    assertEquals(false, s1.getSunk());
    assertEquals(false, s2.getSunk());

    s1.setSunk(true);
    s2.setSunk(true);

    assertEquals(true, s1.getSunk());
    assertEquals(true, s2.getSunk());
  }

  /**
   * tests the setSunk method
   */
  @Test
  public void testSetSunk() {
    assertEquals(false, s1.getSunk());
    assertEquals(false, s2.getSunk());

    s1.setSunk(true);
    s2.setSunk(true);

    assertEquals(true, s1.getSunk());
    assertEquals(true, s2.getSunk());
  }

  /**
   * tests the getSymbol method
   */
  @Test
  public void testGetSymbol() {
    assertEquals("S", s1.getSymbol());
    assertEquals("S", s2.getSymbol());
  }

  /**
   * tests the createShip method
   */
  @Test
  public void testCreateShip() {
    Ship testShip = s1.createShip(coords1);
    assertEquals(coords1, testShip.getCoveredSpaces());
    assertEquals(false, testShip.getSunk());
    assertEquals(3, testShip.getSize());
    Ship testShip2 = s2.createShip(new ArrayList<>());
    assertEquals(new ArrayList<Coord>(), testShip2.getCoveredSpaces());
  }

  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, s1.equals(s2));
    Ship testShip1 = s1.createShip(coords1);
    assertEquals(false, testShip1.equals(s2));
    Ship testShip2 = s3.createShip(coords2);
    assertEquals(false, testShip2.equals(s2));
    Ship testShip3 = s2.createShip(coords2);
    assertEquals(true, testShip3.equals(testShip2));
    assertEquals(false, s2.equals(2));
  }

  /**
   * tests the hashCode method
   */
  @Test
  public void testHashCode() {
    assertEquals(true, s1.hashCode() == s2.hashCode());
    s2.getCoveredSpaces().addAll(coords1);
    assertEquals(false, s1.hashCode() == s2.hashCode());
  }

  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Submarine", s1.getName());
  }

  /**
   * tests the isHorizontal method
   */

  @Test
  public void testIsHorizontal() {
    Random rand = new Random(1);
    assertEquals(false, s1.isHorizontal(rand));
    assertEquals(true, s2.isHorizontal(rand));
  }

  /**
   * tests the fits method
   */
  @Test
  public void testFits() {
    assertEquals(true, s1.fits(6, 0));
    assertEquals(false, s1.fits(6, 4));
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
    Ship testShip = s1.createShip(coords1);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[3][1].setHit(true);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[1][1].setHit(true);
    board[2][1].setHit(true);
    assertEquals(true, testShip.allCoveredSpacesHit(b));
  }

  /**
   * tests the getStart method
   */

  @Test
  public void testGetStart() {
    Random rand3 = new Random(3);
    s1.place(10, 10, rand3);
    assertEquals(new Coord(0, 4), s1.getStart());

    s2.place(6, 6, new Random(6));
    assertEquals(new Coord(0, 1), s2.getStart());
  }



  /**
   * tests the getDir method
   */

  @Test
  public void testGetDir() {
    Random rand3 = new Random(3);
    s1.place(10, 10, rand3);
    assertEquals(Dir.HORIZONTAL, s1.getDir());

    s2.place(6, 6, new Random(6));
    assertEquals(Dir.VERTICAL, s2.getDir());
  }

}
