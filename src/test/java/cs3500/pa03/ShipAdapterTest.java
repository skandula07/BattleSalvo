package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.json.ShipAdapter;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;
import cs3500.pa03.model.ships.Battleship;
import cs3500.pa03.model.ships.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the ShipAdapter record
 */
public class ShipAdapterTest {
  private Ship testShip;
  private Ship testShip2;
  private ShipAdapter shipAdapter1;
  private ShipAdapter shipAdapter2;

  /**
   * instantiates the ships that need to be tests
   */

  @BeforeEach
  public void setTestShips() {

    testShip = new Battleship();
    testShip2 = new Battleship();

    List<Coord> list1 = new ArrayList<>(Arrays.asList(new Coord(0, 4),
        new Coord(1, 4), new Coord(2, 4), new Coord(3, 4),
        new Coord(4, 4)));
    Random rand3 = new Random(3);
    testShip.place(10, 10, rand3);

    List<Coord> list2 = new ArrayList<>(Arrays.asList(new Coord(0, 1),
        new Coord(0, 2), new Coord(0, 3), new Coord(0, 4),
        new Coord(0, 5)));

    testShip2.place(6, 6, new Random(6));

    shipAdapter1 = new ShipAdapter(new Coord(0, 4), 5, Dir.VERTICAL);
    shipAdapter2 = new ShipAdapter(new Coord(0, 1), 5, Dir.HORIZONTAL);

  }

  /**
   * tests the start() get method in the ShipAdapter record
   */

  @Test
  public void testStart() {
    assertEquals(new Coord(0, 4), shipAdapter1.start());
    assertEquals(new Coord(0, 1), shipAdapter2.start());
  }


  /**
   * tests the length() get method in the ShipAdapter record
   */
  @Test
  public void testLength() {
    assertEquals(5, shipAdapter1.length());
    assertEquals(5, shipAdapter2.length());
  }

  /**
   * tests the dir() get method in the ShipAdapter record
   */
  @Test
  public void testDirection() {
    assertEquals(Dir.VERTICAL, shipAdapter1.dir());
    assertEquals(Dir.HORIZONTAL, shipAdapter2.dir());
  }



}
