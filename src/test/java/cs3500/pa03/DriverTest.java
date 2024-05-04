package cs3500.pa03;

import static cs3500.pa03.Driver.isValidArg;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A class to test the Driver class's methods
 */
class DriverTest {

  /**
   * tests the isValidArg method
   */
  @Test
  public void testIsValidArg() {
    assertEquals(false, isValidArg("hi"));
    assertEquals(true, isValidArg("35001"));
  }
}