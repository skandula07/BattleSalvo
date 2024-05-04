package cs3500.pa03.model.gameresult;

/**
 * A class for GameResults
 */
public enum GameResult {
  WIN("You win! You've sunk all of your opponent's ships."),
  LOSS("You lose. Your opponent has sunk all of your ships."),
  DRAW("Draw. You and your opponent have both sunk each other's ships.");

  /**
   * Creates a GameResult object with the given String reason
   *
   * @param r the String reason the game has ended
   */
  GameResult(String r) {
    reason = r;
  }

  private String reason;

  /**
   * getter for the reason field
   *
   * @return the reason field
   */
  public String getReason() {
    return reason;
  }
}