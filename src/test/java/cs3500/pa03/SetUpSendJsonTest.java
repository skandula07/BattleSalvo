package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.json.SetupSendJson;
import cs3500.pa03.json.ShipAdapter;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests SetUpSendJson
 */
public class SetUpSendJsonTest {

  private SetupSendJson testSendJson;
  private ArrayList<ShipAdapter> ships;
  private ShipAdapter ship1;
  private ShipAdapter ship2;

  /**
   * instantiates the SetUpSendJson
   */
  @BeforeEach
  public void setUp() {
    ship1 = new ShipAdapter(new Coord(10, 10), 5, Dir.VERTICAL);
    ship2 = new ShipAdapter(new Coord(0, 0), 6, Dir.HORIZONTAL);
    ships = new ArrayList<>();
    ships.add(ship1);
    ships.add(ship2);
    testSendJson = new SetupSendJson(ships);

  }

  /**
   * tests the method adaptedShips()
   */
  @Test
  public void testAdaptedShips() {
    assertEquals(2, testSendJson.adaptedShips().size());
  }


}
