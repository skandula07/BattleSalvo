package cs3500.pa03.model;

import cs3500.pa03.model.ships.EmptyShip;
import cs3500.pa03.model.ships.Ship;

/**
 * A class representing a Space
 */
public class Space {
  private boolean occupied;
  private boolean hit;
  private boolean missed;
  private Ship occupyingShip;

  /**
   * Instantiates a Space object
   */
  public Space() {
    occupied = false;
    hit = false;
    missed = false;
    occupyingShip = new EmptyShip();
  }

  /**
   * getter for the occupied field
   *
   * @return the occupied field
   */
  public boolean getOccupied() {
    return occupied;
  }

  /**
   * getter for the hit field
   *
   * @return the hit field
   */
  public boolean getHit() {
    return hit;
  }

  /**
   * getter for the missed field
   *
   * @return the missed field
   */
  public boolean getMissed() {
    return missed;
  }

  /**
   * setter for the occupied field
   *
   * @param o the boolean to set the occupied field to
   */
  public void setOccupied(boolean o) {
    occupied = o;
  }

  /**
   * setter for the hit field
   *
   * @param h the boolean to set the hit field to
   */
  public void setHit(boolean h) {
    hit = h;
  }

  /**
   * setter for the missed field
   *
   * @param m the boolean to set the missed field to
   */
  public void setMissed(boolean m) {
    missed = m;
  }

  /**
   * getter for the occupyingShip field
   *
   * @return the occupyingShip field
   */
  public Ship getOccupyingShip() {
    return occupyingShip;
  }

  /**
   * setter for the occupyingShip field
   *
   * @param s the Ship to set the occupyingShip field to
   */
  public void setOccupyingShip(Ship s) {
    occupyingShip = s;
  }
}
