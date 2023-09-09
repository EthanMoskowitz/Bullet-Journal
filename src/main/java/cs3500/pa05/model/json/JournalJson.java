package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record for a JSON journal
 */
public record JournalJson(
    @JsonProperty("preferences") PreferencesJson preferences,
    @JsonProperty("tasks") TaskJson[] tasks,
    @JsonProperty("events") EventJson[] events) {
}
