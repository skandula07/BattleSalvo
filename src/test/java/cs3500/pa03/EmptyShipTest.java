package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.EmptyShip;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the EmptyShip class's methods
 */
public class EmptyShipTest {
  private EmptyShip e1;
  private EmptyShip e2;

  /**
   * Creates 2 EmptyShip objects for testing
   */
  @BeforeEach
  public void setUp() {
    e1 = new EmptyShip();
    e2 = new EmptyShip();
  }

  /**
   * tests the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(true, e1.equals(e1));
    assertEquals(true, e1.equals(e2));
    assertEquals(true, e2.equals(e1));
    assertEquals(false, e1.equals(new Battleship()));
  }

  @Test
  public void testHashCode() {
    assertEquals(true, e1.hashCode() == e2.hashCode());
  }

  /**
   * tests the getSymbol method
   */
  @Test
  public void testGetSymbol() {
    assertEquals("-", e1.getSymbol());
  }

  /**
   * tests the createShip method
   */
  @Test
  public void testCreateShip() {
    assertEquals(e1, e1.createShip(new ArrayList<Coord>()));
  }
}
