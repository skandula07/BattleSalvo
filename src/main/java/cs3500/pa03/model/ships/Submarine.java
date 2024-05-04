package cs3500.pa03.model.ships;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing a Submarine
 */
public class Submarine extends Ship {

  /**
   * Instantiates a Submarine object
   *
   */
  public Submarine() {
    super(3, "Submarine");
  }

  /**
   * gets the symbol for a Submarine Ship
   *
   * @return the symbol for a Submarine Ship
   */
  public String getSymbol() {
    return "S";
  }

  /**
   * creates a new Subamrine that covers the Coords in the given List
   *
   * @param coveredSpaces the Coords to be covered by the new Submarine
   *
   * @return the new Submarine that covers the given Coords
   */
  public Ship createShip(List<Coord> coveredSpaces) {
    Ship s =  new Submarine();
    s.getCoveredSpaces().addAll(coveredSpaces);
    return s;
  }

  /**
   * determines if this Submarine is equal to the given Object
   *
   * @param o the Object to be compared to
   *
   * @return true if the other Object is a Submarine covering the same Coords
   */
  public boolean equals(Object o) {
    if (!(o instanceof Submarine)) {
      return false;
    } else {
      Ship that = (Submarine) o;
      return this.getCoveredSpaces().equals(that.getCoveredSpaces());
    }
  }

  /**
   * overrides the hashCode method
   *
   * @return the hashcode of this Submarine
   */
  @Override
  public int hashCode() {
    return this.getCoveredSpaces().hashCode() * 17 + name.hashCode();
  }
}
