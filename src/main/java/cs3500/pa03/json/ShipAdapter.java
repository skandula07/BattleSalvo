package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Dir;

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
 * @param start the beginning coordinate of the ship
 * @param length the length of the ship
 * @param dir the direction of the ship
 */
public record ShipAdapter(

    @JsonProperty("coord") Coord start,
    @JsonProperty("length") int length,
    @JsonProperty("direction") Dir dir) {
}
