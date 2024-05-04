package cs3500.pa03.model.player;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing a ManualPlayer
 */
public class ManualPlayer extends AbstractPlayer {

  /**
   * instantiates a ManualPlayer object
   *
   * @param n the Player's String name
   */
  public ManualPlayer(String n) {
    super(n);
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public List<Coord> takeShots() {
    return shots.takeShots(shots, fleet);
  }
}
