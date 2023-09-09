package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;

/**
 * Record for a JSON task
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") Day day,
    @JsonProperty("status") CompletionStatus status) {
}
