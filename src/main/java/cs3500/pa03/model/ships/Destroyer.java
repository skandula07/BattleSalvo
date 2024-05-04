package cs3500.pa03.model.ships;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing a Destroyer
 */
public class Destroyer extends Ship {

  /**
   * Instantiates a Destroyer object
   */
  public Destroyer() {
    super(4, "Destroyer");
  }

  /**
   * gets the symbol for a Destroyer Ship
   *
   * @return the symbol for a Destroyer Ship
   */
  public String getSymbol() {
    return "D";
  }

  /**
   * creates a new Destroyer Ship that covers the Coords in the given List
   *
   * @param coveredSpaces the List of Coords to be covered by the new Destroyer
   *
   * @return the new Destroyer Ship
   */
  public Ship createShip(List<Coord> coveredSpaces) {
    Ship d = new Destroyer();
    d.getCoveredSpaces().addAll(coveredSpaces);
    return d;
  }

  /**
   * determines if this Destroyer is equal to the given Object
   *
   * @param o the Object to be compared to
   *
   * @return true if the given Object is a Destroyer that
   *        covers the same Coords
   */
  public boolean equals(Object o) {
    if (!(o instanceof Destroyer)) {
      return false;
    } else {
      Ship that = (Destroyer) o;
      return this.getCoveredSpaces().equals(that.getCoveredSpaces());
    }
  }
}
