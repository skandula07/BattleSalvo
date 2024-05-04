package cs3500.pa03.model.player;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.gameresult.GameResult;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class for an AbstractPlayer
 */
public abstract class AbstractPlayer implements Player {
  private String name;
  protected Fleet fleet;
  protected Shots shots;
  protected Board board;
  private Map<ShipType, Integer> specs;

  /**
   * Constructor for an AbstractPlayer
   *
   * @param n the Player's name
   */
  public AbstractPlayer(String n) {
    name = n;
    fleet = new Fleet(new ArrayList<>());
    shots = new Shots();
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  public String name() {
    return name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   *
   * @param width          the width of the board, range: [6, 15] inclusive
   *
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    board = new Board(height, width);
    specs = specifications;
    fleet = new Fleet(board.setBoard(specs));
    board.setCoveredSpaces(fleet);
    return fleet.getFleet();
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   *
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *      ship on this board
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return shots.reportDamage(opponentShotsOnBoard, board);
  }


  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    shots.getShotsHitOpponent().addAll(shotsThatHitOpponentShips);
  }


  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   *
   * @param reason the reason for the game ending
   */
  public void endGame(GameResult result, String reason) {
    //do nothing
  }
}
