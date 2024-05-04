package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.player.AbstractPlayer;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa03.model.player.ManualPlayer;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Carrier;
import cs3500.pa03.model.ships.Destroyer;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the AiPlayer class's methods
 */
public class AiPlayerTest {
  private AbstractPlayer ai;
  private AbstractPlayer user;
  private ArrayList<Ship> emptyFleet;
  private ArrayList<Ship> fleet2;
  private Fleet userFleet;
  private Fleet aiFleet;

  /**
   * Creates an AiPlayer, ManualPLayer, Ship lists and Fleets for testing
   */
  @BeforeEach
  public void setUp() {
    ai = new AiPlayer();
    user = new ManualPlayer("Player");
    emptyFleet = new ArrayList<>();
    fleet2 = new ArrayList<>(Arrays.asList(new Carrier(),
        new Battleship(), new Destroyer(), new Submarine(), new Submarine()));
    userFleet = new Fleet(emptyFleet);
    aiFleet = new Fleet(emptyFleet);
  }


  /**
   * tests the successfulHits method
   */
  @Test
  public void testSuccessfulHits() {
    ai.successfulHits(new ArrayList<>(Arrays.asList(new Coord(0, 0))));
  }
}
