package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Fleet;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.player.ManualPlayer;
import cs3500.pa03.model.player.Player;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Carrier;
import cs3500.pa03.model.ships.Destroyer;
import cs3500.pa03.model.ships.Ship;
import cs3500.pa03.model.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the ManualPlayer class's methods
 */
public class ManualPlayerTest {
  private Player user;
  private List<Ship> emptyFleet;
  private List<Ship> fleet2;
  private Fleet userFleet;
  private Fleet aiFleet;
  private Shots shots;


  /**
   * Creates a ManualPlayer, AiPlayer, and Coord lists and Fleets for testing
   */
  @BeforeEach
  public void setUp() {
    user = new ManualPlayer("Player");
    emptyFleet = new ArrayList<>();
    fleet2 = new ArrayList<>(Arrays.asList(new Carrier(),
        new Battleship(), new Destroyer(), new Submarine(), new Submarine()));
    userFleet = new Fleet(emptyFleet);
    aiFleet = new Fleet(emptyFleet);
    shots = new Shots();
  }

  /**
   * tests the takeShots method
   */
  @Test
  public void testTakeShots() {
    assertEquals(new ArrayList<>(), user.takeShots());
    List<Coord> testList = new ArrayList<>(Arrays.asList(new Coord(0, 0)));
  }

  /**
   * tests the name method
   */
  @Test
  public void testName() {
    assertEquals("Player", user.name());
  }
}
