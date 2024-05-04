package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Coord class's methods
 */
public class CoordTest {
  private Coord c1;
  private Coord c2;
  private Coord c3;
  private Coord c4;
  private Coord c5;
  private Coord c6;

  /**
   * Creates 6 Coord objects for testing
   */
  @BeforeEach
  public void setUp() {
    c1 = new Coord(0, 0);
    c2 = new Coord(1, 5);
    c3 = new Coord(1, 5);
    c4 = new Coord(1, 6);
    c5 = new Coord(6, 1);
    c6 = new Coord(2, 6);
  }

  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, c1.equals(c1));
    assertEquals(true, c2.equals(c3));
    assertEquals(true, c3.equals(c2));
    assertEquals(false, c1.equals(c2));
    assertEquals(false, c3.equals(c1));
    assertEquals(false, c3.equals(c4));
    assertEquals(false, c6.equals(c4));
    assertEquals(false, c1.equals(5));
  }

  /**
   * tests the getXcoord method
   */
  @Test
  public void testGetXcoord() {
    assertEquals(0, c1.getX());
    assertEquals(1, c2.getX());
    assertEquals(1, c3.getX());
  }

  /**
   * tests the getYcoord method
   */
  @Test
  public void testGetYcoord() {
    assertEquals(0, c1.getY());
    assertEquals(5, c2.getY());
    assertEquals(5, c3.getY());
  }

  /**
   * tests the hashCode method
   */
  @Test
  public void testHashCode() {
    assertEquals(0, c1.hashCode());
    assertEquals(412, c2.hashCode());
    assertEquals(412, c3.hashCode());
    assertEquals(491, c4.hashCode());
    assertEquals(181, c5.hashCode());
  }
}
