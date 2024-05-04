package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.json.SetupReceiveJson;
import cs3500.pa03.model.ships.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests SetUpReceiveJson
 */
public class SetUpReceiveJsonTest {
  private SetupReceiveJson surj;
  private int height;
  private int width;
  private Map<ShipType, Integer> map;

  /**
   * instantiates the SetupReceiveJson
   */
  @BeforeEach
  public void setUp() {
    height = 8;
    width = 7;
    map = new HashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.DESTROYER, 3);
    map.put(ShipType.SUBMARINE, 4);
    surj = new SetupReceiveJson(height, width, map);
  }

  /**
   * tests height method
   */
  @Test
  public void testHeight() {
    assertEquals(8, surj.height());
  }

  /**
   * tests height method
   */
  @Test
  public void testWidth() {
    assertEquals(7, surj.width());
  }


  /**
   * tests fleetSpec method
   */
  @Test
  public void testMap() {
    Map<ShipType, Integer> newMap = new HashMap<>();
    newMap.put(ShipType.CARRIER, 1);
    newMap.put(ShipType.BATTLESHIP, 2);
    newMap.put(ShipType.DESTROYER, 3);
    newMap.put(ShipType.SUBMARINE, 4);

    assertEquals(newMap, surj.fleetSpec());
  }

  /**
   * tests carrier method
   */
  @Test
  public void testCarrier() {
    assertEquals(1, surj.carrier());
  }

  /**
   * tests battleship method
   */
  @Test
  public void testBattleship() {
    assertEquals(2, surj.battleship());
  }

  /**
   * tests destroyer method
   */
  @Test
  public void testDestroyer() {
    assertEquals(3, surj.destroyer());
  }

  /**
   * tests submarine method
   */
  @Test
  public void testSubmarine() {
    assertEquals(4, surj.submarine());
  }




}
