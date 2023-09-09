package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.enumerations.Meridiem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing BujoTime and its associated methods
 */
class BujoTimeTest {

  private int hour;
  private int minutes;
  private Meridiem meridiem;
  private BujoTime bujoTime;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    hour = 8;
    minutes = 25;
    meridiem = Meridiem.PM;
    bujoTime = new BujoTime(hour, minutes, meridiem);
  }

  /**
   * Tests the constructor for exceptions
   */
  @Test
  public void testConstructor() {
    assertThrows(IllegalArgumentException.class,
        () -> new BujoTime(-1, 25, Meridiem.PM));
    assertThrows(IllegalArgumentException.class,
        () -> new BujoTime(0, -1, Meridiem.PM));
    assertThrows(IllegalArgumentException.class,
        () -> new BujoTime(13, 25, Meridiem.AM));
    assertThrows(IllegalArgumentException.class,
        () -> new BujoTime(5, 61, Meridiem.AM));
  }

  /**
   * Tests the getHour method
   */
  @Test
  public void testGetHour() {
    assertEquals(hour, bujoTime.getHour());
  }

  /**
   * Tests the setHour method
   */
  @Test
  public void testSetHour() {
    assertThrows(IllegalArgumentException.class,
        () -> bujoTime.setHour(-2));
    assertThrows(IllegalArgumentException.class,
        () -> bujoTime.setHour(14));
    assertEquals(hour, bujoTime.getHour());
    bujoTime.setHour(11);
    assertEquals(11, bujoTime.getHour());
  }

  /**
   * Tests the getMinute method
   */
  @Test
  public void testGetMinute() {
    assertEquals(minutes, bujoTime.getMinute());
  }

  /**
   * Tests the setMinute method
   */
  @Test
  public void testSetMinute() {
    assertThrows(IllegalArgumentException.class,
        () -> bujoTime.setMinute(-1));
    assertThrows(IllegalArgumentException.class,
        () -> bujoTime.setMinute(70));
    assertEquals(minutes, bujoTime.getMinute());
    bujoTime.setMinute(13);
    assertEquals(13, bujoTime.getMinute());
  }

  /**
   * Tests the getMeridiem
   */
  @Test
  public void testGetMeridiem() {
    assertEquals(meridiem, bujoTime.getMeridiem());
  }

  /**
   * Tests the setMeridiem method
   */
  @Test
  public void testSetMeridiem() {
    assertEquals(meridiem, bujoTime.getMeridiem());
    bujoTime.setMeridiem(Meridiem.AM);
    assertEquals(Meridiem.AM, bujoTime.getMeridiem());
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    assertEquals("8:25pm", bujoTime.toString());
    bujoTime.setMinute(2);
    assertEquals("8:02pm", bujoTime.toString());
  }

}