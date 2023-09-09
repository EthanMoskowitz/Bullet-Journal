package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enumerations.CompletionStatus;
import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Journal and its associated methods
 */
class JournalTest {

  private Preferences preferences;
  private Event eventOne;
  private Task taskOne;
  private Task taskTwo;
  private List<Event> events;
  private List<Task> tasks;
  private Path path;
  private Journal journal;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    preferences = new Preferences("Week name", 5, 7);
    eventOne = new Event("Go to the movies", "Seeing Memento",
        Day.SATURDAY, new BujoTime(12, 0, Meridiem.AM), 2.5);
    Event eventTwo = new Event("Get dinner", "",
        Day.WEDNESDAY, new BujoTime(7, 30, Meridiem.PM), 1.4);
    taskOne = new Task("Do the dishes", "Use soap",
        Day.MONDAY, CompletionStatus.INCOMPLETE);
    taskTwo = new Task("Finish the project", "Write tests",
        Day.FRIDAY, CompletionStatus.COMPLETE);
    Task taskThree = new Task("Take the exam", "Remember SOLID principles",
        Day.THURSDAY, CompletionStatus.COMPLETE);
    events = new ArrayList<>(Arrays.asList(eventOne, eventTwo));
    tasks = new ArrayList<>(Arrays.asList(taskOne, taskTwo, taskThree));
    path = Path.of("Sample directory");
    journal = new Journal(preferences, tasks, events, path);
  }

  /**
   * Tests the getPreferences method
   */
  @Test
  public void testGetPreferences() {
    assertEquals(preferences, journal.getPreferences());
  }

  /**
   * Tests the getTasks method
   */
  @Test
  public void testGetTasks() {
    assertEquals(tasks, journal.getTasks());
  }

  /**
   * Tests the getEvents method
   */
  @Test
  public void testGetEvents() {
    assertEquals(events, journal.getEvents());
  }

  /**
   * Tests the addTask method
   */
  @Test
  public void testAddTask() {
    Task newTask = new Task("Added task", "",
        Day.THURSDAY, CompletionStatus.COMPLETE);
    journal.addTask(newTask);
    assertEquals(4, journal.getTasks().size());
    assertTrue(journal.getTasks().contains(newTask));
  }

  /**
   * Tests the removeTask method
   */
  @Test
  public void testRemoveTask() {
    journal.removeTask(2);
    assertEquals(2, journal.getTasks().size());
    assertTrue(journal.getTasks().contains(taskOne));
    assertTrue(journal.getTasks().contains(taskTwo));
  }

  /**
   * Tests the addEvent method
   */
  @Test
  public void testAddEvent() {
    Event newEvent = new Event("Added event", "", Day.THURSDAY,
        new BujoTime(12, 0, Meridiem.AM), 1.6);
    journal.addEvent(newEvent);
    assertEquals(3, journal.getEvents().size());
    assertTrue(journal.getEvents().contains(newEvent));
  }

  /**
   * Tests the removeEvent method
   */
  @Test
  public void testRemoveEvent() {
    journal.removeEvent(1);
    assertEquals(1, journal.getEvents().size());
    assertTrue(journal.getEvents().contains(eventOne));
  }

  /**
   * Tests the getPath method
   */
  @Test
  public void testGetPath() {
    assertEquals(path, journal.getPath());
  }

  /**
   * Tests the setPath method
   */
  @Test
  public void setPath() {
    Path newPath = Path.of("New Path");
    journal.setPath(newPath);
    assertEquals(newPath, journal.getPath());
  }

}