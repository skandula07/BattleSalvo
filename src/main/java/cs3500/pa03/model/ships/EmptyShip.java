package cs3500.pa03.model.ships;

import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * A class representing an EmptyShip
 */
public class EmptyShip extends Ship {

  /**
   * Instantiates an EmptyShip object
   */
  public EmptyShip() {
    super(0, "Empty");
  }

  /**
   * gets the symbol for an EmptyShip
   *
   * @return the symbol for an EmptyShip
   */
  @Override
  public String getSymbol() {
    return "-";
  }

  /**
   * returns this EmptyShip
   *
   * @param coveredSpaces the spaces covered
   *
   * @return this EmptyShip
   */
  @Override
  public Ship createShip(List<Coord> coveredSpaces) {
    return this;
  }

  /**
   * determines if this EmptyShip is equal to the given object
   *
   * @param o the Object to be compared to
   *
   * @return true if the other Object is an EmptyShip
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof EmptyShip)) {
      return false;
    } else {
      return true;
    }
  }
}
