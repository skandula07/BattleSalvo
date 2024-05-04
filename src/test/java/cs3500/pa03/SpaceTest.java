package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Space;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.EmptyShip;
import cs3500.pa03.model.ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Space class's methods
 */
public class SpaceTest {
  private Space space;
  private Ship testShip;
  private Ship emptyShip;

  /**
   * Creates a Space object and two Ships for testing
   */
  @BeforeEach
  public void setUp() {
    space = new Space();
    testShip = new Battleship();
    emptyShip = new EmptyShip();
  }

  /**
   * tests the setHit method
   */
  @Test
  public void testSetHit() {
    assertEquals(false, space.getHit());
    space.setHit(true);
    assertEquals(true, space.getHit());
    space.setHit(false);
    assertEquals(false, space.getHit());
  }

  /**
   * tests the getHit method
   */
  @Test
  public void testGetHit() {
    assertEquals(false, space.getHit());
    space.setHit(true);
    assertEquals(true, space.getHit());
  }

  /**
   * tests the setOccupyingShip method
   */
  @Test
  public void testSetOccupyingShip() {
    assertEquals(emptyShip, space.getOccupyingShip());
    space.setOccupyingShip(testShip);
    assertEquals(testShip, space.getOccupyingShip());
    space.setOccupyingShip(emptyShip);
    assertEquals(emptyShip, space.getOccupyingShip());
  }

  /**
   * tests the getOccupyingShip method
   */
  @Test
  public void testGetOccupyingShip() {
    assertEquals(emptyShip, space.getOccupyingShip());
    space.setOccupyingShip(testShip);
    assertEquals(testShip, space.getOccupyingShip());
  }

  /**
   * tests the getMissed method
   */
  @Test
  public void testGetMissed() {
    assertEquals(false, space.getMissed());
    space.setMissed(true);
    assertEquals(true, space.getMissed());
  }

  /**
   * tests the setMissed method
   */
  @Test
  public void testSetMissed() {
    assertEquals(false, space.getMissed());
    space.setMissed(true);
    assertEquals(true, space.getMissed());
    space.setMissed(false);
    assertEquals(false, space.getMissed());
  }
}
