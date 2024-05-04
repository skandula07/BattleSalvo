package cs3500.pa03.view;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Space;
import java.util.List;

/**
 * A class for viewing a game
 */
public class GameView {


  /**
   * Instantiates a GameView object with the given Controller
   *
   */
  public GameView() {

  }

  /**
   * displays the given String prompt
   *
   * @param prompt the String to be displayed
   */
  public void showPrompt(String prompt) {
    System.out.println(prompt);
  }

  /**
   * shows the player's board
   *
   * @param b the Space 2d array representing the player's board
   */
  public void showBoard(Board b) {
    System.out.println("\nYOUR BOARD");
    Space[][] board = b.getBoard();
    for (Space[] row : board) {
      System.out.println();
      for (Space s : row) {
        if (s.getHit()) {
          System.out.print(" X ");
        } else if (s.getMissed()) {
          System.out.print(" M ");
        } else {
          System.out.print(" " + s.getOccupyingShip().getSymbol() + " ");
        }
      }
    }
  }

  /**
   * shows the opponent's board
   *
   * @param b the Space 2d array representing the opponenet's board
   */
  public void showOpponentBoard(Board b) {
    System.out.println("\n\nYOUR OPPONENT'S BOARD");
    Space[][] board = b.getBoard();
    for (Space[] row : board) {
      System.out.println();
      for (Space s : row) {
        if (s.getHit()) {
          System.out.print(" X ");
        } else if (s.getMissed()) {
          System.out.print(" M ");
        } else {
          System.out.print(" - ");
        }
      }
    }
  }

  /**
   * displays a report damage
   *
   * @param damage the List of Coord that has damaged ships
   *
   * @param opponentName the opponent's name
   */
  public static void showDamageReport(List<Coord> damage, String opponentName) {
    for (Coord c : damage) {
      System.out.println(opponentName + " has hit your ship at (" + c.getX()
          + ", " + c.getY() + ").");
    }
  }

  /**
   * displays a report of successful hits
   *
   * @param succesfulHits the successful hits
   */
  public static void reportSuccessfulHits(List<Coord> succesfulHits) {
    for (Coord c : succesfulHits) {
      System.out.println(
          "You've hit your opponent's ship at (" + c.getX() + ", " + c.getY() + ").");
    }
  }



  /**
   * shows a welcome message to the user
   *
   * @param name the Player's name
   */
  public void showWelcome(String name) {
    System.out.println("Welcome " + name + "!");
  }
}