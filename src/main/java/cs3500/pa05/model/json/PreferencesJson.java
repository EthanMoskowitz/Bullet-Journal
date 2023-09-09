package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record for a JSON preferences
 */
public record PreferencesJson(
    @JsonProperty("name") String name,
    @JsonProperty("taskLimit") int taskLimit,
    @JsonProperty("eventLimit") int eventLimit) {
}
