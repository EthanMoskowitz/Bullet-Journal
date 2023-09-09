package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Preferences and its associated methods
 */
class PreferencesTest {
  private Preferences preferences;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    preferences = new Preferences("Prefs", 10, 2);
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Prefs", preferences.getName());
  }

  /**
   * Tests the setName method
   */
  @Test
  public void testSetName() {
    String name = "Different Name";
    assertEquals("Prefs", preferences.getName());
    preferences.setName(name);
    assertEquals(name, preferences.getName());
  }

  /**
   * Tests the getTaskLimit method
   */
  @Test
  public void testGetTaskLimit() {
    assertEquals(10, preferences.getTaskLimit());
  }

  /**
   * Tests the setTaskLimit
   */
  @Test
  public void testSetTaskLimit() {
    assertEquals(10, preferences.getTaskLimit());
    preferences.setTaskLimit(6);
    assertEquals(6, preferences.getTaskLimit());
  }

  /**
   * Tests the getEventLimit method
   */
  @Test
  public void testGetEventLimit() {
    assertEquals(2, preferences.getEventLimit());
  }

  /**
   * Tests the setEventLimit method
   */
  @Test
  public void testSetEventLimit() {
    assertEquals(2, preferences.getEventLimit());
    preferences.setEventLimit(7);
    assertEquals(7, preferences.getEventLimit());
  }

}