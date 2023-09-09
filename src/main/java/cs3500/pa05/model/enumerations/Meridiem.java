package cs3500.pa05.model.enumerations;

/**
 * Enumeration for the time meridiem
 */
public enum Meridiem {

  /**
   * Ante meridiem
   */
  AM,

  /**
   * Post meridiem
   */
  PM;

  /**
   * toString method for meridiems
   */
  @Override
  public String toString() {
    return this.name().toLowerCase();
  }

}
