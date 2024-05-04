package cs3500.pa03.model.ships;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing a Battleship
 */
public class Battleship extends Ship {

  /**
   * Instantiates a Battleship object
   *
   */
  public Battleship() {
    super(5, "Battleship");
  }

  /**
   * gets the symbol of this Battleship
   *
   * @return the symbol of Battleships
   */
  public String getSymbol() {
    return "B";
  }

  /**
   * creates a new Battleship that covers the Coords in the given List
   *
   * @param coveredSpaces the List of Coord for the new Battleship to cover
   *
   * @return the new Battleship
   */
  public Ship createShip(List<Coord> coveredSpaces) {
    Ship b =  new Battleship();
    b.getCoveredSpaces().addAll(coveredSpaces);
    return b;
  }

  /**
   * determines if this Battleship is equals to the given Object
   *
   * @param o the Object to be compared to
   *
   * @return true if the given Object is a Battleship covering the same Coords
   */
  public boolean equals(Object o) {
    if (!(o instanceof Battleship)) {
      return false;
    } else {
      Ship that = (Battleship) o;
      return this.getCoveredSpaces().equals(that.getCoveredSpaces());
    }
  }
}
