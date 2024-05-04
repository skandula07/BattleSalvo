package cs3500.pa03.model.player;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class for an AiPlayer
 */
public class AiPlayer extends AbstractPlayer {
  private int shootX;
  private int shootY;

  /**
   * Instantiates an AiPlayer
   */
  public AiPlayer() {
    super("AI Player");
    shootX = 0;
    shootY = 0;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public List<Coord> takeShots() {
    List<Coord> round = shots.aiPlayerTakeShots(board, fleet, shootX, shootY, shots);
    if (round.size() != 0) {
      shootX = round.get(round.size() - 1).getX();
      shootY = round.get(round.size() - 1).getY();

      if (shootX < board.getWidth() - 1) {
        shootX++;
      } else {
        shootX = 0;
        shootY++;
      }
    }
    return round;
  }
}