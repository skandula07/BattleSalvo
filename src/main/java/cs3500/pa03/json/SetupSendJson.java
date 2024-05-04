package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "fleet": [ { } ]
 * }
 * </code>
 * </p>
 *
 * @param adaptedShips the height of the Board
 *
 */
public record SetupSendJson(
    @JsonProperty("fleet") List<ShipAdapter> adaptedShips) {

}
