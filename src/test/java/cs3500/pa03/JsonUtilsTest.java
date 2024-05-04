package cs3500.pa03;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.json.JsonUtils;
import cs3500.pa03.json.MessageJson;
import org.junit.jupiter.api.Test;

/**
 * A class to test the JsonUtils class's methods
 */
public class JsonUtilsTest {

  /**
   * tests the serializeRecord method
   */
  @Test
  public void testSerializeRecord() {
    JsonUtils j = new JsonUtils();
    MessageJson mj = new MessageJson("join",
        new ObjectMapper().getNodeFactory().textNode("void"));
    JsonNode node = JsonUtils.serializeRecord(mj);
  }
}
