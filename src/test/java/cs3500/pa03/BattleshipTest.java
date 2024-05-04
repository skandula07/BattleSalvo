package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Battleship class's methods
 */
public class BattleshipTest {
  private Ship b1;
  private Ship b2;
  private Ship b3;
  private List<Coord> coords1;
  private List<Coord> coords2;

  /**
   * creates 3 Battleship objects and 2 lists of Coord for testing
   */
  @BeforeEach
  public void setUp() {
    b1 = new Battleship();
    b2 = new Battleship();
    b3 = new Battleship();
    coords1 = new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3), new Coord(1, 4), new Coord(1, 5)));
    coords2 = new ArrayList<>(Arrays.asList(new Coord(2, 1), new Coord(2, 2),
        new Coord(2, 3), new Coord(2, 4), new Coord(2, 5)));
  }

  /**
   * tests the getSunk method
   */
  @Test
  public void testGetSunk() {
    assertEquals(false, b1.getSunk());
    assertEquals(false, b2.getSunk());

    b1.setSunk(true);
    b2.setSunk(true);

    assertEquals(true, b1.getSunk());
    assertEquals(true, b2.getSunk());
  }

  /**
   * tests the setSunk method
   */
  @Test
  public void testSetSunk() {
    assertEquals(false, b1.getSunk());
    assertEquals(false, b2.getSunk());

    b1.setSunk(true);
    b2.setSunk(true);

    assertEquals(true, b1.getSunk());
    assertEquals(true, b2.getSunk());
  }

  /**
   * tests the getSymbol method
   */
  @Test
  public void testGetSymbol() {
    assertEquals("B", b1.getSymbol());
    assertEquals("B", b2.getSymbol());
  }

  /**
   * tests the createShip method
   */
  @Test
  public void testCreateShip() {
    Ship testShip = b1.createShip(coords1);
    assertEquals(coords1, testShip.getCoveredSpaces());
    assertEquals(false, testShip.getSunk());
    assertEquals(5, testShip.getSize());
    Ship testShip2 = b2.createShip(new ArrayList<Coord>());
    assertEquals(new ArrayList<Coord>(), testShip2.getCoveredSpaces());
  }


  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, b1.equals(b2));
    Ship testShip1 = b1.createShip(coords1);
    assertEquals(false, testShip1.equals(b2));
    Ship testShip2 = b3.createShip(coords2);
    assertEquals(false, testShip2.equals(b2));
    Ship testShip3 = b2.createShip(coords2);
    assertEquals(true, testShip3.equals(testShip2));
    assertEquals(false, b2.equals(2));
  }

  /**
   * tets the hashCode method
   */
  @Test
  public void testHashCode() {
    assertEquals(true, b1.hashCode() == b2.hashCode());
    b2.getCoveredSpaces().addAll(coords1);
    assertEquals(false, b1.hashCode() == b2.hashCode());
  }

  /**
   * tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Battleship", b1.getName());
  }

  /**
   * tests the isHorizontal method
   */

  @Test
  public void testIsHorizontal() {
    Random rand = new Random(1);
    assertEquals(false, b1.isHorizontal(rand));
    assertEquals(true, b2.isHorizontal(rand));
  }

  /**
   * tests the fits method
   */
  @Test
  public void testFits() {
    assertEquals(true, b1.fits(6, 0));
    assertEquals(false, b1.fits(6, 3));
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
    Ship testShip = b1.createShip(coords1);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[4][1].setHit(true);
    assertEquals(false, testShip.allCoveredSpacesHit(b));
    board[1][1].setHit(true);
    board[2][1].setHit(true);
    board[3][1].setHit(true);
    board[5][1].setHit(true);
    assertEquals(true, testShip.allCoveredSpacesHit(b));
  }

  /**
   * tests the place method
   */

  @Test
  public void testPlace() {
    List<Coord> list1 = new ArrayList<>(Arrays.asList(new Coord(0, 4),
        new Coord(1, 4), new Coord(2, 4), new Coord(3, 4),
        new Coord(4, 4)));
    Random rand3 = new Random(3);
    assertEquals(list1, b1.place(10, 10, rand3));
    assertEquals(new ArrayList<>(), b1.place(7, 7, rand3));
    assertEquals(new ArrayList<>(), b1.place(10, 10, new Random(2)));
    assertEquals(new ArrayList<>(), b2.place(5, 5, new Random(2)));
    assertEquals(new ArrayList<>(), b2.place(6, 6, new Random(4)));
    assertEquals(new ArrayList<>(), b2.place(6, 6, new Random(5)));

    List<Coord> list2 = new ArrayList<>(Arrays.asList(new Coord(0, 1),
        new Coord(0, 2), new Coord(0, 3), new Coord(0, 4),
        new Coord(0, 5)));
    assertEquals(list2, b2.place(6, 6, new Random(6)));
  }

  /**
   * tests the getStart method
   */

  @Test
  public void testGetStart() {
    Random rand3 = new Random(3);
    b1.place(10, 10, rand3);
    assertEquals(new Coord(0, 4), b1.getStart());

    b2.place(6, 6, new Random(6));
    assertEquals(new Coord(0, 1), b2.getStart());
  }

  /**
   * tests the getDir method
   */

  @Test
  public void testGetDir() {
    Random rand3 = new Random(3);
    b1.place(10, 10, rand3);
    assertEquals(Dir.HORIZONTAL, b1.getDir());

    b2.place(6, 6, new Random(6));
    assertEquals(Dir.VERTICAL, b2.getDir());
  }
}
