package cs3500.pa05.model.json.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoTime;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Preferences;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.BujoTimeJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.PreferencesJson;
import cs3500.pa05.model.json.TaskJson;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts Model Classes to a full Json Object
 */
public class JournalAdapter {

  /**
   * Converts a JsonNode to a Journal
   *
   * @param journalJson    journal to convert
   * @param path a path to a journal
   * @return        Journal from JsonNode
   */
  public static Journal toJournal(JournalJson journalJson, Path path) {
    PreferencesJson prefJson = journalJson.preferences();
    TaskJson[] tasksJson = journalJson.tasks();
    EventJson[] eventsJson = journalJson.events();

    Preferences preferences = new Preferences(prefJson.name(), prefJson.taskLimit(),
        prefJson.eventLimit());
    List<Task> tasks = new ArrayList<>();
    List<Event> events = new ArrayList<>();

    for (TaskJson tj : tasksJson) {
      tasks.add(new Task(tj.name(), tj.description(), tj.day(), tj.status()));
    }

    for (EventJson ej : eventsJson) {
      BujoTimeJson bujoTimeJson = ej.start();
      BujoTime time =
          new BujoTime(bujoTimeJson.hour(), bujoTimeJson.minute(), bujoTimeJson.meridiem());
      events.add(new Event(ej.name(), ej.description(), ej.day(), time, ej.duration()));
    }

    return new Journal(preferences, tasks, events, path);
  }

  /**
   * Converts a Journal to a JsonNode
   *
   * @param journal    Journal to convert
   * @return           JsonNode from Journal
   */
  public static JournalJson toJson(Journal journal) {
    ObjectMapper mapper = new ObjectMapper();
    PreferencesJson prefJson = mapper.convertValue(journal.getPreferences(), PreferencesJson.class);
    List<Task> tasks = journal.getTasks();
    List<Event> events = journal.getEvents();
    TaskJson[] tasksJson = new TaskJson[tasks.size()];
    EventJson[] eventsJson = new EventJson[events.size()];

    for (int i = 0; i < tasks.size(); i++) {
      tasksJson[i] = mapper.convertValue(tasks.get(i), TaskJson.class);
    }

    for (int i = 0; i < events.size(); i++) {
      eventsJson[i] = mapper.convertValue(events.get(i), EventJson.class);
    }

    return new JournalJson(prefJson, tasksJson, eventsJson);
  }
}
