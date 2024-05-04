package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.json.CoordinatesJson;
import cs3500.pa03.json.JsonUtils;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * A class for testing the CoordinatesJson class's methods
 */
public class CoordinatesJsonTest {
  private Coord c1;
  private Coord c2;
  private CoordinatesJson cj1;
  private List<Coord> list;


  /**
   * instantiates the CoordinatesJson
   */
  @BeforeEach
  public void setUp() {
    c1 = new Coord(1, 2);
    c2 = new Coord(0, 0);

    list = new ArrayList<>(Arrays.asList(c1, c2));
    cj1 = new CoordinatesJson(list);
  }

  /**
   * tests the coords() method
   */
  @Test
  public void testCoords() {
    assertEquals(list, cj1.coords());
  }

}
