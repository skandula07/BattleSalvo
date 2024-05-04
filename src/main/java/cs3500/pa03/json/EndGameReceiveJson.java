package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.gameresult.GameResult;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "result": GameResult,
 *   "reason": String,
 * }
 * </code>
 * </p>
 *
 * @param result the result of the game
 * @param reason   the reason the game ended
 *
 */
public record EndGameReceiveJson(
    @JsonProperty("result") String result,
    @JsonProperty("reason") String reason) {

  /**
   *
   * @return the GameResult
   */
  public GameResult getResult() {
    if (result.equals("WIN")) {
      return GameResult.WIN;
    } else if (result.equals("LOSE")) {
      return GameResult.LOSS;
    } else {
      return GameResult.DRAW;
    }
  }
}
