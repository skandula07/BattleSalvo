package cs3500.pa03.model.ships;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.Space;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An abstract class for a Ship
 */
public abstract class Ship {
  private List<Coord> coveredSpaces;
  private int size;
  private boolean sunk;
  protected String name;

  /**
   * creates a Ship object
   *
   * @param s the Ship's size
   *
   * @param n the Ship's namne
   *
   */
  public Ship(int s, String n) {
    coveredSpaces = new ArrayList<>();
    size = s;
    sunk = false;
    name = n;
  }

  /**
   * getter for the coveredSpaces field
   *
   * @return the coveredSpaces field
   */
  public List<Coord> getCoveredSpaces() {
    return coveredSpaces;
  }

  /**
   * getter for the size field
   *
   * @return the size field
   */
  public int getSize() {
    return size;
  }

  /**
   * getter for the sunk field
   *
   * @return the sunk field
   */
  public boolean getSunk() {
    return sunk;
  }

  /**
   * gets the String symbol for this Ship
   *
   * @return the String symbol for this Ship
   */
  public abstract String getSymbol();

  /**
   * getter for the name field
   *
   * @return the name field
   */
  public String getName() {
    return name;
  }

  /**
   * determines if this Ship will be placed horizontally
   *
   * @param rand the Random object to be used
   *
   * @return true if the random produces 0, false otherwise
   */
  public boolean isHorizontal(Random rand) {
    int num = rand.nextInt(2);
    return num == 0;
  }

  /**
   * places this Ship within the given height and width
   *
   * @param height the height of the Board being placed on
   *
   * @param width the width of the Board being placed on
   *
   * @param rand the Random object to be used
   *
   * @return the List of Coord where this Ship is placed
   */
  public List<Coord> place(int height, int width, Random rand) {
    this.coveredSpaces = new ArrayList<>();
    int y = rand.nextInt(height);
    int x = rand.nextInt(width);

    boolean horizontal = isHorizontal(rand);

    for (int i = 0; i < size; i++) {
      if (horizontal) {
        if (fits(width, x)) {
          this.coveredSpaces.add(new Coord(x + i, y));
        }
      } else {
        if (fits(height, y)) {
          this.coveredSpaces.add(new Coord(x, y + i));
        }
      }
    }
    return coveredSpaces;
  }

  /**
   * determines if this Ship fits in the given dimension
   *
   * @param dim the dimension to fit in
   *
   * @param startCoord the starting Coord
   *
   * @return true if the entire Ship will fit
   */
  public boolean fits(int dim, int startCoord) {
    return startCoord + size <= dim;
  }

  /**
   * creates a new Ship covering the Coords in the given List
   *
   * @param coveredSpaces the Coords to be covered by the new Ship
   *
   * @return the new Ship covering the Coords in the given List
   */
  public abstract Ship createShip(List<Coord> coveredSpaces);

  /**
   * setter for the sunk field
   *
   * @param s the boolean to set the sunk field to
   */
  public void setSunk(boolean s) {
    this.sunk = s;
  }

  /**
   * determines if all of the Coords covered by this Ship
   * have been hit
   *
   * @param b the Board
   *
   * @return true if all of this Ship's coveredSpaces have been hit
   *        false otherwise
   */

  public boolean allCoveredSpacesHit(Board b) {
    Space[][] board = b.getBoard();
    for (Coord c : coveredSpaces) {
      int x = c.getX();
      int y = c.getY();
      if (!board[y][x].getHit()) {
        return false;
      }
    }
    return true;
  }

  /**
   * overrides the hashCode method
   *
   * @return the hashCode of this Ship
   */
  @Override
  public int hashCode() {
    return name.hashCode() + this.getCoveredSpaces().hashCode();
  }

  /**
   * getter for the start field
   *
   * @return the start field
   */
  public Coord getStart() {
    return coveredSpaces.get(0);
  }

  /**
   * Returns a ship direction of Horizontal if the x coordinates of a ship are equal
   * Returns a ship direction of Vertical if the y coordinates of a ship are equal
   *
   * @return A Dir enum that represents whether the ship is horizontal or vertical
   */
  public Dir getDir() {
    Coord start = getStart();
    Coord next = coveredSpaces.get(1);
    if (start.getX() + 1 == next.getX()) {
      return Dir.HORIZONTAL;
    } else {
      return Dir.VERTICAL;
    }
  }
}


