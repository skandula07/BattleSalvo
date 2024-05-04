package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ships.ShipType;
import java.util.Map;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "height": int height,
 *   "width": int width
 *   "fleet-spec": { }
 * }
 * </code>
 * </p>
 *
 * @param height the height of the Board
 *
 * @param width   the width of the Board
 *
 * @param fleetSpec the Map of ShipType and their number of occurence
 *
 */
public record SetupReceiveJson(
    @JsonProperty("height") int height,
    @JsonProperty("width") int width,
    @JsonProperty("fleet-spec") Map<ShipType, Integer> fleetSpec) {


  /**
   * gets the number of Carriers in this SetupReceiveJson's fleetSpec
   *
   * @return the number of Carriers
   */
  public int carrier() {
    return fleetSpec().get(ShipType.CARRIER);
  }

  /**
   * gets the number of Battleships in this SetupReceiveJson's fleetSpec
   *
   * @return the number of Battleships
   */
  public int battleship() {
    return fleetSpec().get(ShipType.BATTLESHIP);
  }

  /**
   * gets the number of Destroyers in this SetupReceiveJson's fleetSpec
   *
   * @return the number of Destroyers
   */
  public int destroyer() {
    return fleetSpec().get(ShipType.DESTROYER);
  }

  /**
   * gets the number of Submarines in this SetupReceiveJson's fleetSpec
   *
   * @return the number of Submarines
   */
  public int submarine() {
    return fleetSpec().get(ShipType.SUBMARINE);
  }
}
