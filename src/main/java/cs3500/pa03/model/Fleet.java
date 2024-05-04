package cs3500.pa03.model;

import cs3500.pa03.model.ships.Ship;
import java.util.List;

/**
 * A class representing a Fleet of Ships
 */
public class Fleet {
  private List<Ship> fleet;

  /**
   * Instantiates a Fleet object
   *
   * @param f the List of Ships to be included in this Fleet
   */
  public Fleet(List<Ship> f) {
    fleet = f;
  }

  /**
   * getter for the fleet field
   *
   * @return the fleet field
   */
  public List<Ship> getFleet() {
    return fleet;
  }

  /**
   * determines the number of shots that can be taken
   *
   * @param s a Player's Shots
   *
   * @param board the Board
   *
   * @return either the number of ships left in this fleet or the number
   *        of Spaces left in the given board, whichever is smaller
   */
  public int determineNumShots(Shots s, Board board) {
    int min = Math.min((board.getHeight() * board.getWidth()) - s.getShotsFired().size(),
        getFleet().size());
    return min;
  }
}
