package cs3500.pa03.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class for representing coordinates
 */
public class Coord {
  private int x1;
  private int y1;

  /**
   * Instantiates a Coord object
   *
   * @param x the x coordinate
   *
   * @param y the y coordinate
   */
  public Coord(@JsonProperty ("x") int x, @JsonProperty ("y") int y) {
    x1 = x;
    y1 = y;
  }


  /**
   * getter for the xCoord field
   *
   * @return the xCoord field
   */
  public int getX() {
    return x1;
  }

  /**
   * getter for the yCoord field
   *
   * @return the yCoord field
   */
  public int getY() {
    return y1;
  }

  /**
   * determines if the given Object is equal to this Coord
   *
   * @param o the Object toe be compared to
   *
   * @return true if o is a Coord object with the same x and y values
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coord)) {
      return false;
    }
    Coord that = (Coord) o;
    return this.x1 == that.x1 && this.y1 == that.y1;
  }

  /**
   * gets the hashcode for this object
   *
   * @return the hashcode for this Coord
   */
  @Override
  public int hashCode() {
    return this.x1 * 17 + this.y1 * 79;
  }
}



