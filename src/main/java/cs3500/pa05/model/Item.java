package cs3500.pa05.model;

import cs3500.pa05.model.enumerations.Day;

/**
 * Abstract class for a journal item
 */
public abstract class Item {

  private String name;
  private String description;
  private Day day;

  /**
   * Instantiates an Item
   *
   * @param name the name of the item
   * @param description a description of the item
   * @param day the day of the week the item occurs on
   */
  public Item(String name, String description, Day day) {
    this.name = name;
    this.description = description;
    this.day = day;
  }

  /**
   * Gets the name of the item
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the item
   *
   * @param name a name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description of the item
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description
   *
   * @param description a description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the day of the item
   *
   * @return the day
   */
  public Day getDay() {
    return this.day;
  }

  /**
   * Sets the day of the item
   *
   * @param day a day
   */
  public void setDay(Day day) {
    this.day = day;
  }

}
