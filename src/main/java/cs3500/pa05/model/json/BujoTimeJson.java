package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enumerations.Meridiem;

/**
 * Record for a BujoTime
 *
 * @param hour the hour
 * @param minute the minute
 * @param meridiem the meridiem
 */
public record BujoTimeJson(
    @JsonProperty("hour") int hour,
    @JsonProperty("minute") int minute,
    @JsonProperty("meridiem") Meridiem meridiem) {
}
