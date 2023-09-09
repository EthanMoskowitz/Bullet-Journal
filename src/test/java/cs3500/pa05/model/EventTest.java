package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enumerations.Day;
import cs3500.pa05.model.enumerations.Meridiem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Event and its associated methods
 */
class EventTest {

  private Event event;
  private BujoTime time;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    time = new BujoTime(12, 0, Meridiem.PM);
    event = new Event("Event name", "Desc",
        Day.THURSDAY, time, 1.6);
  }

  /**
   * Tests the getStart method
   */
  @Test
  public void testGetStart() {
    assertEquals(time, event.getStart());
  }

  /**
   * Tests the setStart method
   */
  @Test
  public void testSetStart() {
    assertEquals(time, event.getStart());
    BujoTime newTime = new BujoTime(9, 37, Meridiem.AM);
    event.setStart(newTime);
    assertEquals(newTime, event.getStart());
  }

  /**
   * Tests the getDuration method
   */
  @Test
  public void testGetDuration() {
    assertEquals(1.6, event.getDuration());
  }

  /**
   * Tests the setDuration method
   */
  @Test
  public void testSetDuration() {
    assertEquals(1.6, event.getDuration());
    event.setDuration(8.4);
    assertEquals(8.4, event.getDuration());
  }

}