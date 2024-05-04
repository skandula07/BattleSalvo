package cs3500.pa03.model;

import static cs3500.pa03.model.ships.ShipType.BATTLESHIP;
import static cs3500.pa03.model.ships.ShipType.CARRIER;
import static cs3500.pa03.model.ships.ShipType.DESTROYER;
import static cs3500.pa03.model.ships.ShipType.SUBMARINE;

import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Carrier;
import cs3500.pa03.model.ships.Destroyer;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.ShipType;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A class representing a Board
 */
public class Board {
  private Space[][] board;
  private int height;
  private int width;
  private List<Coord> coveredSpaces;

  /**
   * Instantiates a Board object
   *
   * @param h the height of the Board
   *
   * @param w the width of the Board
   *
   */
  public Board(int h, int w) {
    height = h;
    width = w;
    board = new Space[height][width];
    coveredSpaces = new ArrayList<>();
    this.fillBoard();
  }

  /**
   * Sets up this Board using the number of occurrences of each ShipType
   * specified in the given Map
   *
   * @param specs the Map of ShipType and number of occurrences
   *
   * @return return the ArrayList of Ships to be a fleet
   */
  public ArrayList<Ship> setBoard(Map<ShipType, Integer> specs) {
    ArrayList<ArrayList<Ship>> fleets = new ArrayList<>();

    fleets.add(setShips(new Carrier(), specs.get(CARRIER), new Random()));
    fleets.add(setShips(new Battleship(), specs.get(BATTLESHIP), new Random()));
    fleets.add(setShips(new Destroyer(), specs.get(DESTROYER), new Random()));
    fleets.add(setShips(new Submarine(), specs.get(SUBMARINE), new Random()));

    ArrayList<Ship> fleet = new ArrayList<>();
    for (List<Ship> ships : fleets) {
      for (Ship s : ships) {
        fleet.add(s);
      }
    }
    return fleet;
  }

  /**
   *
   * @param ship A Ship of a specified type from the ShipType Map
   *
   * @param count the number of occurrences of the Ship to be on this Board
   *
   * @param rand a Random object
   *
   * @return the ArrayList of Ships to be a fleet
   */
  public ArrayList<Ship> setShips(Ship ship, int count, Random rand) {
    ArrayList<Ship> ships = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      List<Coord> placement = ship.place(height, width, rand);
      while (placement.size() == 0 || !validPlacement(placement)) {
        placement = ship.place(height, width, rand);
      }
      Ship s = ship.createShip(placement);
      this.coveredSpaces.addAll(placement);
      ships.add(s);
    }
    return ships;
  }

  /**
   * Sets the attributes of the Spaces in this Board
   * based on the given Fleet
   *
   * @param fleet the Fleet with the Ships on this Board
   */
  public void setSpaces(Fleet fleet) {
    for (Ship s : fleet.getFleet()) {
      coveredSpaces.addAll(s.getCoveredSpaces());
      for (Coord c : s.getCoveredSpaces()) {
        int x = c.getX();
        int y = c.getY();
        this.board[y][x].setOccupied(true);
        this.board[y][x].setOccupyingShip(s);
      }
    }
  }

  /**
   * fills this Board with empty Spaces
   */
  private void fillBoard() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = new Space();
      }
    }
  }

  /**
   * getter for the height field
   *
   * @return the height field
   */
  public int getHeight() {
    return height;
  }

  /**
   * getter for the width field
   *
   * @return the width field
   */
  public int getWidth() {
    return width;
  }

  /**
   * getter for the board field
   *
   * @return the board field
   */
  public Space[][] getBoard() {
    return board;
  }

  /**
   * determines if the given placement is valid
   *
   * @param placement the List of Coord to potentially place
   *                  a Ship
   * @return true if none of the Coord in placement is already
   *        occupied
   */
  public boolean validPlacement(List<Coord> placement) {
    for (Coord c : placement) {
      if (coveredSpaces.contains(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * updates the given board to reflect any new hits or misses,
   * removes a ship from the given fleet if it is sunk
   *
   * @param shots a Player's Shots
   *
   * @param fleet a Fleet of ships
   */
  public void updateBoard(Shots shots, Fleet fleet) {
    Space[][] b = this.getBoard();

    for (Coord received : shots.getShotsReceived()) {
      int x = received.getX();
      int y = received.getY();
      if (coveredSpaces.contains(received)) {
        b[y][x].setHit(true);
        Ship occupyingShip = b[y][x].getOccupyingShip();
        if (occupyingShip.allCoveredSpacesHit(this)) {
          occupyingShip.setSunk(true);
          fleet.getFleet().remove(occupyingShip);
        }
      } else {
        b[y][x].setMissed(true);
      }
    }
  }

  /**
   * adds the spaces covered by each Ship in the given Fleet
   * to the coveredSpaces field
   *
   * @param fleet the Fleet of Ships on this board
   *
   * @return all of the covered spaces on this board
   */
  public List<Coord> setCoveredSpaces(Fleet fleet) {
    for (Ship s : fleet.getFleet()) {
      coveredSpaces.addAll(s.getCoveredSpaces());
    }
    for (Coord c : coveredSpaces) {
      int x = c.getX();
      int y = c.getY();
      board[y][x].setOccupied(true);
    }
    return coveredSpaces;
  }

  public List<Coord> getCoveredSpaces() {
    return coveredSpaces;
  }
}
