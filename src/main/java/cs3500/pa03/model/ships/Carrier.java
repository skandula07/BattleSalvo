package cs3500.pa03.model.ships;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing a Carrier
 */
public class Carrier extends Ship {

  /**
   * Instantiates a Carrier object
   */
  public Carrier() {
    super(6, "Carrier");
  }

  /**
   * gets the symbol for a Carrier Ship
   *
   * @return the symbol for a Carrier Ship
   */
  public String getSymbol() {
    return "C";
  }

  /**
   * Creates a new Carrier Ship that covers the Coords in the given List
   *
   * @param coveredSpaces the Coords to be covered by the new Ship
   *
   * @return the new Carrier that covers the Coords in the given List
   */
  public Ship createShip(List<Coord> coveredSpaces) {
    Ship c =  new Carrier();
    c.getCoveredSpaces().addAll(coveredSpaces);
    return c;
  }

  /**
   * determines if this Carrier is equal to the given Object
   *
   * @param o the Object to be compared to
   *
   * @return true if the given Object is a Carrier covering the same Coords
   */
  public boolean equals(Object o) {
    if (!(o instanceof Carrier)) {
      return false;
    } else {
      Ship that = (Carrier) o;
      return this.getCoveredSpaces().equals(that.getCoveredSpaces());
    }
  }
}
