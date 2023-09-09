package cs3500.pa05.model.enumerations;

import static cs3500.pa05.model.enumerations.Day.FRIDAY;
import static cs3500.pa05.model.enumerations.Day.MONDAY;
import static cs3500.pa05.model.enumerations.Day.SATURDAY;
import static cs3500.pa05.model.enumerations.Day.SUNDAY;
import static cs3500.pa05.model.enumerations.Day.THURSDAY;
import static cs3500.pa05.model.enumerations.Day.TUESDAY;
import static cs3500.pa05.model.enumerations.Day.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class for testing Day
 */
class DayTest {

  private final List<Day> days =
      Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);

  /**
   * Tests the asList method
   */
  @Test
  public void testAsList() {
    assertEquals(days, Day.asList());
  }

}