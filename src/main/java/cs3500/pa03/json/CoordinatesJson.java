package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.List;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "coordinates": [ {} ]
 * }
 * </code>
 * </p>
 *
 * @param coords a list of shots
 *
 */
public record CoordinatesJson(
    @JsonProperty("coordinates")List<Coord> coords) {

}
