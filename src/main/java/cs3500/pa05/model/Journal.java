package cs3500.pa05.model;

import java.nio.file.Path;
import java.util.List;

/**
 * Class for representing a bullet journal
 */
public class Journal {

  private final Preferences preferences;
  private final List<Task> tasks;
  private final List<Event> events;

  private Path path;



  /**
   * Instantiates a journal
   *
   * @param preferences the preferences of the journal
   * @param events a list of events for the week
   * @param tasks a list of tasks for the week
   * @param path a path to where the journal is stored
   */
  public Journal(Preferences preferences, List<Task> tasks, List<Event> events, Path path) {
    this.preferences = preferences;
    this.tasks = tasks;
    this.events = events;
    this.path = path;
  }


  /**
   * Gets the preferences
   *
   * @return the preferences
   */
  public Preferences getPreferences() {
    return this.preferences;
  }

  /**
   * Gets the tasks
   *
   * @return the tasks
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Gets the events
   *
   * @return the events
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Adds a task to the task list
   *
   * @param task the task
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Removes a task from the task list
   *
   * @param index the index of the list to remove
   */
  public void removeTask(int index) {
    this.tasks.remove(index);
  }

  /**
   * Adds an event to the event list
   *
   * @param event the event
   */
  public void addEvent(Event event) {
    this.events.add(event);
  }

  /**
   * Removes an event from the event list
   *
   * @param index the index of the list to remove
   */
  public void removeEvent(int index) {
    this.events.remove(index);
  }

  /**
   * Gets the path of the journal
   *
   * @return the path
   */
  public Path getPath() {
    return this.path;
  }

  /**
   * Sets the path of the journal
   *
   * @param path a path
   */
  public void setPath(Path path) {
    this.path = path;
  }
}
