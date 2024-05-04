package cs3500.pa03;

import static cs3500.pa03.view.GameView.reportSuccessfulHits;
import static cs3500.pa03.view.GameView.showDamageReport;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.EmptyShip;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.view.GameView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the GameView class's methods
 */
public class GameViewTest {
  private ByteArrayOutputStream out;
  private ByteArrayOutputStream out2;
  private ByteArrayOutputStream out3;
  private ByteArrayOutputStream out4;
  private PrintStream ps;
  private PrintStream ps2;
  private PrintStream ps3;
  private PrintStream ps4;
  private GameView view;
  private Board b1;
  private Ship testShip;

  /**
   * Creates a Scanner, Controller, and view,
   * various output streams and print streams,
   * a Board and Ships for testing
   */
  @BeforeEach
  public void setUp() {
    view = new GameView();
    out = new ByteArrayOutputStream();
    out2 = new ByteArrayOutputStream();
    out3 = new ByteArrayOutputStream();
    out4 = new ByteArrayOutputStream();
    ps = new PrintStream(out);
    ps2 = new PrintStream(out2);
    ps3 = new PrintStream(out3);
    ps4 = new PrintStream(out4);
    System.setOut(ps);
    b1 = new Board(2, 2);
    testShip = new Battleship();
  }

  /**
   * tests the showPrompt method
   */
  @Test
  public void testShowPrompt() {
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains("test"));
    view.showPrompt("test");
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains("test"));
    System.setOut(System.out);
  }

  /**
   * tests the showWelcome method
   */
  @Test
  public void testShowWelcome() {
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains("Welcome Player!"));
    view.showWelcome("Player");
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains("Welcome Player!"));
    System.setOut(System.out);
  }

  /**
   * tests the showBoard method
   */
  @Test
  public void testShowBoard() {
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains(" -  - \n -  - "));
    view.showBoard(b1);
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains(" -  - \n -  - "));

    b1.getBoard()[0][0].setOccupyingShip(testShip);
    System.setOut(ps2);
    gameOutput = out2.toString();
    assertEquals(false, gameOutput.contains(" B  - \n -  - "));
    view.showBoard(b1);
    gameOutput = out2.toString();
    assertEquals(true, gameOutput.contains(" B  - \n -  - "));

    b1.getBoard()[0][1].setHit(true);
    System.setOut(ps3);
    gameOutput = out3.toString();
    assertEquals(false, gameOutput.contains(" B  X \n -  - "));
    view.showBoard(b1);
    gameOutput = out3.toString();
    assertEquals(true, gameOutput.contains(" B  X \n -  - "));

    b1.getBoard()[1][0].setMissed(true);
    System.setOut(ps4);
    gameOutput = out4.toString();
    assertEquals(false, gameOutput.contains(" B  X \n M  - "));
    view.showBoard(b1);
    gameOutput = out4.toString();
    assertEquals(true, gameOutput.contains(" B  X \n M  - "));

    System.setOut(System.out);
  }

  /**
   * tests the showOpponentBoard method
   */
  @Test
  public void testShowOpponentBoard() {
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains(" -  - \n -  - "));
    view.showOpponentBoard(b1);
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains(" -  - \n -  - "));

    b1.getBoard()[0][0].setOccupyingShip(testShip);
    System.setOut(ps2);
    gameOutput = out2.toString();
    assertEquals(false, gameOutput.contains(" B  - \n -  - "));
    assertEquals(false, gameOutput.contains(" -  - \n -  - "));
    view.showOpponentBoard(b1);
    gameOutput = out2.toString();
    assertEquals(false, gameOutput.contains(" B  - \n -  - "));
    assertEquals(true, gameOutput.contains(" -  - \n -  - "));

    b1.getBoard()[0][1].setHit(true);
    System.setOut(ps3);
    gameOutput = out3.toString();
    assertEquals(false, gameOutput.contains(" -  X \n -  - "));
    view.showOpponentBoard(b1);
    gameOutput = out3.toString();
    assertEquals(true, gameOutput.contains(" -  X \n -  - "));

    b1.getBoard()[1][0].setMissed(true);
    System.setOut(ps4);
    gameOutput = out4.toString();
    assertEquals(false, gameOutput.contains(" -  X \n M  - "));
    view.showOpponentBoard(b1);
    gameOutput = out4.toString();
    assertEquals(true, gameOutput.contains(" -  X \n M  - "));

    System.setOut(System.out);
  }

  /**
   * tests the showDamageReport method
   */
  @Test
  public void testShowDamageReport() {
    showDamageReport(new ArrayList<>(), "Ai Player");
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains("Ai Player has hit your ship at (0, 0)."));

    showDamageReport(new ArrayList<>(Arrays.asList(new Coord(0, 0))), "Ai Player");
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains("Ai Player has hit your ship at (0, 0)."));
  }

  /**
   * tests the reportSuccessfulHits
   */
  @Test
  public void testReportSuccessfulHits() {
    reportSuccessfulHits(new ArrayList<>());
    String gameOutput = out.toString();
    assertEquals(false, gameOutput.contains("You've hit your opponent's ship at (0, 0)."));

    reportSuccessfulHits(new ArrayList<>(Arrays.asList(new Coord(0, 0))));
    gameOutput = out.toString();
    assertEquals(true, gameOutput.contains("You've hit your opponent's ship at (0, 0)."));
  }
}
