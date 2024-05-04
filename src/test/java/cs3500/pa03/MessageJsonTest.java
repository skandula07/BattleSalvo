package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.json.CoordinatesJson;
import cs3500.pa03.json.JsonUtils;
import cs3500.pa03.json.MessageJson;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class to test the MessageJson class's methods
 */
public class MessageJsonTest {
  private MessageJson mj1;
  private MessageJson mj2;
  List<Coord> coords;

  /**
   * instantiates a MessageJson and it's necessary fields
   */
  @BeforeEach
  public void setUp() {
    coords = new ArrayList<>(Arrays.asList(new Coord(0, 0), new Coord(0, 1)));
    CoordinatesJson cj = new CoordinatesJson(coords);
    JsonNode node = JsonUtils.serializeRecord(cj);
    mj1 = new MessageJson("report-damage", node);
    mj2 = new MessageJson("take-shots", node);
  }

  /**
   * tests the messageName method
   */
  @Test
  public void testMessageName() {
    assertEquals("report-damage", mj1.messageName());
    assertEquals("take-shots", mj2.messageName());
  }

  /**
   * tests the arguments method
   */
  @Test
  public void testArguments() {
    CoordinatesJson cj = new CoordinatesJson(coords);
    JsonNode node = JsonUtils.serializeRecord(cj);
    assertEquals(node, mj1.arguments());
    assertEquals(node, mj2.arguments());
  }
}
