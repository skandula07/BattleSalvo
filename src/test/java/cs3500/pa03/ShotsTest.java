package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.controller.Controller;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.Battleship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the Shots class's methods
 */
public class ShotsTest {
  private Shots shots;
  private Player ai;
  private Coord c1;
  private Coord c2;
  private Coord c3;
  private Coord c4;
  private List<Coord> testList;
  private List<Coord> emptyList;

  /**
   * Instantiates an AiPlayer, Shots object, Coords, and Lists
   * of Coords for testing
   */
  @BeforeEach
  public void setUp() {
    ai = new AiPlayer();
    shots = new Shots();
    c1 = new Coord(0, 0);
    c2 = new Coord(0, 1);
    c3 = new Coord(1, 0);
    c4 = new Coord(1, 1);
    testList = new ArrayList<>(Arrays.asList(c1, c2));
    emptyList = new ArrayList<>();
  }

  /**
   * tests the getShotsFired method
   */
  @Test
  public void testGetShotsFired() {
    assertEquals(emptyList, shots.getShotsFired());
    shots.getShotsFired().addAll(testList);
    assertEquals(testList, shots.getShotsFired());
  }

  /**
   * tests the getShotsReceived method
   */
  @Test
  public void testGetShotsReceived() {
    assertEquals(emptyList, shots.getShotsReceived());
    shots.getShotsReceived().addAll(testList);
    assertEquals(testList, shots.getShotsReceived());
  }

  /**
   * tests the getNextRound method
   */
  @Test
  public void testGetNextRound() {
    assertEquals(emptyList, shots.getNextRound());
    shots.getNextRound().addAll(testList);
    assertEquals(testList, shots.getNextRound());
  }

  /**
   * tests the getShotsHitOpponent method
   */
  @Test
  public void testGetShotsHitOpponent() {
    assertEquals(emptyList, shots.getShotsHitOpponent());
    shots.getShotsHitOpponent().addAll(testList);
    assertEquals(testList, shots.getShotsHitOpponent());
  }

  /**
   * tests the takeShots method
   */
  @Test
  public void testTakeShots() {
    Scanner s = new Scanner("Player\n6\n6\n1 1 1 1");
    Controller c = new Controller(s);
    Shots shots = c.getUserShots();
    shots.getNextRound().add(new Coord(0, 0));
    Fleet fleet = c.getUserFleet();
    fleet.getFleet().add(new Battleship());
    assertEquals(1, shots.takeShots(shots, fleet).size());
  }

  /**
   * tests the reportDamage method
   */
  @Test
  public void testReportDamage() {
    Player ai = new AiPlayer();
    Shots shots = new Shots();
    Board board = new Board(2, 2);
    Coord c1 = new Coord(0, 0);
    board.getCoveredSpaces().add(c1);
    Coord c2 = new Coord(1, 1);
    List<Coord> testList = new ArrayList<>(Arrays.asList(c1, c2));
    assertEquals(new ArrayList<>(Arrays.asList(c1)),
        shots.reportDamage(testList, board));
  }
}
