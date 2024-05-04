package cs3500.pa03;

import static cs3500.pa03.model.gameresult.GameResult.DRAW;
import static cs3500.pa03.model.gameresult.GameResult.LOSS;
import static cs3500.pa03.model.gameresult.GameResult.WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A class for testing the GameResult class's methods
 */
public class GameResultTest {

  /**
   * tests the getReason method
   */
  @Test
  public void testGetReason() {
    assertEquals("You win! You've sunk all of your opponent's ships.", WIN.getReason());
    assertEquals("You lose. Your opponent has sunk all of your ships.", LOSS.getReason());
    assertEquals("Draw. You and your opponent have both sunk each other's ships.",
        DRAW.getReason());
  }
}
