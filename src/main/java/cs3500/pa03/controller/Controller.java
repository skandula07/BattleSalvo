package cs3500.pa03.controller;

import static cs3500.pa03.model.gameresult.GameResult.LOSS;
import static cs3500.pa03.model.gameresult.GameResult.WIN;
import static cs3500.pa03.model.ships.ShipType.BATTLESHIP;
import static cs3500.pa03.model.ships.ShipType.CARRIER;
import static cs3500.pa03.model.ships.ShipType.DESTROYER;
import static cs3500.pa03.model.ships.ShipType.SUBMARINE;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.gameresult.GameResult;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa03.model.player.ManualPlayer;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.ShipType;
import cs3500.pa03.view.GameView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * A class representing a Controller
 */
public class Controller {
  private static Player user;
  private static Player ai;
  private static GameView view;
  private static Scanner scanner;
  private Board userBoard;
  private Board aiBoard;
  private Shots userShots;
  private Shots aiShots;
  private Fleet userFleet;
  private Fleet aiFleet;

  /**
   * instantiates a Controller object along with Model and View objects
   * for running a game
   *
   * @param s the Scanner to be used
   */
  public Controller(Scanner s) {
    scanner = s;
    view = new GameView();
    user = new ManualPlayer(getNameInfo(scanner));
    ai = new AiPlayer();
    userShots = new Shots();
    aiShots = new Shots();
    userFleet = new Fleet(new ArrayList<>());
    aiFleet = new Fleet(new ArrayList<>());
  }

  /**
   * gets the manual player's name
   *
   * @param s the Scanner to be used
   *
   * @return the given String representing the player's name
   */
  public static String getNameInfo(Scanner s) {
    view.showPrompt("What is your name?");
    String name = s.nextLine();
    view.showWelcome(name);
    return name;
  }

  /**
   * Collects information for the game Board and Player's fleets
   *
   * @param s the Scanner to be used
   *
   * @return the maximum fleet size
   */
  public int getSetupInfo(Scanner s) {
    view.showPrompt("Please enter the board height below.");
    int height = getValidDimension(s);
    view.showPrompt("Please enter the board width below.");
    int width = getValidDimension(s);
    int max = Math.min(height, width);
    HashMap<ShipType, Integer> fleet = getValidFleet(s, max);
    userFleet = new Fleet(user.setup(height, width, fleet));
    aiFleet = new Fleet(ai.setup(height, width, fleet));
    userBoard = new Board(height, width);
    aiBoard = new Board(height, width);
    return max;
  }

  /**
   * collects information for a valid fleet
   *
   * @param s the Scanner to be used
   *
   * @param max the max size of the fleet
   *
   * @return a HashMap with each ShipType as a key and the number of occurences
   *        as the value
   */
  public HashMap<ShipType, Integer> getValidFleet(Scanner s, int max) {
    view.showPrompt(
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size " + max + ".");
    HashMap<ShipType, Integer> fleet = new HashMap<>();
    int carrierCount;
    int battleshipCount;
    int destroyerCount;
    int submarineCount;
    String carrier = s.next();
    String battleship = s.next();
    String destroyer = s.next();
    String submarine = s.next();
    try {
      carrierCount = Integer.valueOf(carrier);
      battleshipCount = Integer.valueOf(battleship);
      destroyerCount = Integer.valueOf(destroyer);
      submarineCount = Integer.valueOf(submarine);
    } catch (NumberFormatException e) {
      view.showPrompt("Invalid fleet input: " + carrier + " " + battleship
          + " " + destroyer + " " + submarine + ".");
      return getValidFleet(s, max);
    }
    if (isValidFleet(carrierCount, battleshipCount, destroyerCount, submarineCount, max)) {
      fleet.put(CARRIER, carrierCount);
      fleet.put(BATTLESHIP, battleshipCount);
      fleet.put(DESTROYER, destroyerCount);
      fleet.put(SUBMARINE, submarineCount);
      return fleet;
    } else {
      view.showPrompt("Invalid fleet input: " + carrier + " " + battleship
          + " " + destroyer + " " + submarine + ".");
      return getValidFleet(s, max);
    }
  }

  /**
   * determines if the given number of each ship makes a valid fleet
   *
   * @param c the number of Carrier ships
   *
   * @param b the number of Battleship ships
   *
   * @param d the number of Destroyer ships
   *
   * @param s the number of Submarine ships
   *
   * @param max the maximum number of ships that can be in the fleet
   *
   * @return true if all counts are >= 1 and the total number of ships
   *        does not exceed the max
   */
  public static boolean isValidFleet(int c, int b, int d, int s, int max) {
    return (c >= 1) && (b >= 1) && (d >= 1) && (s >= 1) && ((c + b + d + s) <= max);
  }

  /**
   * gets and returns a valid dimension for a Board
   *
   * @param s the Scanner to be used
   *
   * @return a valid dimension
   */
  public static int getValidDimension(Scanner s) {
    String str = s.nextLine();
    try {
      int dimension = Integer.valueOf(str);
      if (!isValidDimension(dimension)) {
        view.showPrompt(
            "Invalid input: " + dimension + ". Dimension must be between 6 and 15 inclusive."
                + "\nPlease enter the board height and width below.");
        return getValidDimension(s);
      } else {
        return dimension;
      }
    } catch (NumberFormatException e) {
      view.showPrompt("Invalid input: " + str
          + ".\nDimension must be an int between 6 and 15 inclusive.");
      return getValidDimension(s);
    }
  }

  /**
   * determines if the given int is a valid Board dimension
   *
   * @param i the Board dimension
   *
   * @return true if the given int is between 6 and 15 inclusive
   */
  public static boolean isValidDimension(int i) {
    return 6 <= i && i <= 15;
  }

  /**
   * Collects information for the ManualPlayer's volley,
   * Adds the valid volley to the userShots shotsFired field
   *
   * @param s the Scanner to be used
   *
   * @return the ManualPlayer's valid volley
   */
  public ArrayList<Coord> getUserShotInfo(Scanner s) {
    ArrayList<Coord> shots = new ArrayList<>();
    int numShots = userFleet.determineNumShots(userShots, userBoard);
    view.showPrompt(
        "\nPlease input your next round of shots in x y format."
            + " Hit enter after each set of coordinates."
            + "\nYou get to take " + numShots + " shots.");
    for (int i = 0; i < numShots; i++) {
      String x = s.next();
      String y = s.next();
      int x2;
      int y2;
      try {
        x2 = Integer.valueOf(x);
        y2 = Integer.valueOf(y);
        shots.add(new Coord(x2, y2));
      } catch (NumberFormatException e) {
        //do nothing
      }
    }
    if (!validShots(shots, userShots, userBoard) || shots.size() < numShots) {
      view.showPrompt("Invalid shot coordinates.");
      return getUserShotInfo(s);
    }
    userShots.getShotsFired().addAll(shots);
    return shots;
  }

  /**
   * determines if a volley of shots is valid
   *
   * @param shots the volley
   *
   * @param s A Player's Shots
   *
   * @param b A Player's Board
   *
   * @return true if all of the shots in the volley are unique and not already fired
   */

  public static boolean validShots(List<Coord> shots, Shots s, Board b) {
    ArrayList<Coord> currentRound = new ArrayList<>();
    for (Coord c : shots) {
      int x = c.getX();
      int y = c.getY();
      int h = b.getHeight();
      int w = b.getWidth();
      if (currentRound.contains(c) || s.getShotsFired().contains(c) || !validShot(x, y, h, w)) {
        return false;
      } else {
        currentRound.add(c);
      }
    }
    return true;
  }

  /**
   * Allows the playing of a BattleSalvo game
   */
  public void play() {
    userBoard.setSpaces(userFleet);
    aiBoard.setSpaces(aiFleet);
    while (!isOver()) {
      userBoard.updateBoard(userShots, userFleet);
      aiBoard.updateBoard(aiShots, aiFleet);

      view.showBoard(userBoard);
      view.showOpponentBoard(aiBoard);

      List<Coord> shotsFromAi = getAiShotInfo();
      List<Coord> shotsFromUser = getUserShotInfo(scanner);
      userShots.getShotsReceived().addAll(shotsFromAi);
      aiShots.getShotsReceived().addAll(shotsFromUser);

      List<Coord> damageFromUser = ai.reportDamage(shotsFromUser);
      List<Coord> damageFromAi = user.reportDamage(shotsFromAi);
      view.showDamageReport(damageFromAi, ai.name());
      view.reportSuccessfulHits(damageFromUser);
      ai.successfulHits(damageFromAi);
      user.successfulHits(damageFromUser);

      userBoard.updateBoard(userShots, userFleet);
      aiBoard.updateBoard(aiShots, aiFleet);
    }

    GameResult result = checkEnd();
    user.endGame(result, result.getReason());
    view.showPrompt(result.getReason());
  }

  /**
   * Collects a valid volley from an AiPlayer,
   * Adds the valid volley to the AiPlayer's Shots shotsFired field
   *
   * @return a valid volley
   */
  public List<Coord> getAiShotInfo() {
    List<Coord> nextRound = ai.takeShots();
    aiShots.getShotsFired().addAll(nextRound);
    return nextRound;
  }

  /**
   * determines if the game is over
   *
   * @return true if either of the Players has 0 ships left unsunk
   */
  public boolean isOver() {
    int u = userFleet.getFleet().size();
    int a = aiFleet.getFleet().size();
    return u == 0 || a == 0;
  }

  /**
   * determines if a shot has valid coordinates
   *
   * @param x the x coordinate of the shot
   *
   * @param y the y coordinates of the shot
   *
   * @param h the height of the board being shot on
   *
   * @param w the width of the board being shot on
   *
   * @return true if the x coordinate is [0, w)
   *        and the y coordinate is [0, h)
   */
  public static boolean validShot(int x, int y, int h, int w) {
    return 0 <= x && x < w && 0 <= y && y < h;
  }

  /**
   * determines and returns the correct end game scenario
   *
   * @return DRAW if both players have no remaining ships, otherwise
   *        LOSS if the ManualPlayer has 0 ships left,
   *        or WIN if the AiPlayer has 0 ships left
   */
  public GameResult checkEnd() {
    int u = userFleet.getFleet().size();
    int a = aiFleet.getFleet().size();
    if (u == 0 && a == 0) {
      return GameResult.DRAW;
    } else if (u == 0) {
      return LOSS;
    } else {
      return WIN;
    }
  }

  /**
   * getter for the userFleet field
   *
   * @return the userFleet field
   */
  public Fleet getUserFleet() {
    return userFleet;
  }

  /**
   * getter for the aiFleet field
   *
   * @return the aiFleet field
   */
  public Fleet getAiFleet() {
    return aiFleet;
  }

  /**
   * getter for the userShots field
   *
   * @return the userShots field
   */
  public Shots getUserShots() {
    return userShots;
  }
}