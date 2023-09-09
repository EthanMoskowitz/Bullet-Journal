package cs3500.pa05.model.enumerations;

import java.util.Arrays;
import java.util.List;

/**
 * Enumeration for representing the days of the week
 */
public enum Day {

  /**
   * Monday
   */
  MONDAY,

  /**
   * Tuesday
   */
  TUESDAY,

  /**
   * Wednesday
   */
  WEDNESDAY,

  /**
   * Thursday
   */
  THURSDAY,

  /**
   * Friday
   */
  FRIDAY,

  /**
   * Saturday
   */
  SATURDAY,

  /**
   * Sunday
   */
  SUNDAY;

  /**
   * Returns the days of the week as a list
   *
   * @return  LIST OF DAYS
   */
  public static List<Day> asList() {
    return Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);
  }
}
