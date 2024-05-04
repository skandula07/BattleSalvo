package cs3500.pa03.json;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "name": String,
 * }
 * </code>
 * </p>
 *
 * @param name the name of the player
 *
 */
public record JoinJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType) {
}
