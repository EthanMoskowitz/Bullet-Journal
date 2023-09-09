package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.Meridiem;

/**
 * Represents a BujoTime
 */
public class BujoTime {
  private int hour;
  private int minute;
  private Meridiem meridiem;

  /**
   * Instantiates a BujoTime
   *
   * @param hour the hour
   * @param minute the minute
   * @param meridiem a meridiem
   */
  public BujoTime(int hour, int minute, Meridiem meridiem) {
    if (hour > 12 || hour < 0) {
      throw new IllegalArgumentException("Hour must be between 0 and 12");
    }
    this.hour = hour;
    if (minute > 60 || minute < 0) {
      throw new IllegalArgumentException("Minute must be between 0 and 60");
    }
    this.minute = minute;
    this.meridiem = meridiem;
  }

  /**
   * Gets the hour
   *
   * @return the hour
   */
  public int getHour() {
    return this.hour;
  }

  /**
   * Sets the hour
   *
   * @param hour an hour
   * @throws IllegalArgumentException if the hour is not between 0 and 12
   */
  public void setHour(int hour) {
    if (hour > 12 || hour < 0) {
      throw new IllegalArgumentException("Hour must be between 0 and 12");
    }
    this.hour = hour;
  }

  /**
   * Gets the minute
   *
   * @return the minute
   */
  public int getMinute() {
    return this.minute;
  }

  /**
   * Sets the minute
   *
   * @param minute a minute
   * @throws IllegalArgumentException if the minute is not between 0 and 60
   */
  public void setMinute(int minute) {
    if (minute > 60 || minute < 0) {
      throw new IllegalArgumentException("Minute must be between 0 and 60");
    }
    this.minute = minute;
  }

  /**
   * Gets the meridiem
   *
   * @return the meridiem
   */
  public Meridiem getMeridiem() {
    return this.meridiem;
  }

  /**
   * Sets the meridiem
   *
   * @param meridiem a meridiem
   */
  public void setMeridiem(Meridiem meridiem) {
    this.meridiem = meridiem;
  }

  /**
   * toString method for BujoTimes
   */
  @Override
  public String toString() {
    if (this.minute < 10) {
      return this.hour + ":0" + this.minute + this.meridiem.toString();
    }
    return this.hour + ":" + this.minute + this.meridiem.toString();
  }
}
